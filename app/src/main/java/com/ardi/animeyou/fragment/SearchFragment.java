package com.ardi.animeyou.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.ardi.animeyou.R;
import com.ardi.animeyou.activity.MainActivity;
import com.ardi.animeyou.adapter.AnimeAdapter;
import com.ardi.animeyou.adapter.SearchAdapter;
import com.ardi.animeyou.entity.Anime;
import com.ardi.animeyou.utils.AnimeAsyncTaskLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<Anime>>{
    @BindView(R.id.lv_movie)
    ListView lvMovie;
    @BindView(R.id.edt_movie)
    EditText edtTitle;
    @Nullable
//    @BindView(R.id.image_detail)
//    ImageView imgMovie;
    @BindView(R.id.btn_find)
    FancyButton btnFindMovie;
    SearchAdapter adapter;
    private View view;
    private Anime film;
    private Context context;
    static final String EXTRAS_MOVIE = "data";

    public SearchFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);


        adapter = new SearchAdapter(getActivity());
        adapter.notifyDataSetChanged();
        lvMovie.setAdapter(adapter);
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {
                Anime items = (Anime) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("anime", items);


                getActivity().startActivity(intent);
            }
        });

        btnFindMovie.setOnClickListener(movieListener);

        String judul_film = edtTitle.getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_MOVIE, judul_film);
        LoaderManager.getInstance(this).initLoader(0, bundle, SearchFragment.this);

        return view;
    }

    @NonNull
    @Override
    public Loader<ArrayList<Anime>> onCreateLoader(int i, @Nullable Bundle bundle) {
        String movieJudul = "";
        if (bundle != null) {
            movieJudul = bundle.getString(EXTRAS_MOVIE);
        }
        return new AnimeAsyncTaskLoader(getActivity(), movieJudul);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Anime>> loader, ArrayList<Anime> anime) {
        adapter.setData(anime);
    }

    @Override
    public void onLoaderReset( Loader<ArrayList<Anime>> loader) {
        adapter.setData(null);
    }

    final View.OnClickListener movieListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String title_film = edtTitle.getText().toString();
            if (TextUtils.isEmpty(title_film)) {
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE, title_film);
            getLoaderManager().restartLoader(0, bundle, SearchFragment.this);
        }
    };
}
