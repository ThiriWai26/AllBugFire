package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class DotaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnDotaItemClickListener listener;
    private ImageView featurephoto;
    private TextView tvtitle, tvabout;

    public DotaHolder(@NonNull View view, OnDotaItemClickListener listener) {
        super(view);
        this.listener = listener;

        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);
        itemView.setOnClickListener(this);
    }

    public static DotaHolder create(LayoutInflater inflater, ViewGroup parent, OnDotaItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_dota_item, parent, false);
        return new DotaHolder(view, listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onDotaClick();
    }

    public interface OnDotaItemClickListener {
        void onDotaClick();
    }
}
