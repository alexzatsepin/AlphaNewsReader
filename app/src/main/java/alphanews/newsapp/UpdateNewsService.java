package alphanews.newsapp;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import alphanews.newsapp.engine.AlphaNewsFileFactory;
import alphanews.newsapp.engine.NewsEngine;
import alphanews.newsapp.engine.NewsFactory;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class UpdateNewsService extends IntentService {

    private static final String LOG_TAG = UpdateNewsService.class.getSimpleName();
    private static final long UPDATE_PERIOD = 1000/**60*5*/ * 3;

    public UpdateNewsService() {
        super("UpdateNewsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(LOG_TAG, "Update news service is launched");
        NewsApplication app = (NewsApplication)getApplicationContext();
        NewsFactory factory = app.getNewsFactory();
        NewsEngine engine = factory.createNewsProcessor();
        engine.process();
    }

    public static void startUpdate(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getService(context, 0, new Intent(context, UpdateNewsService.class), 0);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, UPDATE_PERIOD, UPDATE_PERIOD, alarmIntent);
    }

    public static void stopUpdate(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getService(context, 0, new Intent(context, UpdateNewsService.class), 0);
        alarmManager.cancel(alarmIntent);
    }
}
