package alphanews.newsapp.server.commands;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public class AlphabankNewsCommand extends ServerCommand {

    @Override
    String getUrl() {
        return "http://pda.alfabank.ru/_/rss/_rss.html";
    }
}
