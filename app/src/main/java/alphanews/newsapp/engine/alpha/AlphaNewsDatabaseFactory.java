package alphanews.newsapp.engine.alpha;

import android.content.Context;

import java.sql.SQLException;

import alphanews.newsapp.NewsApplication;
import alphanews.newsapp.engine.NewsDao;
import alphanews.newsapp.engine.NewsEngine;
import alphanews.newsapp.engine.NewsFactory;
import alphanews.newsapp.engine.Parser;
import alphanews.newsapp.engine.Command;

/**
 * Created by Zatsepin on 17.08.2014.
 */
public class AlphaNewsDatabaseFactory implements NewsFactory {
    
    private final Context mContext;

    public AlphaNewsDatabaseFactory(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public NewsEngine createNewsEngine() {
        return new NewsEngine(this);
    }

    @Override
    public NewsDao createNewsDao() {
        NewsApplication application = (NewsApplication)mContext.getApplicationContext();
        AlphaDatabaseHelper dbHelper = application.getDataBaseHelper();
        NewsDao dao = null;
        try {
            dao = dbHelper.getNewsDao();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot get news dao instance", e);
        }
        return dao;
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
