package alphanews.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by Zatsepin on 17.08.2014.
 */
public class NewsResultReceiver extends ResultReceiver {

    private Receiver mReceiver;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public NewsResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver){
        mReceiver = receiver;
    }

    public interface Receiver{
        void onNewsUpdated();
        void onUpdateError();
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null){
            if (resultCode == Activity.RESULT_OK){
                mReceiver.onNewsUpdated();
            }else{
                mReceiver.onUpdateError();
            }
        }
    }
}
