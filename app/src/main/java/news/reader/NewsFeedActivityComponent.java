package news.reader;

import dagger.Subcomponent;
import news.reader.feed.NewFeedActivity;
import news.reader.feed.NewsFeedAdapter;

/**
 * Created by vijay on 8/18/16.
 */
@ActivityScope
@Subcomponent(
        modules = {NewsFeedActivityModule.class}
)
public interface NewsFeedActivityComponent {
    void inject(NewFeedActivity activity);
    void inject(NewsFeedAdapter adapter);
}
