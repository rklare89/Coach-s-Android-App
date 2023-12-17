package android.reserver.capstone_robertklare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.AsyncQueryHandler;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Entities.Player;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TeamDetails extends AppCompatActivity implements PlayerListAdapter.OnItemClickListener{

    private PlayerListAdapter adapter;
    Repository repo;
    private int teamID;
    private static final String TEAM_ID_KEY = "team_id_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);

        repo = new Repository(getApplication());

        // Sets up headers on page
        Intent intent = getIntent();
        String teamName = intent.getStringExtra("team");
        String teamAge = intent.getStringExtra("age");

        if (savedInstanceState != null) {
            teamID = savedInstanceState.getInt(TEAM_ID_KEY, 0);
        } else {

            teamID = intent.getIntExtra("id", 0);
        }


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

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {

                        repo.removePlayersFromTeam(teamID);
                        repo.deleteTeamById(teamID);
                        return null;
                    }
                }.execute();

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
        long parID = selectedPlayer.getParentID();
        Log.d("Parent ID:", "onItemClick: " + parID);
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ResumeCalled", "onResume: Method Called");



        // Refresh data when returning to the activity
        repo.getPlayersByTeamID(teamID).observe(this, player -> {
            Log.d("listSize", "onResume: " + player.size());
            Log.d("TeamID", "TeamID = " + teamID);
            adapter.setPlayers(player);
            adapter.notifyDataSetChanged(); // Notify adapter about the data change
        });


    }



}