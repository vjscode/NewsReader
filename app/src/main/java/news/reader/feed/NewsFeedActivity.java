package news.reader.feed;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import news.reader.BaseActivity;
import news.reader.NewsFeedActivityModule;
import news.reader.NewsFeedApplication;
import news.reader.R;
import news.reader.model.News;

public class NewsFeedActivity extends BaseActivity implements NewsFeedView {

    private static final String TAG = NewsFeedActivity.class.getSimpleName();
    @BindView(R.id.newsReaderList)
    public RecyclerView newsFeedList;

    private Unbinder unbinder;

    @Inject
    NewsFeedAdapter newsFeedAdapter;
    private NewsFeedPresenter newsFeedPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feed);
        unbinder = ButterKnife.bind(this);
        newsFeedList.setLayoutManager(new LinearLayoutManager(this));
        newsFeedList.setAdapter(newsFeedAdapter);
        setUpPresenter();
    }

    private void setUpPresenter() {
        Object lastConfig = getLastCustomNonConfigurationInstance();
        if (lastConfig != null) {
            SavedNewsFeed savedNewsFeed = (SavedNewsFeed) lastConfig;
            newsFeedPresenter = savedNewsFeed.newsFeedPresenter;
            newsFeedPresenter.display(this);

        } else {
            newsFeedPresenter = new NewsFeedPresenterImpl(this);
            newsFeedPresenter.fetchNewsAndDisplay();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onNewsFeedFetchComplete(List<News> news) {
        newsFeedAdapter.setNewsList(news);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        SavedNewsFeed savedNewsFeed = new SavedNewsFeed();
        savedNewsFeed.newsFeedPresenter = newsFeedPresenter;
        return savedNewsFeed;
    }

    @Override
    protected void setUpDependencies() {
        NewsFeedApplication.getAppComponent().build(new NewsFeedActivityModule(this)).inject(this);
    }
}
