package com.epicodus.andrewrusso.elforge.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.andrewrusso.elforge.Constants;
import com.epicodus.andrewrusso.elforge.R;
import com.epicodus.andrewrusso.elforge.adapters.FirebaseGameListAdapter;
import com.epicodus.andrewrusso.elforge.adapters.FirebaseGameViewHolder;
import com.epicodus.andrewrusso.elforge.models.Game;
import com.epicodus.andrewrusso.elforge.util.OnStartDragListener;
import com.epicodus.andrewrusso.elforge.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QueuedGameListActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mGameReference;
    private FirebaseGameListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);



        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mGameReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_GAMES).child(uid);

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_GAMES)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY);

        mFirebaseAdapter = new FirebaseGameListAdapter(Game.class, R.layout.queue_list_item_drag, FirebaseGameViewHolder.class,
                        mGameReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);

        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
