package android.reserver.capstone_robertklare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Entities.Player;
import android.reserver.capstone_robertklare.Entities.Team;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TeamListAdapter.OnItemClickListener {

    private TeamListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTeamBtn = findViewById(R.id.addTeamBtn);

        Repository repo = new Repository(getApplication());

        Player testPlayer = new Player("Rusty", "Nail", 1, "P/OF", 20, "12/14/1989", 1, true);
        repo.insertPlayer(testPlayer);

        addTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTeam.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.teamList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TeamListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        // Observe changes in LiveData and update the adapter
        repo.getAllTeams().observe(this, teams -> {
            // Update the adapter with the new data
            adapter.setTeams(teams);
        });
    }

    @Override
    public void onItemClick(int position) {
        Team selectedTeam = adapter.getTeams().get(position);
        String teamName = selectedTeam.getTeamName();
        int teamId = selectedTeam.getTeamId();
        String ageGroup = selectedTeam.getAgeGroup();

        Intent intent = new Intent(MainActivity.this, TeamDetails.class);
        intent.putExtra("team", teamName);
        intent.putExtra("age", ageGroup);
        intent.putExtra("id", teamId);
        startActivity(intent);
    }
}
