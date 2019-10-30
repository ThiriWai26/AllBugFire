package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class MobileHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnMobileItemClickListener listener;
    private ImageView imgmobilelegand, imgpubg;

    public MobileHolder(@NonNull View itemView, OnMobileItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        imgmobilelegand = itemView.findViewById(R.id.imgmobile);
        imgpubg = itemView.findViewById(R.id.imgpubg);

        itemView.setOnClickListener(this);
    }

    public static void bindData() {
    }

    public static MobileHolder create(LayoutInflater inflater, ViewGroup parent, OnMobileItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_mobile_item, parent, false);
        return new MobileHolder(view, listener);
    }

    @Override
    public void onClick(View v) {
        listener.onMobileClick();
    }

    public interface OnMobileItemClickListener {
        void onMobileClick();
    }
}
