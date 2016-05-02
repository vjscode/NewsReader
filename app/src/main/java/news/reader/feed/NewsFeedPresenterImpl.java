package news.reader.feed;

import android.util.Log;

import java.util.List;

import news.reader.model.News;
import news.reader.networking.RestManager;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vijay on 5/2/16.
 */
public class NewsFeedPresenterImpl implements NewsFeedPresenter {
    private static final String TAG = "NewsFeedPresenter";
    private NewsFeedView newsFeedView;
    private Subscription newsFeedSubscription;

    public NewsFeedPresenterImpl(NewsFeedView newsFeedView) {
        this.newsFeedView = newsFeedView;
    }

    @Override
    public void fetchNewsAndDisplay() {

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
                                   newsFeedView.onNewsFeedFetchComplete(news);
                               }
                           }
                );
    }

    @Override
    public void onDestroy() {
        if (!newsFeedSubscription.isUnsubscribed()) {
            newsFeedSubscription.unsubscribe();
        }
    }
}
