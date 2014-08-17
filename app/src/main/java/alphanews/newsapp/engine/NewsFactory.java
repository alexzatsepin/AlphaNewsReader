package alphanews.newsapp.engine;

/**
 * Created by Zatsepin on 16.08.2014.
 */
public interface NewsFactory {
    NewsEngine createNewsEngine();
    NewsDao createNewsDao();
    Command createNewsCommand();
    Parser createNewsParser();
}
