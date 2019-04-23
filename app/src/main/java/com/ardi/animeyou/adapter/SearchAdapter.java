package com.ardi.animeyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardi.animeyou.R;
import com.ardi.animeyou.entity.Anime;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends BaseAdapter {
    private ArrayList<Anime> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<Anime> items) {
        mData = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }

    @Override
    public Anime getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row_anime, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(mData.get(position).getJudul());
        holder.tvGenre.setText(mData.get(position).getGanre());
        holder.tvDate.setText(mData.get(position).getDate());
        Picasso.get().load(mData.get(position).getImg()).into(holder.imgMovie);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_genre_row)
        TextView tvGenre;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.gambar)
        ImageView imgMovie;
        @BindView(R.id.tv_date)
        TextView tvDate;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
