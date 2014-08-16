package alphanews.newsapp.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Zatsepin on 16.08.2014.
 */
@Root(strict = false)
public class Channel {
    @Element(required = false)
    private String title;
    @Element(required = false)
    private String link;
    @Element(required = false)
    private String description;
    @Element(required = false)
    private String language;
    @ElementList(inline = true)
    private List<News> news;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news){
        this.news = news;
    }
}
