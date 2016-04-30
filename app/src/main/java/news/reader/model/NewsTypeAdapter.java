package news.reader.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijay on 4/28/16.
 */
public class NewsTypeAdapter extends TypeAdapter<List<News>> {

    public NewsTypeAdapter() {
        super();
    }

    @Override
    public void write(JsonWriter out, List<News> value) throws IOException {

    }

    @Override
    public List<News> read(JsonReader in) throws IOException {
        in.beginObject();
        while(in.hasNext()) {
            String key = in.nextName();
            if (key.equals("results") && in.peek() != JsonToken.NULL) {
                return readResults(in);
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return null;
    }

    private List<News> readResults(JsonReader in) throws IOException {
        in.beginArray();
        List<News> newsList = new ArrayList<>();
        while (in.hasNext()) {
            newsList.add(readNews(in));
        }
        in.endArray();
        return newsList;
    }

    private News readNews(JsonReader in) throws IOException {
        in.beginObject();
        News news = new News();
        while (in.hasNext()) {
            String key = in.nextName();
            if (key.equals("url")) {
                String url = in.nextString();
                news.setUrl(url);
            } else if (key.equals("title")) {
                news.setTitle(in.nextString());
            } else if (key.equals("abstract")) {
                news.setAbstractContent(in.nextString());
            } else if (key.equals("media") && in.peek() == JsonToken.BEGIN_ARRAY) {
                //parse media
                readMediaList(news, in);
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return news;
    }

    private void readMediaList(News news, JsonReader in) throws IOException {
        in.beginArray();
        while (in.hasNext()) {
            news.setMediaList(readMedia(in));
        }
        in.endArray();
    }

    private List<NewsMedia> readMedia(JsonReader in) throws IOException {
        in.beginObject();
        List<NewsMedia> mediaList = null;
        while (in.hasNext()) {
            String key = in.nextName();
            if (key.equals("media-metadata")) {
                //media.setImgUrl(in.nextString());
                mediaList = readMetaMediaList(in);
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return mediaList;
    }

    private List<NewsMedia> readMetaMediaList(JsonReader in) throws IOException {
        in.beginArray();
        List<NewsMedia> mediaList = new ArrayList<>();
        while (in.hasNext()) {
            mediaList.add(readMetaMedia(in));
        }
        in.endArray();
        return mediaList;
    }
    private NewsMedia readMetaMedia(JsonReader in) throws IOException {
        in.beginObject();
        NewsMedia media = new NewsMedia();
        while (in.hasNext()) {
            String key = in.nextName();
            if (key.equals("url")) {
                media.setImgUrl(in.nextString());
            } else {
                in.skipValue();
            }
        }
        in.endObject();
        return media;
    }
}
