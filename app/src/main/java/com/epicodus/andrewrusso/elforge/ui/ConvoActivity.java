package com.epicodus.andrewrusso.elforge.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.epicodus.andrewrusso.elforge.Constants;
import com.epicodus.andrewrusso.elforge.R;
import com.epicodus.andrewrusso.elforge.adapters.FirebaseConvoViewHolder;
import com.epicodus.andrewrusso.elforge.models.Convo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConvoActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mChatReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    private String name;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.messageInput) EditText mMessageInput;
    @Bind(R.id.sendButton) ImageButton mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convo);
        ButterKnife.bind(this);

        mChatReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CONVOS);
        setupFirebaseAdapter();
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    getSupportActionBar().setTitle("El Forge");
                    name = user.getDisplayName();
                } else {

                }
            }
        };

        mSendButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        String message = mMessageInput.getText().toString();
        Date date = new Date();
        java.text.DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        mMessageInput.setText("");
        Convo convo = new Convo(name, message, dateFormat.format(date));
        mChatReference.push().setValue(convo);
    }


    private void setupFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Convo, FirebaseConvoViewHolder>(Convo.class, R.layout.convo_list_item,
                FirebaseConvoViewHolder.class, mChatReference) {
            @Override
            protected void populateViewHolder(FirebaseConvoViewHolder viewHolder, Convo model, int position) {
                viewHolder.bindConvo(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
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


}
