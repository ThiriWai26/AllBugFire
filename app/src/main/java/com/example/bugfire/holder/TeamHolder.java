package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.Teams;
import com.example.bugfire.rabbitconverter.Rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class TeamHolder extends RecyclerView.ViewHolder {

    private OnTeamsItemClickListener listener;
    private LinearLayout layout;
    private ImageView imageView;
    private TextView teamname, tvid;

    public TeamHolder(@NonNull View itemView, TeamHolder.OnTeamsItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        layout = itemView.findViewById(R.id.layout);
        tvid = itemView.findViewById(R.id.tvId);
        imageView = itemView.findViewById(R.id.imageView);
        teamname = itemView.findViewById(R.id.teamname);
    }

    public static TeamHolder create(LayoutInflater inflater, ViewGroup parent, TeamHolder.OnTeamsItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_team_item, parent, false);
        return new TeamHolder(view, listener);
    }

    public void bindData(final Teams teams) {
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + teams.photo).into(imageView);
        if (userFont.equals("z")) {
            teamname.setText(Rabbit.uni2zg(teams.name));
        } else {
            teamname.setText(Rabbit.zg2uni(teams.name));
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTeamClick(teams);
            }
        });

    }

    public interface OnTeamsItemClickListener {
        void onTeamClick(Teams teams);
    }
}
