package com.example.bugfire.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.GamesList;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class MobileGamesHolder extends RecyclerView.ViewHolder {

    private OnMobileItemClickListener listener;
    private ImageView imageView;
    private TextView tvId;

    public MobileGamesHolder(@NonNull View itemView, MobileGamesHolder.OnMobileItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        imageView = itemView.findViewById(R.id.imageView);
        tvId = itemView.findViewById(R.id.tvId);
    }

    public static MobileGamesHolder create(LayoutInflater inflater, ViewGroup parent, MobileGamesHolder.OnMobileItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_mobile_item, parent, false);
        return new MobileGamesHolder(view, listener);
    }

    public void bindData(final GamesList gamesList) {

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + gamesList.photo).into(imageView);


        Log.e("photo", gamesList.photo);
        Log.e("id", String.valueOf(gamesList.id));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMobileItemClick(gamesList.id);
            }
        });
    }

    public interface OnMobileItemClickListener {
        void onMobileItemClick(int id);
    }
}
