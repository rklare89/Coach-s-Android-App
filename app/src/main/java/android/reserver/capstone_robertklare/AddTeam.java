package android.reserver.capstone_robertklare;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Entities.Team;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        Repository repo = new Repository(getApplication());


        //Code to get the back button working
        Button backBtn = findViewById(R.id.addTeamBackBtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AddTeam.this, MainActivity.class);
            startActivity(intent);
        });

        //Code for the drop down list.  Makes sure data is in correct format.
        Spinner ageSpinner = findViewById(R.id.ageSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.Ages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ageSpinner.setAdapter(adapter);

        Button saveBtn = findViewById(R.id.addTeamSaveBtn);
        saveBtn.setOnClickListener(v -> {

            Team newTeam = new Team("default", "default");

            EditText teamName = findViewById(R.id.insertTeamName);
            String teamString = CleanInput.sanitize(teamName.getText().toString().trim());
            String newAge = ageSpinner.getSelectedItem().toString();
            if (!(teamString.isEmpty())) {
                newTeam.setTeamName(teamString);
                newTeam.setAgeGroup(newAge);
                repo.insertTeam(newTeam);
                Toast.makeText(AddTeam.this, "Team Added!", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(AddTeam.this, "Team Name is Empty! Try Again!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}