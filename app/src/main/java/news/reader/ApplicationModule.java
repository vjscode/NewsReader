package news.reader;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vijay on 4/29/16.
 */
@Module
public class ApplicationModule {

    Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context providesApplication() {
        return mApplication;
    }
}
