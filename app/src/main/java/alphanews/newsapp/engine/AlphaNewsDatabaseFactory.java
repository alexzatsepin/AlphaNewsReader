package alphanews.newsapp.engine;

import android.content.Context;

import java.sql.SQLException;

import alphanews.newsapp.NewsApplication;
import alphanews.newsapp.engine.db.NewsDatabaseHelper;
import alphanews.newsapp.engine.db.NewsSqliteHelper;
import alphanews.newsapp.server.commands.AlphabankNewsCommand;

/**
 * Created by Zatsepin on 17.08.2014.
 */
public class AlphaNewsDatabaseFactory implements NewsFactory {
    
    private final Context mContext;

    public AlphaNewsDatabaseFactory(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public NewsEngine createNewsProcessor() {
        return new NewsEngine(new AlphabankNewsCommand(), new AlphaSimpleXmlParser(), createNewsDao());
    }

    @Override
    public NewsDao createNewsDao() {
        NewsApplication application = (NewsApplication)mContext.getApplicationContext();
        NewsDatabaseHelper dbHelper = application.getDataBaseHelper();
        NewsDao dao = null;
        try {
            dao = dbHelper.getNewsDao();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot get news dao instance", e);
        }
        return dao;
    }
}
