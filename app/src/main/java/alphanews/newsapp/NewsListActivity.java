package alphanews.newsapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import alphanews.newsapp.domain.alpha.News;
import alphanews.newsapp.engine.NewsEngine;
import alphanews.newsapp.engine.NewsFactory;

public class NewsListActivity extends ListActivity implements SwipeRefreshLayout.OnRefreshListener, NewsResultReceiver.Receiver{
    private android.support.v4.widget.SwipeRefreshLayout mSwipeRefreshLayout;

    private static final String LOG_TAG = NewsListActivity.class.getSimpleName();
    private Detachable<NewsListActivity> mDetachable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_light,
                                           android.R.color.holo_green_light,
                                           android.R.color.holo_red_light,
                                           android.R.color.black);

        Button developerBtn = (Button)findViewById(R.id.developer);
        developerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsListActivity.this, DeveloperInfoActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCachedNews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDetachable.onDetach();
    }

    private void loadCachedNews(){
        if (mDetachable != null){
            mDetachable.onDetach();
        }
        GetCachedNewsTask task = new GetCachedNewsTask(this);
        mDetachable = task;
        task.execute();
    }

    private void onGetNews(List<News> news){
        setListAdapter(new NewsAdapter(this, R.layout.news_list_item, news));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        News news = (News)getListAdapter().getItem(position);
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra(NewsActivity.EXTRA_NEWS_LINK, news.getLink());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        updateNews();
    }

    private void updateNews(){
        NewsResultReceiver receiver = new NewsResultReceiver(new Handler());
        receiver.setReceiver(this);
        Intent intent = new Intent(getApplicationContext(), UpdateNewsService.class);
        intent.putExtra(UpdateNewsService.EXTRA_RESULT_RECEIVER, receiver);
        startService(intent);
    }

    @Override
    public void onNewsUpdated() {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getApplicationContext(), R.string.news_was_updated, Toast.LENGTH_SHORT).show();
        loadCachedNews();
    }

    @Override
    public void onUpdateError() {
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getApplicationContext(), R.string.news_update_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NewsApplication app = (NewsApplication)getApplicationContext();
        app.releaseDataBaseHelper();
    }

    private static class GetCachedNewsTask extends AsyncTask<Void, Void, List<News>> implements Detachable<NewsListActivity>{

        private NewsListActivity mActivity;

        GetCachedNewsTask(NewsListActivity activity){
            mActivity = activity;
        }

        @Override
        protected List<News> doInBackground(Void... params) {
            List<News> result = null;
            if (mActivity != null){
                Log.d(LOG_TAG, "GetNewTask '" + this + "' is executing with mActivity is '" + mActivity + "'");
                NewsApplication app = ((NewsApplication)mActivity.getApplicationContext());
                NewsFactory factory = app.getNewsFactory();
                NewsEngine engine = factory.createNewsEngine();
                result = engine.getCachedNews();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<News> news) {
            Log.d(LOG_TAG, "GetNewTask '" + this + "' done and mActivity is '" + mActivity + "'");
            if (news != null && mActivity != null){
                mActivity.onGetNews(news);
            }
        }

        @Override
        public void onDetach() {
            mActivity = null;
        }
    }

    interface Detachable<T>{
        void onDetach();
    }
}
