package android.reserver.capstone_robertklare;

import android.reserver.capstone_robertklare.Entities.Team;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView teamNameTextView;
    private TextView teamAgeGroupTextView;
    CardView cardView;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        teamNameTextView = itemView.findViewById(R.id.team_name);
        teamAgeGroupTextView = itemView.findViewById(R.id.team_age);
        cardView = itemView.findViewById(R.id.cardView);

    }

    public CardView getCardView() {
        return cardView;
    }

    public void bindData(Team team) {
        teamNameTextView.setText(team.getTeamName());
        teamAgeGroupTextView.setText(team.getAgeGroup());
    }
}
