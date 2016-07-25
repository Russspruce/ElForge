package com.epicodus.andrewrusso.elforge.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.andrewrusso.elforge.Constants;
import com.epicodus.andrewrusso.elforge.R;
import com.epicodus.andrewrusso.elforge.models.Game;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameDetailFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.gameImage) ImageView mImageLabel;
    @Bind(R.id.gameTextView) TextView mNameLabel;
    @Bind(R.id.gameQueueButton) Button mQueueButton;

    private Game mGame;

    public static GameDetailFragment newInstance(Game game) {
        GameDetailFragment gameDetailFragment = new GameDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("games", Parcels.wrap(game));
        gameDetailFragment.setArguments(args);
        return gameDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGame = Parcels.unwrap(getArguments().getParcelable("games"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mGame.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mGame.getName());

        mQueueButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mQueueButton) {
            DatabaseReference gameRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_GAMES);
            gameRef.push().setValue(mGame);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }

}
