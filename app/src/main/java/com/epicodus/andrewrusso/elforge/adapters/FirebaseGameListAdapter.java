package com.epicodus.andrewrusso.elforge.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.andrewrusso.elforge.models.Game;
import com.epicodus.andrewrusso.elforge.ui.GameDetailActivity;
import com.epicodus.andrewrusso.elforge.ui.QueuedGameListActivity;
import com.epicodus.andrewrusso.elforge.util.ItemTouchHelperAdapter;
import com.epicodus.andrewrusso.elforge.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by andrewrusso on 7/26/16.
 */
public class FirebaseGameListAdapter extends FirebaseRecyclerAdapter<Game, FirebaseGameViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEventListener;
    private ArrayList<Game> mGames = new ArrayList<>();

    public FirebaseGameListAdapter(Class<Game> modelClass, int modelLayout,
                                   Class<FirebaseGameViewHolder> viewHolderClass,
                                   Query ref, QueuedGameListActivity onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mGames.add(dataSnapshot.getValue(Game.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseGameViewHolder viewHolder, Game model, int position) {
        viewHolder.bindGame(model);

        viewHolder.mGameImage.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }

        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GameDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("games", Parcels.wrap(mGames));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mGames, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mGames.remove(position);
        getRef(position).removeValue();

    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

    private void setIndexInFirebase() {
        for (Game game : mGames) {
            int index = mGames.indexOf(game);
            DatabaseReference ref = getRef(index);
            game.setIndex(Integer.toString(index));
            ref.setValue(game);
        }
    }
}



