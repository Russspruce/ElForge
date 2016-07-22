package com.epicodus.andrewrusso.elforge.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.andrewrusso.elforge.R;
import com.epicodus.andrewrusso.elforge.models.Game;
import com.epicodus.andrewrusso.elforge.services.GameService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchResultsActivity extends AppCompatActivity {
    public static final String TAG = SearchResultsActivity.class.getSimpleName();

    @Bind(R.id.listView) ListView mListView;

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
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mGames = gameService.gameResults(response);

                SearchResultsActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        String[] gameTitles = new String[mGames.size()];
                        for (int x = 0; x < gameTitles.length; x++) {
                            gameTitles[x] = mGames.get(x).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(SearchResultsActivity.this,
                                android.R.layout.simple_list_item_1, gameTitles);
                        mListView.setAdapter(adapter);

                        for (Game game : mGames) {
                            Log.d(TAG, "Name: " + game.getName());
                            Log.d(TAG, "Image: " + game.getImage());
                            Log.d(TAG, "ID: " + game.getId());


                        }

                    }


                });


            }
        });
    }
}
