package alphanews.newsapp.engine;

import android.content.Context;

/**
 * Created by Zatsepin on 16.08.2014.
 */
public abstract class BaseNewsDao implements NewsDao {

    private final Context mContext;

    protected BaseNewsDao(Context mContext) {
        this.mContext = mContext;
    }

    protected Context getContext(){
        return mContext;
    }

}
