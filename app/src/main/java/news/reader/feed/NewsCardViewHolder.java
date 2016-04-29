package news.reader.feed;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import news.reader.R;

/**
 * Created by vijay on 4/27/16.
 */
public class NewsCardViewHolder extends RecyclerView.ViewHolder {
    public TextView txtAbstractContent;
    public ImageView imgNews;

    public NewsCardViewHolder(View itemView) {
        super(itemView);
        txtAbstractContent = (TextView) itemView.findViewById(R.id.cardText);
        imgNews = (ImageView) itemView.findViewById(R.id.cardImage);
    }
}
