package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class PlayerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnPlayerItemClickListener listener;
    private LinearLayout linearone, lineartwo;

    public PlayerHolder(@NonNull View itemView, OnPlayerItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        linearone = itemView.findViewById(R.id.linearone);
        lineartwo = itemView.findViewById(R.id.lineartwo);
        itemView.setOnClickListener(this);
    }

    public static PlayerHolder create(LayoutInflater inflater, ViewGroup parent, OnPlayerItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_player_item, parent, false);
        return new PlayerHolder(view, listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onPlayerClick();
    }

    public interface OnPlayerItemClickListener {
        void onPlayerClick();
    }
}
