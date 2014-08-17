package alphanews.newsapp.engine.alpha;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import alphanews.newsapp.domain.alpha.News;
import alphanews.newsapp.engine.NewsDao;

/**
 * Created by Zatsepin on 17.08.2014.
 */
public class AlphaDbDao extends BaseDaoImpl<News, String> implements NewsDao {

    protected AlphaDbDao(ConnectionSource connectionSource, Class<News> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    @Override
    public List<News> getAllNews() {
        List<News> result = null;
        try {
            result = (List<News>)queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void saveNews(List<News> news) {
        for(News item: news){
            try {
                createOrUpdate(item);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Cannot create ot update item = " + item, e);
            }
        }
    }
}
