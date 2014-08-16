package alphanews.newsapp.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Zatsepin on 16.08.2014.
 */
@Root(strict=false)
public class Rss {
    @Element
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }
}
