package com.ardi.animeyou.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ardi.animeyou.R;
import com.ardi.animeyou.adapter.AnimeAdapter;
import com.ardi.animeyou.entity.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvAnime;
    private RecyclerView.Adapter adapter;
    private View view;
    private ArrayList<Anime> animeList;

    private static final String URL = "https://animeyou.net/api/home.php";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rvAnime = view.findViewById(R.id.rv_list_anime);
        rvAnime.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAnime.setHasFixedSize(true);
        animeList = new ArrayList<>();
        loadUrlData();
        return view;
    }

    private void loadUrlData() {
//        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("data");
                    for (int i = 0; i < array.length(); i++) {
                        Anime anime = new Anime();

                        JSONObject data = array.getJSONObject(i);
                        anime.setId(data.getString("id"));
                        anime.setJudul(data.getString("judul"));
                        anime.setDate(data.getString("tanggal"));
                        anime.setGanre(data.getString("genre"));
                        anime.setImg(data.getString("gambar"));
                        animeList.add(anime);
                    }
                    adapter = new AnimeAdapter(animeList, getActivity());
                    rvAnime.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
                loadUrlData();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
