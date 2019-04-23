package com.ardi.animeyou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardi.animeyou.R;
import com.ardi.animeyou.activity.DetailAnimeActivity;
import com.ardi.animeyou.entity.Anime;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {

    private ArrayList<Anime> listAnime;
    private Context context;

    public AnimeAdapter(ArrayList<Anime> anime, Context context) {
        this.listAnime = anime;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_anime, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Anime animex = listAnime.get(position);
        holder.tvJudul.setText(animex.getJudul());
        holder.tvRilis.setText(animex.getDate());
        holder.genre.setText(animex.getGanre());
        Picasso.get().load(animex.getImg()).into(holder.imgAnime);

        holder.imgAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Anime anime = listAnime.get(position);
                Intent intent = new Intent(context, DetailAnimeActivity.class);
                intent.putExtra("anime", anime);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listAnime == null) return 0;
        return listAnime.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_rilis)
        TextView tvRilis;
        @BindView(R.id.gambar_anime)
        ImageView imgAnime;
        @BindView(R.id.tv_genre)
        TextView genre;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
