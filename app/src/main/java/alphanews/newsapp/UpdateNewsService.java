package alphanews.newsapp;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import alphanews.newsapp.domain.News;
import alphanews.newsapp.processor.NewsProcessor;
import alphanews.newsapp.processor.NewsProcessorFactory;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 */
public class UpdateNewsService extends IntentService {

    private static final long UPDATE_PERIOD = 1000*60*5;

    public UpdateNewsService() {
        super("UpdateNewsService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            NewsProcessor alphaProcessor = NewsProcessorFactory.createAlphaBankProcessor();
            List<News> news = alphaProcessor.process();
        }
    }

    public static void startUpdate(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getService(context, 0, new Intent(context, UpdateNewsService.class), 0);
        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, UPDATE_PERIOD, UPDATE_PERIOD, alarmIntent);
    }

    public static void stopUpdate(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = PendingIntent.getService(context, 0, new Intent(context, UpdateNewsService.class), 0);
        alarmManager.cancel(alarmIntent);
    }
}
