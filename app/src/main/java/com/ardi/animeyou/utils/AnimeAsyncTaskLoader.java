package com.ardi.animeyou.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.ardi.animeyou.entity.Anime;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AnimeAsyncTaskLoader extends AsyncTaskLoader<ArrayList<Anime>> {

    private ArrayList<Anime> mData;
    private boolean mHasResult = false;

    private String mJudulFilm;

    public AnimeAsyncTaskLoader(final Context context, String JudulFilm) {
        super(context);

        onContentChanged();
        this.mJudulFilm = JudulFilm;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (mHasResult)
            deliverResult(mData);
    }

    public void deliverResult(ArrayList<Anime> data) {
        mData = data;
        mHasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (mHasResult) {
            onReleaseResources(mData);
            mData = null;
            mHasResult = false;
        }
    }

    private void onReleaseResources(ArrayList<Anime> data) {
    }

    @Nullable
    @Override
    public ArrayList<Anime> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<Anime> movieItemses = new ArrayList<>();

        String url = "https://animeyou.net/api/home.php" + mJudulFilm;

        client.get(url, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("data");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject movie = list.getJSONObject(i);
                        Anime movieItems = new Anime(movie);
                        movieItemses.add(movieItems);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return movieItemses;
    }
}
