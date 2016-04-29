package news.reader.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vijay on 4/28/16.
 */
public class NewsMedia {
    @SerializedName("url")
    String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
