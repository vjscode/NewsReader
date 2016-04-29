package news.reader.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import news.reader.model.News;
import news.reader.model.NewsTypeAdapter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by vijay on 4/28/16.
 */
public class RestManager {

    private static String MOST_POPULAR_URL = "http://api.nytimes.com/";

    public interface NYTAPI {
        @GET("svc/mostpopular/v2/mostviewed/all-sections/1.json?api-key=22308b33eba4848a9661a80a4ab1f183%3A2%3A75138777")
        Observable<List<News>> getMostPopularNews();
    }

    public static Observable<List<News>> getMostPopularNews() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new LoggingInterceptor())
                .build();

        Type newsClassListType = new TypeToken<List<News>>() {}.getType();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(newsClassListType, new NewsTypeAdapter())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOST_POPULAR_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        NYTAPI service = retrofit.create(NYTAPI.class);
        Observable<List<News>> news = service.getMostPopularNews();
        return news;
    }
}
