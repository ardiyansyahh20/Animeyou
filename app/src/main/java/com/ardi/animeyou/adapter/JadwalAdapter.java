package com.ardi.animeyou.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardi.animeyou.R;
import com.ardi.animeyou.entity.Jadwal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {

    private ArrayList<Jadwal> listJadwal;
    private Context context;

    public JadwalAdapter(ArrayList<Jadwal> jadwals, Context context) {
        this.listJadwal = jadwals;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_jadwal, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Jadwal jadwal = listJadwal.get(position);
        holder.tvHari.setText(R.string.hari);
        holder.tvJudul.setText(R.string.judul);
        holder.tvHariAnime.setText(jadwal.getHari());
        holder.tvJudulAnime.setText(jadwal.getJudul());
        Picasso.get().load(jadwal.getGambar()).into(holder.imgAnime);
    }

    @Override
    public int getItemCount() {
        if (listJadwal == null) return 0;
        return listJadwal.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_judul)
        TextView tvJudul;
        @BindView(R.id.tv_judul_anime)
        TextView tvJudulAnime;
        @BindView(R.id.gambar_anime)
        ImageView imgAnime;
        @BindView(R.id.tv_hari)
        TextView tvHari;
        @BindView(R.id.tv_hari_anime)
        TextView tvHariAnime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
