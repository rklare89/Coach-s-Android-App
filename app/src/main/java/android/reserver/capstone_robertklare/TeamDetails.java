package android.reserver.capstone_robertklare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TeamDetails extends AppCompatActivity {

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
    }
}