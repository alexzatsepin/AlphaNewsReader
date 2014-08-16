package alphanews.newsapp.engine.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import alphanews.newsapp.domain.News;

/**
 * Created by Zatsepin on 17.08.2014.
 */
public class NewsDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "alphanews.db";
    private static final int DB_VERSION = 1;

    private AlphaDbDao mNewsDao = null;

    public NewsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, News.class);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, News.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public AlphaDbDao getNewsDao() throws SQLException {
        if (mNewsDao == null) {
            mNewsDao = new AlphaDbDao(getConnectionSource(), News.class);
        }
        return mNewsDao;
    }
}
