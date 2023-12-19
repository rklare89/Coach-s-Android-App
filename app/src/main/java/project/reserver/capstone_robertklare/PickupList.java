package project.reserver.capstone_robertklare;

import android.content.Intent;
import android.os.Bundle;
import project.reserver.capstone_robertklare.Database.Repository;
import project.reserver.capstone_robertklare.Entities.Player;

import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PickupList extends AppCompatActivity implements PickupListAdapter.OnItemClickListener{

    private PickupListAdapter adapter;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_list);
        repo = new Repository(getApplication());


        RecyclerView recyclerView = findViewById(R.id.pickupList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PickupListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        repo.getPlayersNotRostered().observe(this, player -> adapter.setPlayers(player));

        Button doneBtn = findViewById(R.id.pickupDone);
        doneBtn.setOnClickListener(v -> finish());
    }

    @Override
    public void onItemClick(int position) {
        Player selectedPlayer = adapter.getPlayers().get(position);
        String playerFirst = selectedPlayer.getFirstName();
        String playerLast = selectedPlayer.getLastname();
        String playerPosition = selectedPlayer.getPosition();
        String playerDob = selectedPlayer.getDob();
        int playerNum = selectedPlayer.getNumber();
        int teamID = selectedPlayer.getTeamID();
        long parID = selectedPlayer.getParentID();
        long playerID = selectedPlayer.getPersonID();
        Log.d("Parent ID:", "onItemClick: " + parID);
        boolean isRostered = selectedPlayer.isRostered();

        //Packages intent for Details Activity
        Intent intent = new Intent(PickupList.this, PlayerDetails.class);
        intent.putExtra("firstName", playerFirst);
        intent.putExtra("lastName", playerLast);
        intent.putExtra("position", playerPosition);
        intent.putExtra("DOB", playerDob);
        intent.putExtra("number", playerNum);
        intent.putExtra("team", teamID);
        intent.putExtra("parent", parID);
        intent.putExtra("roster", isRostered);
        intent.putExtra("personID", playerID);
        startActivity(intent);
    }
}