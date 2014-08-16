package alphanews.newsapp.engine;

import java.util.List;

import alphanews.newsapp.domain.News;

/**
 * Created by Zatsepin on 14.08.2014.
 */
public interface Parser {
    List<News> parse(String rawData) throws Exception;
}
