package com.example.bugfire.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.PlayerList;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class PlayerHolder extends RecyclerView.ViewHolder  {

    private OnPlayerItemClickListener listener;
    private LinearLayout linearone, lineartwo;
    private ImageView imageView1, imageView2;
    private TextView playername1, tvid, playername2, playertype1, playertype2;

    public PlayerHolder(@NonNull View itemView, OnPlayerItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        tvid = itemView.findViewById(R.id.playerId);
        imageView1 = itemView.findViewById(R.id.imageView1);
        imageView2 = itemView.findViewById(R.id.imageView2);
        playername1 = itemView.findViewById(R.id.playername1);
        playername2 = itemView.findViewById(R.id.playername2);
        playertype1 = itemView.findViewById(R.id.teamname1);
        playertype2 = itemView.findViewById(R.id.teamname2);
        linearone = itemView.findViewById(R.id.linearone);
        lineartwo = itemView.findViewById(R.id.lineartwo);
    }

    public static PlayerHolder create(LayoutInflater inflater, ViewGroup parent, OnPlayerItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_player_item, parent, false);
        return new PlayerHolder(view, listener);
    }

    public void bindData(PlayerList playerList) {

        Picasso.get().load(RetrofitService.BASE_URL + playerList.photo).into(imageView1);
        playername1.setText(playerList.name);
        playertype1.setText(playerList.teamName);
        tvid.setText(playerList.id);

        linearone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPlayerClick(Integer.parseInt((String) tvid.getText()));
                int position;
                position = getAdapterPosition();
                Log.e("position", String.valueOf(position));
            }
        });

    }


    public interface OnPlayerItemClickListener {
        void onPlayerClick(int i);
    }
}
