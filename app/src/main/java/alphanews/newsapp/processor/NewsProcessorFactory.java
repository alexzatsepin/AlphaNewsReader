package alphanews.newsapp.processor;

import alphanews.newsapp.server.commands.AlphabankNewsCommand;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public class NewsProcessorFactory {

    public static NewsProcessor createAlphaBankProcessor(){
        return new NewsProcessor(new AlphabankNewsCommand(), new AlphabankParser());
    }
}
