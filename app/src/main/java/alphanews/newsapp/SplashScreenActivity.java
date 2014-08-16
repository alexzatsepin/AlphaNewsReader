package alphanews.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.prefs.Preferences;


public class SplashScreenActivity extends Activity {

    private static final String PREF_KEY_IS_FIRST_LAUNCH = "pref_key_is_first_launch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (isFirstAppLaunch()){
            UpdateNewsService.startUpdate(getApplicationContext());
            SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
            editor.putBoolean(PREF_KEY_IS_FIRST_LAUNCH, false);
            editor.commit();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, NewsListActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    private boolean isFirstAppLaunch(){
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        return prefs.getBoolean(PREF_KEY_IS_FIRST_LAUNCH, true);
    }
}
