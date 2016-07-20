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
    public static void findGames(String gameListTextView, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GAME_BASE_URL).newBuilder();

        String url = "http://www.giantbomb.com/api/search/?api_key=\"+GAME_DB_API_KEY+ \"&format=json&query=\"+GAME_NAME_TITLE+\"&resources=game";
        Log.d("url", url);

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
