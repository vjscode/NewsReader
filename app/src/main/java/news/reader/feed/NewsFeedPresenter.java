package news.reader.feed;

/**
 * Created by vijay on 5/2/16.
 */
public interface NewsFeedPresenter {
    void fetchNewsAndDisplay();
    void onDestroy();
}
