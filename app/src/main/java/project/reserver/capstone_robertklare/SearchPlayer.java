package project.reserver.capstone_robertklare;

import android.content.Intent;
import android.os.Bundle;
import project.reserver.capstone_robertklare.Database.Repository;
import project.reserver.capstone_robertklare.Entities.Player;


import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchPlayer extends AppCompatActivity implements PlayerListAdapter.OnItemClickListener{

    Repository repo;
    private PlayerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_player);

        repo = new Repository(getApplication());
        Button doneBtn = findViewById(R.id.searchDoneButton);
        doneBtn.setOnClickListener(v -> finish());

        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.searchList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PlayerListAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        repo.getAllPlayers().observe(this, player -> adapter.setPlayers(player));



        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = sanitize(newText);
                filterData(newText);
                return true;
            }
        });


    }



    private void filterData(String searchText) {
        // Use the repository to get filtered data based on the search text
        repo.searchPlayers(searchText).observe(this, players -> {
            // Update the adapter with the filtered data
            adapter.setPlayers(players);
        });}

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
        Intent intent = new Intent(SearchPlayer.this, PlayerDetails.class);
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


    //Function to sanitize data
    public String sanitize(String string) {
        String regex = "[^a-zA-Z0-9\\s]";
        return string.replaceAll(regex, "");
    }



}