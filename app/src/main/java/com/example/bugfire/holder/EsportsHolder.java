package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class EsportsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnEsportItemClickListener listener;
    private ImageView featurephoto;
    private TextView tvtitle, tvabout;

    public EsportsHolder(@NonNull View view, OnEsportItemClickListener listener) {
        super(view);
        this.listener = listener;

        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);
        itemView.setOnClickListener(this);
    }

    public static EsportsHolder create(LayoutInflater inflater, ViewGroup parent, OnEsportItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_esport_item, parent, false);
        return new EsportsHolder(view, listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onEsportsClick();
    }

    public interface OnEsportItemClickListener {
        void onEsportsClick();
    }
}
