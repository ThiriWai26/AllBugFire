package com.example.bugfire.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.Feeds;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class FeedsImageGridHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    public FeedsImageGridHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView1);
    }


    public static FeedsImageGridHolder create(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.layout_imagegrid_feeditem, viewGroup,false);
        return new FeedsImageGridHolder(view);
    }

    public void bindData(String photo) {

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + photo).into(imageView);

        Log.e("photo", String.valueOf(photo));
    }
}
