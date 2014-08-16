package alphanews.newsapp.engine;

import java.util.List;

import alphanews.newsapp.domain.News;
import alphanews.newsapp.server.commands.Command;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public class NewsEngine {

    private final Command mCommand;
    private final Parser mParser;
    private final NewsDao mDao;

    NewsEngine(Command mCommand, Parser mParser, NewsDao dao) {
        this.mCommand = mCommand;
        this.mParser = mParser;
        this.mDao = dao;
    }

    public void process(){
        try {
            String response = mCommand.execute();
            List<News> news = mParser.parse(response);
            mDao.saveNews(news);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
