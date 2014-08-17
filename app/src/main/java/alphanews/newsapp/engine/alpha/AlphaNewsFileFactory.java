package alphanews.newsapp.engine.alpha;

import android.content.Context;

import alphanews.newsapp.engine.NewsDao;
import alphanews.newsapp.engine.NewsEngine;
import alphanews.newsapp.engine.NewsFactory;
import alphanews.newsapp.engine.Parser;
import alphanews.newsapp.engine.Command;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public class AlphaNewsFileFactory implements NewsFactory {

    private final Context mContext;

    public AlphaNewsFileFactory(Context context) {
        mContext = context;
    }

    @Override
    public NewsEngine createNewsEngine() {
        return new NewsEngine(this);
    }

    @Override
    public NewsDao createNewsDao() {
        return new AlphaFileDao(mContext);
    }

    @Override
    public Command createNewsCommand() {
        return new AlphabankNewsCommand();
    }

    @Override
    public Parser createNewsParser() {
        return new AlphaSimpleXmlParser();
    }
}
