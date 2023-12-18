package android.reserver.capstone_robertklare;

import android.reserver.capstone_robertklare.Entities.Player;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PickupListAdapter extends RecyclerView.Adapter<PickupListViewHolder> {

    List<Player> playerList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public PickupListAdapter(List<Player> playerList, OnItemClickListener listener) {
        this.playerList = playerList;
        this.onItemClickListener = listener;
    }

    public PickupListAdapter(List<Player> playerList) { this.playerList = playerList; }


    @NonNull
    @Override
    public PickupListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_layout, parent, false);
        return new PickupListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PickupListViewHolder holder, int position) {

        Player currentPlayer = playerList.get(position);
        holder.bindData(currentPlayer);

        holder.cardview.setOnClickListener(v -> {
            if(onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public void setPlayers(List<Player> players) {
        playerList = players;
        notifyDataSetChanged();
    }

    public List<Player> getPlayers(){
        return playerList;
    }
}
