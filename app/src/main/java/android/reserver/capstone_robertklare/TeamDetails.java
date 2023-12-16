package android.reserver.capstone_robertklare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Entities.Player;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TeamDetails extends AppCompatActivity implements PlayerListAdapter.OnItemClickListener{

    private PlayerListAdapter adapter;
    Repository repo = new Repository(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);


        //Sets up headers on page
        Intent intent = getIntent();
        String teamName = intent.getStringExtra("team");
        String teamAge = intent.getStringExtra("age");
        int teamID = intent.getIntExtra("id", 0);

        TextView teamNameBox = findViewById(R.id.detailsTeamName);
        TextView teamAgeBox = findViewById(R.id.textView9);
        teamNameBox.setText(teamName);
        teamAgeBox.setText(teamAge);

        RecyclerView recyclerView = findViewById(R.id.playerList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlayerListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        repo.getPlayersByTeamID(teamID).observe(this, player -> {
            adapter.setPlayers(player);
        });

        Button deleteBtn = findViewById(R.id.deleteTeamButton);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            repo.deleteTeamById(teamID);
            finish();
            }
        });

        Button addBtn = findViewById(R.id.addPlayerButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(TeamDetails.this, AddPlayer.class);
                addIntent.putExtra("id", teamID);
                addIntent.putExtra("age", teamAge);
                startActivity(addIntent);
            }
        });

    }

    @Override
    public void onItemClick(int position) {

        //Extracts Data for Detail Activity
        Player selectedPlayer = adapter.getPlayers().get(position);
        String playerFirst = selectedPlayer.getFirstName();
        String playerLast = selectedPlayer.getLastname();
        String playerPosition = selectedPlayer.getPosition();
        String playerDob = selectedPlayer.getDob();
        int playerNum = selectedPlayer.getNumber();
        int teamID = selectedPlayer.getTeamID();
        int parID = selectedPlayer.getParentID();
        boolean isRostered = selectedPlayer.isRostered();

        //Packages intent for Details Activity
        Intent intent = new Intent(TeamDetails.this, PlayerDetails.class);
        intent.putExtra("firstName", playerFirst);
        intent.putExtra("lastName", playerLast);
        intent.putExtra("position", playerPosition);
        intent.putExtra("DOB", playerDob);
        intent.putExtra("number", playerNum);
        intent.putExtra("team", teamID);
        intent.putExtra("parent", parID);
        intent.putExtra("roster", isRostered);
        startActivity(intent);

    }


}