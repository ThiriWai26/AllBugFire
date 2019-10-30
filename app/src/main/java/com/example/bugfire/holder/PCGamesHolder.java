package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class PCGamesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    
    private OnPCItemClickListener listener;
    private ImageView imgdota, imgcounter;
    
    public PCGamesHolder(@NonNull View itemView, OnPCItemClickListener listener) {
        super(itemView);
        this.listener = this.listener;
        
        imgdota = itemView.findViewById(R.id.imgdota);
        imgcounter = itemView.findViewById(R.id.imgcounter);
        
        itemView.setOnClickListener(this);
    }

    public static void bindData() {
    }

    public static PCGamesHolder create(LayoutInflater inflater, ViewGroup parent, OnPCItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_pcgame_item, parent, false);
        return new PCGamesHolder(view, listener);
    }

    @Override
    public void onClick(View v) {
        listener.onPCClick();
    }

    public interface OnPCItemClickListener {
        void onPCClick();
    }
}
