package alphanews.newsapp.engine.alpha;

import alphanews.newsapp.engine.ServerCommand;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public class AlphabankNewsCommand extends ServerCommand {

    @Override
    protected String getUrl() {
        return "http://pda.alfabank.ru/_/rss/_rss.html";
    }
}
