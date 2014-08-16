package alphanews.newsapp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import alphanews.newsapp.domain.News;
import alphanews.newsapp.engine.AlphaNewsFileFactory;
import alphanews.newsapp.engine.NewsDao;
import alphanews.newsapp.engine.NewsFactory;

public class NewsListActivity extends ListActivity implements SwipeRefreshLayout.OnRefreshListener{
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateNews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDetachable.onDetach();
    }

    private void updateNews(){
        if (mDetachable != null){
            mDetachable.onDetach();
        }
        GetNewsTask task = new GetNewsTask(this);
        mDetachable = task;
        task.execute();
    }

    private void onGetNews(List<News> news){
        setListAdapter(new NewsAdapter(this, R.layout.news_list_item, news));
        mSwipeRefreshLayout.setRefreshing(false);
        Toast.makeText(getApplicationContext(), R.string.news_was_refresh, Toast.LENGTH_SHORT).show();
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
        new GetNewsTask(this).execute();
    }

    private static class GetNewsTask extends AsyncTask<Void, Void, List<News>> implements Detachable<NewsListActivity>{

        private NewsListActivity mActivity;

        GetNewsTask(NewsListActivity activity){
            mActivity = activity;
        }

        @Override
        protected List<News> doInBackground(Void... params) {
            List<News> result = null;
            if (mActivity != null){
                Log.d(LOG_TAG, "GetNewTask '" + this + "' is executing with mActivity is '" + mActivity + "'");
                NewsApplication app = ((NewsApplication)mActivity.getApplicationContext());
                NewsFactory factory = app.getNewsFactory();
                NewsDao dao = factory.createNewsDao();
                result = dao.getAllNews();
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
