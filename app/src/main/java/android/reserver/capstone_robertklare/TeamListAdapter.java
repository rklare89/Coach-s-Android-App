package android.reserver.capstone_robertklare;

import android.reserver.capstone_robertklare.Entities.Team;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamListAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<Team> teamList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public TeamListAdapter(List<Team> teamList, OnItemClickListener listener) {
    this.teamList = teamList;
    this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Team currentTeam = teamList.get(position);
        holder.bindData(currentTeam);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {

        return teamList.size();
    }

    public void setTeams(List<Team> teams) {
        teamList = teams;
        notifyDataSetChanged();
    }

    public List<Team> getTeams() {
        return teamList;
    }
}
