package com.epicodus.andrewrusso.elforge.services;

/**
 * Created by andrewrusso on 7/17/16.
// */


import android.util.Log;

import com.epicodus.andrewrusso.elforge.Constants;

import okhttp3.Call;
        import okhttp3.Callback;
        import okhttp3.HttpUrl;
        import okhttp3.OkHttpClient;
        import okhttp3.Request;
        import okhttp3.Response;


public class GameService {
    public static void findGames(String query, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GAME_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.FORMAT, Constants.FORMAT_TYPE)
                .addQueryParameter(Constants.API_KEY_QUERY, Constants.GAME_DB_API_KEY)
                .addQueryParameter(Constants.FIELD_LIST, Constants.FIND_GAMES_FIELD_LIST)
                .addQueryParameter(Constants.LIMIT, "20")
                .addQueryParameter(Constants.QUERY_PARAMETER, query);
        String url = urlBuilder.build().toString();

        Log.d("url", url);

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
