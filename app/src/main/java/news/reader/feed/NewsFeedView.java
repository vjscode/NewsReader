package news.reader.feed;

import java.util.List;

import news.reader.model.News;

/**
 * Created by vijay on 5/2/16.
 */
public interface NewsFeedView {
    void onNewsFeedFetchComplete(List<News> news);
}
