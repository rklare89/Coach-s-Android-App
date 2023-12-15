package android.reserver.capstone_robertklare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Entities.Player;
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

    }

    @Override
    public void onItemClick(int position) {
        Player selectedPlayer = adapter.getPlayers().get(position);

    }
}