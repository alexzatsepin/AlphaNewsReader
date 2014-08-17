package alphanews.newsapp.engine;

import java.util.List;

import alphanews.newsapp.domain.alpha.News;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public class NewsEngine {

    private final Command mCommand;
    private final Parser mParser;
    private final NewsDao mDao;

    public NewsEngine(NewsFactory factory) {
        mCommand = factory.createNewsCommand();
        mParser = factory.createNewsParser();
        mDao = factory.createNewsDao();
    }

    public void updateNews() throws Exception {
        String response = mCommand.execute();
        List<News> news = mParser.parse(response);
        mDao.saveNews(news);
    }

    public List<News> getCachedNews(){
        return mDao.getAllNews();
    }
}
