package alphanews.newsapp.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Zatsepin on 14.08.2014.
 */
@Root(name = "item")
public class News {
    @Element
    private String title;
    @Element
    private String link;
    @Element
    private String description;
    @Element
    private String pubDate;
    @Element
    private String guid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String mLink) {
        this.link = mLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        this.description = mDescription;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String mPubDate) {
        this.pubDate = mPubDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String mGuid) {
        this.guid = mGuid;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
