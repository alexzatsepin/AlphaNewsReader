package alphanews.newsapp.engine.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zatsepin on 16.08.2014.
 */
public class NewsSqliteHelper extends SQLiteOpenHelper {

    static final String TABLE_NEWS = "news";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_TITLE = "title";
    static final String COLUMN_LINK = "link";
    static final String COLUMN_DESCRIPTION = "description";
    static final String COLUMN_PUB_DATE = "date";
    static final String COLUMN_GUID = "guid";
    private static final String DATABASE_NAME = "news.db";
    private static final int DB_VERSION = 1;
    private static final String CREATE_DB_SQL = "create table "
            + TABLE_NEWS + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_LINK + " text not null unique, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_DESCRIPTION + " text not null, "
            + COLUMN_PUB_DATE + " text not null, "
            + COLUMN_GUID + " text not null);";

    public NewsSqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //empty
    }
}
