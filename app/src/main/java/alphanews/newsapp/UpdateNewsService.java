package alphanews.newsapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.util.Log;

import alphanews.newsapp.engine.NewsEngine;
import alphanews.newsapp.engine.NewsFactory;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class UpdateNewsService extends IntentService {

    static final String EXTRA_RESULT_RECEIVER = "extra_result_receiver";
    private static final String LOG_TAG = UpdateNewsService.class.getSimpleName();
    private static final long UPDATE_PERIOD = 1000*60*5;

    public UpdateNewsService() {
        super("UpdateNewsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            Log.d(LOG_TAG, "Update news service is launched");
            NewsApplication app = (NewsApplication)getApplicationContext();
            NewsFactory factory = app.getNewsFactory();
            NewsEngine engine = factory.createNewsEngine();
            ResultReceiver receiver = intent.getParcelableExtra(EXTRA_RESULT_RECEIVER);
            try {
                engine.updateNews();
                if (receiver != null){
                    receiver.send(Activity.RESULT_OK, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (receiver != null){
                    receiver.send(Activity.RESULT_CANCELED, null);
                }
            }
        }
    }

    public static void startPeriodicallyUpdate(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getService(context, 0, new Intent(context, UpdateNewsService.class), 0);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + UPDATE_PERIOD, UPDATE_PERIOD, alarmIntent);
    }
}
