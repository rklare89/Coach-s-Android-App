package android.reserver.capstone_robertklare;

import android.reserver.capstone_robertklare.Entities.Player;
import android.reserver.capstone_robertklare.Entities.Team;
import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerListViewHolder extends RecyclerView.ViewHolder {

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView positionTextView;
    private TextView birthYearTextView;
    CardView cardview;

    public PlayerListViewHolder(@NonNull View itemView) {
        super(itemView);

        cardview = itemView.findViewById(R.id.cardView2);
        firstNameTextView = itemView.findViewById(R.id.firstName);
        lastNameTextView = itemView.findViewById(R.id.lastName);
        positionTextView = itemView.findViewById(R.id.position);
        birthYearTextView = itemView.findViewById(R.id.birthday);
    }

    public CardView getCardView() {
        return cardview;
    }

    public void bindData(Player player) {
        firstNameTextView.setText(player.getFirstName());
        lastNameTextView.setText((player.getLastname()));
        positionTextView.setText((player.getPosition()));
        birthYearTextView.setText((player.getDob()));

    }
}
