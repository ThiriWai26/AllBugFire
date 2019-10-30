package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class CSGOHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnCSGOItemClickListener listener;
    private ImageView featurephoto;
    private TextView tvtitle, tvabout;
    
    public CSGOHolder(@NonNull View view, OnCSGOItemClickListener listener) {
        super(view);
        this.listener = listener;

        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);
        itemView.setOnClickListener(this);
    }
    
    public static CSGOHolder create(LayoutInflater inflater, ViewGroup parent, OnCSGOItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_csgo_item, parent, false);
        return new CSGOHolder(view, listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onCSGOClick();
    }

    public interface OnCSGOItemClickListener {
        void onCSGOClick();
    }
}
