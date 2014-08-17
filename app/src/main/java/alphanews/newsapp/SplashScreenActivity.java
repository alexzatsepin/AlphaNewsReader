package alphanews.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;


public class SplashScreenActivity extends Activity implements NewsResultReceiver.Receiver {

    private static final String PREF_KEY_IS_FIRST_LAUNCH = "pref_key_is_first_launch";
    public static final int SPLASH_SCREEN_DURATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        NewsResultReceiver resultReceiver = new NewsResultReceiver(new Handler());
        resultReceiver.setReceiver(this);
        if (isFirstAppLaunch()){
            Intent intent = new Intent(getApplicationContext(), UpdateNewsService.class);
            intent.putExtra(UpdateNewsService.EXTRA_RESULT_RECEIVER, resultReceiver);
            startService(intent);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startNewsListActivity();
                }
            }, SPLASH_SCREEN_DURATION);
        }
    }

    private boolean isFirstAppLaunch(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        return prefs.getBoolean(PREF_KEY_IS_FIRST_LAUNCH, true);
    }


    @Override
    public void onNewsUpdated() {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putBoolean(PREF_KEY_IS_FIRST_LAUNCH, false);
        editor.commit();
        UpdateNewsService.startPeriodicallyUpdate(getApplicationContext());
        startNewsListActivity();
    }

    @Override
    public void onUpdateError() {
        Toast.makeText(getApplicationContext(),
                getString(R.string.news_update_error), Toast.LENGTH_SHORT).show();
        NewsApplication app = (NewsApplication)getApplicationContext();
        app.releaseDataBaseHelper();
    }

    private void startNewsListActivity(){
        Intent intent = new Intent(SplashScreenActivity.this, NewsListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
