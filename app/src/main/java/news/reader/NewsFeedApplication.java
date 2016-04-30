package news.reader;

import android.app.Application;
import android.util.Log;

/**
 * Created by vijay on 4/29/16.
 */
public class NewsFeedApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
    }

    public static AppComponent getAppComponent() {
        Log.d("test", "appComponent: " + appComponent);
        return appComponent;
    }
}
