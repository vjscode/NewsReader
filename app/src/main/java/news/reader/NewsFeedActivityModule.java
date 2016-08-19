package news.reader;

import dagger.Module;
import dagger.Provides;
import news.reader.feed.NewsFeedActivity;
import news.reader.feed.NewsFeedAdapter;
import news.reader.feed.NewsFeedPresenter;
import news.reader.feed.NewsFeedPresenterImpl;

/**
 * Created by vijay on 8/18/16.
 */
@Module
public class NewsFeedActivityModule {
    NewsFeedActivity newsFeedActivity;

    public NewsFeedActivityModule(NewsFeedActivity newsFeedActivity) {
        this.newsFeedActivity = newsFeedActivity;
    }

    @Provides
    @ActivityScope
    NewsFeedAdapter providesNewsFeedAdapter() {
        return new NewsFeedAdapter(newsFeedActivity);
    }

    @Provides
    @ActivityScope
    NewsFeedPresenter providesNewsFeedPresenterImpl() {
        return new NewsFeedPresenterImpl(newsFeedActivity);
    }
}
