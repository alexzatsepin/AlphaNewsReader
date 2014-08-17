package alphanews.newsapp.domain.alpha;

import android.provider.ContactsContract;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Zatsepin on 14.08.2014.
 */
@DatabaseTable(tableName = "news")
@Root(name = "item")
public class News {

    @Element
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String title;
    @Element
    @DatabaseField(canBeNull = false, dataType = DataType.STRING, id = true)
    private String link;
    @Element
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String description;
    @Element
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String pubDate;
    @Element
    @DatabaseField(canBeNull = false, dataType = DataType.STRING)
    private String guid;

    public News() {
    }

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
