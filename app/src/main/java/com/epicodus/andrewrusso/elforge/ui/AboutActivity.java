package com.epicodus.andrewrusso.elforge.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.graphics.Typeface;

import com.epicodus.andrewrusso.elforge.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.aboutTextView) TextView mAboutTextView;
    @Bind(R.id.infoTextView) TextView mInfoTextView;
    @Bind(R.id.github) TextView mGitHub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/OstrichSansInline-Regular.otf");
        mAboutTextView.setTypeface(ostrichFont);

        Typeface oswald = Typeface.createFromAsset(getAssets(), "fonts/Oswald-Regular.ttf");
        mInfoTextView.setTypeface(oswald);

        mGitHub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mGitHub) {
            Uri uri = Uri.parse("https://github.com/russspruce/elforge");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            finish();
        }


    }
}
