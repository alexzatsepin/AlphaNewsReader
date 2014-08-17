package alphanews.newsapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import alphanews.newsapp.domain.alpha.News;

/**
 * Created by Zatsepin on 16.08.2014.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private final int mResourceId;

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        mResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(mResourceId, parent, false);

            holder = new NewsViewHolder();
            holder.mTitle = (TextView) convertView.findViewById(R.id.title);
            holder.mSnippet = (TextView) convertView.findViewById(R.id.snippet);
            convertView.setTag(holder);
        } else {
            holder = (NewsViewHolder)convertView.getTag();
        }

        News news = getItem(position);
        holder.mTitle.setText(news.getTitle());
        holder.mSnippet.setText(news.getGuid());

        return convertView;
    }

    static class NewsViewHolder{
        TextView mTitle;
        TextView mSnippet;
    }

}
