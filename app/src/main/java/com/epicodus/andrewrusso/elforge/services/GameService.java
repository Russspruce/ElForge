package com.epicodus.andrewrusso.elforge.services;

/**
 * Created by andrewrusso on 7/17/16.
// */


import android.util.Log;

import com.epicodus.andrewrusso.elforge.Constants;
import com.epicodus.andrewrusso.elforge.models.Game;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<Game> gameResults(Response response) {
        ArrayList<Game> games = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject gameDBJSON = new JSONObject(jsonData);
                JSONArray gamesJSON = gameDBJSON.getJSONArray("games");
                for (int x = 0; x < gamesJSON.length(); x++) {
                    JSONObject gameJSON = gamesJSON.getJSONObject(x);
                    String name = gameJSON.getString("name");
                    String deck = gameJSON.getString("deck");
                    String id = gameJSON.getString("id");
                    String imageUrl = gameJSON.getString("imageUrl");

                    Game game = new Game(name, deck, id, imageUrl);

                    games.add(game);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return games;


    }
}



