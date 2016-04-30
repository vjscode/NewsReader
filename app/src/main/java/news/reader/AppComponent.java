package news.reader;

import javax.inject.Singleton;

import dagger.Component;
import news.reader.feed.NewFeedActivity;
import news.reader.feed.NewsFeedAdapter;

/**
 * Created by vijay on 4/29/16.
 */
@Singleton
@Component(modules={ApplicationModule.class})
public interface AppComponent {
    void inject(NewFeedActivity activity);
    void inject(NewsFeedAdapter adapter);
}
