package news.reader;

import dagger.Module;
import dagger.Provides;
import news.reader.feed.NewFeedActivity;
import news.reader.feed.NewsFeedAdapter;
import news.reader.feed.NewsFeedPresenter;
import news.reader.feed.NewsFeedPresenterImpl;

/**
 * Created by vijay on 8/18/16.
 */
@Module
public class NewsFeedActivityModule {
    NewFeedActivity newFeedActivity;

    public NewsFeedActivityModule(NewFeedActivity newFeedActivity) {
        this.newFeedActivity = newFeedActivity;
    }

    @Provides
    @ActivityScope
    NewsFeedAdapter providesNewsFeedAdapter() {
        return new NewsFeedAdapter(newFeedActivity);
    }

    @Provides
    @ActivityScope
    NewsFeedPresenter providesNewsFeedPresenterImpl() {
        return new NewsFeedPresenterImpl(newFeedActivity);
    }
}
