package project.reserver.capstone_robertklare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import project.reserver.capstone_robertklare.Database.Repository;
import project.reserver.capstone_robertklare.Entities.Parent;
import project.reserver.capstone_robertklare.Entities.Player;
import project.reserver.capstone_robertklare.Enum.Role;

import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddPlayer extends AppCompatActivity {

    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        Repository repo = new Repository(getApplication());

        Intent teamIntent = getIntent();
        int teamID = teamIntent.getIntExtra("id", 0);


        Spinner rosterSpinner = findViewById(R.id.rosterSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.yesNo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        rosterSpinner.setAdapter(adapter);

        Button cancelBtn = findViewById(R.id.cancelAddPlayer);
        cancelBtn.setOnClickListener(v -> finish());

        DatePicker datePicker = findViewById(R.id.datePicker);
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), (view, year, monthOfYear, dayOfMonth) -> {
            // Convert the selected date to a string
            selectedDate = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
        });

        Button addBtn = findViewById(R.id.addPlayer);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Parent Information
                //Parent First Name
                EditText parentFirst = findViewById(R.id.editTextParentFirst);
                String parentFirstName = CleanInput.sanitize(parentFirst.getText().toString());
                //Parent Last Name
                EditText parentLast = findViewById(R.id.editTextParentLast);
                String parentLastName = CleanInput.sanitize(parentLast.getText().toString());
                //Parent E-mail address
                EditText parentEmail = findViewById(R.id.editTextTextEmailAddress);
                String parentEmailAdd = parentEmail.getText().toString();
                //Parent Phone Number
                EditText parentPhone = findViewById(R.id.editTextPhone);
                parentPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
                String parentPhoneNo = parentPhone.getText().toString();


                //Player First Name
                EditText playerFirst = findViewById(R.id.editTextFirstName);
                String newPlayerFirst = CleanInput.sanitize(playerFirst.getText().toString());
                //Player Last Name
                EditText playerLast = findViewById(R.id.editTextLastName);
                String newPlayerLast = CleanInput.sanitize(playerLast.getText().toString());
                //Player Number
                EditText playerNum = findViewById(R.id.editTextNumber);
                String nu = CleanInput.removeLetters(CleanInput.sanitize(playerNum.getText().toString()));
                int num = Integer.parseInt(nu);
                //Player Position
                EditText playerPos = findViewById(R.id.editTextPosition);
                String newPos = playerPos.getText().toString();
                //Roster Status
                Spinner yesNo = findViewById(R.id.rosterSpinner);
                String rostered = yesNo.getSelectedItem().toString();
                boolean isRostered = rostered.equals("Yes");
                //Player information collected


                if (CleanInput.isValidEmail(parentEmailAdd) && CleanInput.isValidPhone(parentPhoneNo)) {

                    Parent newParent = new Parent(parentFirstName, parentLastName, teamID, parentPhoneNo, parentEmailAdd, Role.PARENT);
                    long parentNo = repo.insertParent(newParent);
                    //int parentNo = 0;   //need to assign inserted parent's "personID" to this value
                    Player newPlayer = new Player(newPlayerFirst, newPlayerLast, teamID, newPos, num, selectedDate, parentNo, isRostered);
                    repo.insertPlayer(newPlayer);
                    finish();
                }
                else if(!CleanInput.isValidEmail(parentEmailAdd) && (CleanInput.isValidPhone(parentPhoneNo))) {
                    Toast.makeText(AddPlayer.this, "Check Your Email Address. example@gmail.com", Toast.LENGTH_SHORT).show();
                }
                else if (CleanInput.isValidEmail(parentEmailAdd) && !(CleanInput.isValidPhone(parentPhoneNo))){
                    Toast.makeText(AddPlayer.this, "Check Your Phone Number.  (xxx) xxx-xxxx", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddPlayer.this, "Enter Valid Phone and E-mail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}