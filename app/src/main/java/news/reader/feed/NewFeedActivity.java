package news.reader.feed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import news.reader.R;
import news.reader.model.News;

public class NewFeedActivity extends AppCompatActivity implements NewsFeedView {

    private static final String TAG = NewFeedActivity.class.getSimpleName();
    @BindView(R.id.newsReaderList)
    public RecyclerView newsFeedList;

    private Unbinder unbinder;

    private NewsFeedAdapter newsFeedAdapter;
    private NewsFeedPresenter newsFeedPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_feed);
        unbinder = ButterKnife.bind(this);
        newsFeedAdapter = new NewsFeedAdapter();
        newsFeedList.setLayoutManager(new LinearLayoutManager(this));
        newsFeedList.setAdapter(newsFeedAdapter);
        setUpPresenter();
    }

    private void setUpPresenter() {
        newsFeedPresenter = new NewsFeedPresenterImpl(this);
        newsFeedPresenter.fetchNewsAndDisplay();
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
}
