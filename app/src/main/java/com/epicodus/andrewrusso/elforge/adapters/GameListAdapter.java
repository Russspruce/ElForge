package com.epicodus.andrewrusso.elforge.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.andrewrusso.elforge.R;
import com.epicodus.andrewrusso.elforge.models.Game;
import com.epicodus.andrewrusso.elforge.ui.GameDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by andrewrusso on 7/20/16.
 */
public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {
    private ArrayList<Game> mGames = new ArrayList<>();
    private Context mContext;

    public GameListAdapter(Context context, ArrayList<Game> games) {
        mContext = context;
        mGames = games;
    }

    @Override
    public GameListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        GameViewHolder viewHolder = new GameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameListAdapter.GameViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.gameImage) ImageView mGameImage;
        @Bind(R.id.gameTextView) TextView mGameTextView;

        private Context mContext;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, GameDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("games", Parcels.wrap(mGames));
            mContext.startActivity(intent);

        }

        public void bindGame(Game game) {
            Picasso.with(mContext).load(game.getImageUrl()).into(mGameImage);
            mGameTextView.setText(game.getName());
        }

    }
}
