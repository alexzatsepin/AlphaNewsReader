package alphanews.newsapp.engine;

import android.content.Context;

import alphanews.newsapp.server.commands.AlphabankNewsCommand;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public class AlphaNewsFactory implements NewsFactory{

    private final Context mContext;

    public AlphaNewsFactory(Context context) {
        mContext = context;
    }

    @Override
    public NewsEngine createNewsProcessor() {
        return new NewsEngine(new AlphabankNewsCommand(), new AlphaSimpleXmlParser(), new AlphaFileDao(mContext));
    }

    @Override
    public NewsDao createNewsDao() {
        return new AlphaFileDao(mContext);
    }
}
