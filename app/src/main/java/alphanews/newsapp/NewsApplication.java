package alphanews.newsapp;

import android.app.Application;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import alphanews.newsapp.engine.alpha.AlphaDatabaseHelper;
import alphanews.newsapp.engine.alpha.AlphaNewsDatabaseFactory;
import alphanews.newsapp.engine.alpha.AlphaNewsFileFactory;
import alphanews.newsapp.engine.NewsFactory;

/**
 * Created by Zatsepin on 17.08.2014.
 */
public class NewsApplication extends Application {
    /**
     * Indicates what type of cache use, sqlite or internal storage
     */
    private final static boolean CONFIG_DB_CACHE = true;
    private AlphaDatabaseHelper mDbHelper;
    private NewsFactory mNewsFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        if (CONFIG_DB_CACHE){
            mNewsFactory = new AlphaNewsDatabaseFactory(this);
        }else{
            mNewsFactory = new AlphaNewsFileFactory(this);
        }
    }

    public synchronized AlphaDatabaseHelper getDataBaseHelper(){
        if (mDbHelper == null){
            mDbHelper = OpenHelperManager.getHelper(this, AlphaDatabaseHelper.class);
        }
        return mDbHelper;
    }

    public synchronized void releaseDataBaseHelper(){
        if (mDbHelper != null){
            OpenHelperManager.releaseHelper();
            mDbHelper = null;
        }
    }

    public NewsFactory getNewsFactory(){
        return mNewsFactory;
    }

}
