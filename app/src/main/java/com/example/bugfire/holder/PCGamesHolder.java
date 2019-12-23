package com.example.bugfire.holder;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapPCGamesDetailAdapter;
import com.example.bugfire.fragment.PCGamesDetailFeedsFragment;
import com.example.bugfire.model.GamesList;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class PCGamesHolder extends RecyclerView.ViewHolder {
    
    private OnPCItemClickListener listener;
    private ImageView imageView;
    private TextView tvId;
    
    public PCGamesHolder(@NonNull View itemView, OnPCItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        
        imageView = itemView.findViewById(R.id.imageView);
        tvId = itemView.findViewById(R.id.tvId);

    }

    public void bindData(final GamesList gamesList) {

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + gamesList.photo).into(imageView);

        Log.e("photo", gamesList.photo);
        Log.e("id", String.valueOf(gamesList.id));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPCClick(gamesList.id);
            }
        });

    }

    public static PCGamesHolder create(LayoutInflater inflater, ViewGroup parent, OnPCItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_pcgame_item, parent, false);
        return new PCGamesHolder(view, listener);
    }


    public interface OnPCItemClickListener {
        void onPCClick(int i);
    }
}
