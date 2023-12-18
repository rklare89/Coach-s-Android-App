package android.reserver.capstone_robertklare;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Entities.Team;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TeamListAdapter.OnItemClickListener {

    private TeamListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTeamBtn = findViewById(R.id.addTeamBtn);
        Button pickupPlayer = findViewById(R.id.pickupBtn);
        Button searchBtn = findViewById(R.id.searchMain);
        searchBtn.setOnClickListener(v -> {
            Intent searchIntent = new Intent(MainActivity.this, SearchPlayer.class);
            startActivity(searchIntent);
        });



        pickupPlayer.setOnClickListener(v -> {
            Intent pickupIntent = new Intent(MainActivity.this, PickupList.class);
            startActivity(pickupIntent);
        });
        Repository repo = new Repository(getApplication());


        addTeamBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTeam.class);
            startActivity(intent);
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
