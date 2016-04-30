package news.reader.feed;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import news.reader.NewsFeedApplication;
import news.reader.R;
import news.reader.model.News;

/**
 * Created by vijay on 4/27/16.
 */
public class NewsFeedAdapter extends RecyclerView.Adapter<NewsCardViewHolder> {

    @Inject
    public Context mContext;
    private List<News> newsList = new ArrayList<>();

    public NewsFeedAdapter() {
        NewsFeedApplication.getAppComponent().inject(this);
    }

    @Override
    public NewsCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newscard = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feed_card, parent, false);
        NewsCardViewHolder holder = new NewsCardViewHolder(newscard);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewsCardViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.txtAbstractContent.setText(news.getAbstractContent());
        if (news.getMediaList() != null && news.getMediaList().size() > 0) {
            int size = news.getMediaList().size();
            int index = size > 0 ? size - 1 : 0;
            loadImage(news.getMediaList().get(index).getImgUrl(), holder.imgNews);
        }
    }

    private void loadImage(String url, ImageView view) {
        Picasso.with(mContext)
                .load(url)
                .noPlaceholder()
                .into(view);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void setNewsList(List<News> list) {
        this.newsList = list;
        notifyDataSetChanged();
    }
}
