package com.ardi.animeyou.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.ardi.animeyou.adapter.JadwalAdapter;
import com.ardi.animeyou.entity.Anime;
import com.ardi.animeyou.entity.Jadwal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalFragment extends Fragment {
    private RecyclerView rvJadwal;
    private RecyclerView.Adapter adapter;
    private ArrayList<Jadwal> listJadwal;

    private static final String URL = "https://animeyou.net/api/jadwal.php";

    public JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jadwal, container, false);
        rvJadwal = view.findViewById(R.id.rv_jadwal);
        rvJadwal.setHasFixedSize(true);
        rvJadwal.setLayoutManager(new LinearLayoutManager(getActivity()));
        listJadwal = new ArrayList<>();
        loadUrlData();
        return view;
    }

    private void loadUrlData(){
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
                        Jadwal jadwal = new Jadwal();

                        JSONObject data = array.getJSONObject(i);
                        jadwal.setId(data.getString("id"));
                        jadwal.setJudul(data.getString("judul"));
                        jadwal.setHari(data.getString("hari"));
                        jadwal.setHalaman(data.getString("halaman"));
                        jadwal.setGambar(data.getString("gambar"));
                        listJadwal.add(jadwal);
                    }
                    adapter = new JadwalAdapter(listJadwal, getActivity());
                    rvJadwal.setAdapter(adapter);

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
