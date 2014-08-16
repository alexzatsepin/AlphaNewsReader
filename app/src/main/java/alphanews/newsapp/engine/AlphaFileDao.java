package alphanews.newsapp.engine;

import android.content.Context;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import alphanews.newsapp.domain.Channel;
import alphanews.newsapp.domain.News;

/**
 * Created by Zatsepin on 16.08.2014.
 */
public class AlphaFileDao extends BaseNewsDao {

    private static final String CACHE_FILE_NAME = "alpha_news.xml";

    AlphaFileDao(Context mContext) {
        super(mContext);
    }

    @Override
    public List<News> getAllNews() {
        FileInputStream fis = null;
        try{
            fis = getContext().openFileInput(CACHE_FILE_NAME);
            Serializer serializer = new Persister();
            Channel channel = serializer.read(Channel.class, fis);
            if (channel != null){
                return channel.getNews();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void saveNews(List<News> news) {
        FileOutputStream fos = null;
        try {
            fos = getContext().openFileOutput(CACHE_FILE_NAME, Context.MODE_PRIVATE);
            Serializer serializer = new Persister();
            Channel channel = new Channel();
            channel.setNews(news);
            serializer.write(channel, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
