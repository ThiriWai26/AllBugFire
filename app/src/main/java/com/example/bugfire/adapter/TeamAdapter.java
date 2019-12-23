package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.fragment.TeamsFragment;
import com.example.bugfire.holder.TeamHolder;
import com.example.bugfire.model.Teams;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamHolder> {

    List<Teams> teamsList;
    private TeamHolder.OnTeamsItemClickListener listener;

    public TeamAdapter(TeamsFragment teamsFragment) {
        teamsList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return TeamHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, int position) {
        holder.bindData(teamsList.get(position));
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }

    public void addItem(List<Teams> teams){

        if(this.teamsList.size() == 0 ){
            this.teamsList = teams;
        }
        this.teamsList.addAll(teams);
        notifyDataSetChanged();

    }
}
