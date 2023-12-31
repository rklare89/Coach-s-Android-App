package project.reserver.capstone_robertklare;

import project.reserver.capstone_robertklare.Entities.Player;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PickupListViewHolder extends RecyclerView.ViewHolder {

    private final TextView firstNameTextView;
    private final TextView lastNameTextView;
    private final TextView positionTextView;
    private final TextView birthYearTextView;
    CardView cardview;

    public PickupListViewHolder(@NonNull View itemView) {
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
