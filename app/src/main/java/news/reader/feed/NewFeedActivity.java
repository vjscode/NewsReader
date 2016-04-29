package news.reader.feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import news.reader.R;
import news.reader.model.News;
import news.reader.networking.RestManager;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewFeedActivity extends AppCompatActivity {

    private static final String TAG = NewFeedActivity.class.getSimpleName();
    @BindView(R.id.newsReaderList)
    public RecyclerView newsFeedList;

    private Unbinder unbinder;
    private Subscription newsFeedSubscription;

    private NewsFeedAdapter newsFeedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feed);
        unbinder = ButterKnife.bind(this);
        newsFeedAdapter = new NewsFeedAdapter(this);
        newsFeedList.setLayoutManager(new LinearLayoutManager(this));
        newsFeedList.setAdapter(newsFeedAdapter);
        populateNewsFeed();
    }

    private void populateNewsFeed() {
        newsFeedSubscription = RestManager.getMostPopularNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<News>>() {
                               @Override
                               public void onCompleted() {
                                   Log.d(TAG, "completed");
                               }

                               @Override
                               public void onError(Throwable e) {
                                    Log.d(TAG, "e: " + e);
                               }

                               @Override
                               public void onNext(List<News> news) {
                                   newsFeedAdapter.setNewsList(news);
                               }
                           }
                );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        newsFeedSubscription.unsubscribe();
    }
}
