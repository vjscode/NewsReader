package news.reader;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vijay on 4/29/16.
 */
@Singleton
@Component(modules={ApplicationModule.class})
public interface AppComponent {
    NewsFeedActivityComponent build(NewsFeedActivityModule module);
}
