package android.reserver.capstone_robertklare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Entities.Team;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayerDetails extends AppCompatActivity {

    Repository repo = new Repository(getApplication());
    Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        Button donebtn = findViewById(R.id.Done);
        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();

        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String fullName = firstName + " " + lastName;
        String position = intent.getStringExtra("position");
        String dob = intent.getStringExtra("DOB");
        int num = intent.getIntExtra("number", 0);
        int teamID = intent.getIntExtra("team", 0);
        int parID = intent.getIntExtra("parent", 0);
        boolean isRostered = intent.getBooleanExtra("roster", false);
        TextView onTeam = findViewById(R.id.onTeam);

        new AsyncTask<Integer, Void, Team>() {
            @Override
            protected Team doInBackground(Integer... params) {
                int teamID = params[0];
                return repo.getPlayerTeamByID(teamID);
            }

            @Override
            protected void onPostExecute(Team result) {
                // Handle the result on the main thread
                // Update your UI with the team information
                // For example, you can set text to TextViews, etc.
                team = result;
                updateUIWithTeamInfo(isRostered, onTeam);
            }
        }.execute(teamID);

        //String teamName = team.getTeamName();
        TextView playerName = findViewById(R.id.playerNameDetails);
        playerName.setText(fullName);




        TextView DOB = findViewById(R.id.Bday);
        DOB.setText(dob);
        TextView posiView = findViewById(R.id.positionDetails);
        posiView.setText(position);
        TextView numView = findViewById(R.id.number);
        numView.setText(String.valueOf(num));






    }

    private void updateUIWithTeamInfo(boolean isRostered, TextView onTeam) {
        // Update UI with team information (e.g., set text to TextViews)
        if (team != null) {
            String teamName = team.getTeamName();
            // Update relevant TextViews with team information
            if (isRostered) {
                String message = "Rostered with " + teamName;
                onTeam.setText(message);
            }
            else {
                String message = "Pickup Player";
                onTeam.setText(message);
            }
        }
        else {
            String message = "Pickup Player";
            onTeam.setText(message);
        }

    }
}