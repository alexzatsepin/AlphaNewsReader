package alphanews.newsapp.engine;

import java.util.List;

import alphanews.newsapp.domain.News;

/**
 * Created by Zatsepin on 16.08.2014.
 */
public interface NewsDao {
    List<News> getAllNews();
    void saveNews(List<News> news);
    News getNewsByLink(String link);
}
