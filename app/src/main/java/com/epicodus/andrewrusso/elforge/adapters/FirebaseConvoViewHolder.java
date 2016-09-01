package com.epicodus.andrewrusso.elforge.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.andrewrusso.elforge.R;
import com.epicodus.andrewrusso.elforge.models.Convo;

/**
 * Created by andrewrusso on 9/1/16.
 */
public class FirebaseConvoViewHolder extends RecyclerView.ViewHolder{
    View mView;
    Context mContext;

    public FirebaseConvoViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindConvo (Convo convo) {
        TextView name = (TextView) mView.findViewById(R.id.nameTextView);
        TextView message = (TextView) mView.findViewById(R.id.messageTextView);
        TextView date = (TextView) mView.findViewById(R.id.dateTextView);

        name.setText(convo.getName() + ":");
        message.setText(convo.getMessage());
        date.setText(convo.getDate());

    }
}
