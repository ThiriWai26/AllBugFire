package com.example.bugfire.holder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsImageGridAdapter;
import com.example.bugfire.model.Feeds;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FeedsImageGridHolder extends RecyclerView.ViewHolder {

    private ImageView img, dialogimg;
    private FeedsImageGridAdapter feedsImageGridAdapter;
    List<String> images = new ArrayList<>();
    private OnItemClickListener listener;
    private LinearLayout layout;

    public FeedsImageGridHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        img = itemView.findViewById(R.id.imageView1);
        layout = itemView.findViewById(R.id.layout_image);
    }

    public static FeedsImageGridHolder create(LayoutInflater inflater, ViewGroup viewGroup, FeedsImageGridHolder.OnItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_imagegrid_feeditem, viewGroup,false);
        return new FeedsImageGridHolder(view,listener);
    }

    public void bindData(String photo) {
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + photo).into(img);
        Log.e("photo", String.valueOf(photo));

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("image","click");
                listener.onItemClickListener(img);
                Log.e("photo",photo);
                Dialog dialog = new Dialog(itemView.getContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
                dialog.setContentView(R.layout.layout_fullscreen_image);
                dialogimg = dialog.findViewById(R.id.dialog_imageview);
                Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + photo).into(dialogimg);
                Log.e("dialogimg", String.valueOf(dialogimg));
                dialog.show();
            }
        });

//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClickListener(img);
//                Dialog dialog = new Dialog(itemView.getContext(), android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
//                dialog.setContentView(R.layout.layout_fullscreen_image);
//            }
//        });
    }

    public interface OnItemClickListener {
        void onItemClickListener(ImageView img1);
    }
}