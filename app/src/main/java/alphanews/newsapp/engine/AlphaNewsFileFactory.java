package alphanews.newsapp.engine;

import android.content.Context;

import alphanews.newsapp.server.commands.AlphabankNewsCommand;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public class AlphaNewsFileFactory implements NewsFactory{

    private final Context mContext;

    public AlphaNewsFileFactory(Context context) {
        mContext = context;
    }

    @Override
    public NewsEngine createNewsProcessor() {
        return new NewsEngine(new AlphabankNewsCommand(), new AlphaSimpleXmlParser(), createNewsDao());
    }

    @Override
    public NewsDao createNewsDao() {
        return new AlphaFileDao(mContext);
    }
}
