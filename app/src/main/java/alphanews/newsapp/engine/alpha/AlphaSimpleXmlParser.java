package alphanews.newsapp.engine.alpha;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.util.List;

import alphanews.newsapp.domain.alpha.Channel;
import alphanews.newsapp.domain.alpha.News;
import alphanews.newsapp.domain.alpha.Rss;
import alphanews.newsapp.engine.Parser;

/**
 * Created by Zatsepin on 16.08.2014.
 */
class AlphaSimpleXmlParser implements Parser {

    @Override
    public List<News> parse(String rawData) throws Exception {
        Serializer serializer = new Persister();
        Rss rss = serializer.read(Rss.class, rawData);
        Channel channel = rss.getChannel();
        return channel.getNews();
    }

}
