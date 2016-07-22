package com.epicodus.andrewrusso.elforge.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.epicodus.andrewrusso.elforge.R;
import com.epicodus.andrewrusso.elforge.models.Game;
import com.epicodus.andrewrusso.elforge.services.GameService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity {
    public static final String TAG = SearchResultsActivity.class.getSimpleName();

    public ArrayList<Game> mGames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String searchParam = intent.getStringExtra("searchParam");
        getGames(searchParam);
    }

    private void getGames(String searchParam) {
        final GameService gameService = new GameService();
        gameService.findGames(searchParam, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        });


    }
}
