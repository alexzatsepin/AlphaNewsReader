package alphanews.newsapp;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.List;

import alphanews.newsapp.domain.News;
import alphanews.newsapp.engine.AlphaNewsFactory;
import alphanews.newsapp.engine.NewsDao;
import alphanews.newsapp.engine.NewsFactory;

public class NewsListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        new GetNewsTask(this).execute();
    }

    private void onGetNews(List<News> news){
        setListAdapter(new NewsAdapter(this, R.layout.news_list_item, news));
    }

    private static class GetNewsTask extends AsyncTask<Void, Void, List<News>>{

        private final WeakReference<NewsListActivity> mActivityRef;

        GetNewsTask(NewsListActivity activity){
            mActivityRef = new WeakReference<NewsListActivity>(activity);
        }

        @Override
        protected List<News> doInBackground(Void... params) {
            List<News> result = null;
            if (mActivityRef.get() != null){
                Activity activity = mActivityRef.get();
                NewsFactory factory = new AlphaNewsFactory(activity.getApplicationContext());
                NewsDao dao = factory.createNewsDao();
                result = dao.getAllNews();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<News> news) {
            if (news != null && mActivityRef.get() != null){
                NewsListActivity activity = mActivityRef.get();
                activity.onGetNews(news);
            }
        }
    }
}
