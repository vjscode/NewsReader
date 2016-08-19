package news.reader;

import dagger.Subcomponent;
import news.reader.feed.NewsFeedActivity;
import news.reader.feed.NewsFeedAdapter;

/**
 * Created by vijay on 8/18/16.
 */
@ActivityScope
@Subcomponent(
        modules = {NewsFeedActivityModule.class}
)
public interface NewsFeedActivityComponent {
    void inject(NewsFeedActivity activity);
    void inject(NewsFeedAdapter adapter);
}
