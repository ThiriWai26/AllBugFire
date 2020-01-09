package com.example.bugfire.holder;

import android.annotation.SuppressLint;
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
import com.example.bugfire.rabbitconverter.Rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class PlayerHolder extends RecyclerView.ViewHolder {

    private OnPlayerItemClickListener listener;
    private LinearLayout layoutplayer1;
    private ImageView imageView1;
    private TextView playername1, playerteam1, playerId1;

    public PlayerHolder(@NonNull View itemView, OnPlayerItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        layoutplayer1 = itemView.findViewById(R.id.layout_playerItems);
        imageView1 = itemView.findViewById(R.id.imageView1);
        playername1 = itemView.findViewById(R.id.playername1);
        playerteam1 = itemView.findViewById(R.id.teamname1);
        playerId1 = itemView.findViewById(R.id.playerId1);

    }

    public static PlayerHolder create(LayoutInflater inflater, ViewGroup parent, OnPlayerItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_player_item, parent, false);
        return new PlayerHolder(view, listener);
    }

    public void bindData(final PlayerList playerList) {

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + playerList.photo).into(imageView1);
        if (userFont.equals("z")) {
            playername1.setText(Rabbit.uni2zg(playerList.name));
            playerteam1.setText(Rabbit.uni2zg(playerList.teamName));
        } else {
            playername1.setText(Rabbit.zg2uni(playerList.name));
            playerteam1.setText(Rabbit.zg2uni(playerList.teamName));
        }

        layoutplayer1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                listener.onPlayerClick(playerList);
            }
        });

        Log.e("id", String.valueOf(playerList.id));
        Log.e("name", playerList.name);
        Log.e("photo", playerList.photo);
        Log.e("team_name", playerList.teamName);

    }

    public interface OnPlayerItemClickListener {
        void onPlayerClick(PlayerList playerList);
    }
}
