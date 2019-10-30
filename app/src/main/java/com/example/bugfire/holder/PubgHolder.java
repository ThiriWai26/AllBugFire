package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class PubgHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnPubgItemClickListener listener;
    private ImageView featurephoto;
    private TextView tvtitle, tvabout;


    public PubgHolder(@NonNull View view, OnPubgItemClickListener listener) {
        super(view);
        this.listener = listener;

        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);
        itemView.setOnClickListener(this);
    }

    public static PubgHolder create(LayoutInflater inflater, ViewGroup parent, OnPubgItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_pubg_item, parent, false);
        return new PubgHolder(view, listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onPubgClick();
    }

    public interface OnPubgItemClickListener {
        void onPubgClick();
    }
}
