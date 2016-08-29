package com.epicodus.andrewrusso.elforge.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.andrewrusso.elforge.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Bind(R.id.appTitleTextView) TextView mAppTitleTextView;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.queuedButton) Button mQueuedButton;
    @Bind(R.id.logout) Button mLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/OstrichSansInline-Regular.otf");
        mAppTitleTextView.setTypeface(ostrichFont);


        Typeface oswald = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Regular.ttf");
        mQueuedButton.setTypeface(oswald);
        mAboutButton.setTypeface(oswald);
        mSearchButton.setTypeface(oswald);




        mSearchButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
        mQueuedButton.setOnClickListener(this);
        mLogout.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_friends) {
            Intent intent = new Intent(MainActivity.this, Test.class);
            startActivity(intent);
        }

        if (id == R.id.action_queue) {
            Intent intent = new Intent(MainActivity.this, QueuedGameListActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View v) {
//        if (v == mSearchButton) {
//
//            Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
//            startActivity(intent);
//        }
//
//        if (v == mAboutButton) {
//
//            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
//            startActivity(intent);
//        }
//
//        if (v == mQueuedButton) {
//                Intent intent = new Intent(MainActivity.this, QueuedGameListActivity.class);
//                startActivity(intent);
//        }
        if (v == mLogout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }


}
