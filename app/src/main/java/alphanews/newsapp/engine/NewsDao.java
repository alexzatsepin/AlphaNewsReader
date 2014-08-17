package alphanews.newsapp.engine;

import java.util.List;

import alphanews.newsapp.domain.alpha.News;

/**
 * Created by Zatsepin on 16.08.2014.
 */
public interface NewsDao {
    List<News> getAllNews();
    void saveNews(List<News> news);
}
