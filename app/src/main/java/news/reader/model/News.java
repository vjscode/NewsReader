package news.reader.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vijay on 4/28/16.
 */
public class News {
    @SerializedName("url")
    String url;

    @SerializedName("title")
    String title;

    @SerializedName("abstract")
    String abstractContent;

    @SerializedName("media-metadata")
    List<NewsMedia> mediaList;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public List<NewsMedia> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<NewsMedia> mediaList) {
        this.mediaList = mediaList;
    }
}
