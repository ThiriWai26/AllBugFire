package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class MBLLHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnMBLLItemClickListener listener;
    private ImageView featurephoto;
    private TextView tvtitle, tvabout;

    public MBLLHolder(@NonNull View view, OnMBLLItemClickListener listener) {
        super(view);
        this.listener = listener;

        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);
        itemView.setOnClickListener(this);
    }

    public static void bindData() {
    }

    public static MBLLHolder create(LayoutInflater inflater, ViewGroup parent, OnMBLLItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_mbll_item, parent, false);
        return new MBLLHolder(view, listener);
    }

    @Override
    public void onClick(View v) {
        listener.onMBLLClick();
    }

    public interface OnMBLLItemClickListener {
        void onMBLLClick();
    }
}
