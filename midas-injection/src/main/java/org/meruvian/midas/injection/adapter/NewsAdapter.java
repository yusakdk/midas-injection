package org.meruvian.midas.injection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.meruvian.midas.injection.R;
import org.meruvian.midas.injection.repository.db.News;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by merv on 6/3/15.
 */
public class NewsAdapter extends BaseAdapter {
    private List<News> newses = new ArrayList<News>();
    private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    private Context context;

    private LayoutInflater inflater;


    public NewsAdapter(Context context, List<News> newses){
        this.context = context;
        this.newses = newses;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return newses.size();
    }

    @Override
    public Object getItem(int position) {
        return newses.get(position);
    }

    @Override
    public long getItemId(int id) {
        return 0;
    }

    public void addNews(List<News> categories){
        this.newses.addAll(categories);
        notifyDataSetChanged();
    }

    public void clear(){
        newses.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.adapter_news, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.name.setText(newses.get(position).getTitle());
        holder.description.setText(newses.get(position).getContent());
        holder.date.setText(dateFormat.format(new Date(newses.get(position).getDbCreateDate().getTime())));

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.text_title)
        TextView name;
        @Bind(R.id.text_content)
        TextView description;
        @Bind(R.id.text_date)
        TextView date;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
