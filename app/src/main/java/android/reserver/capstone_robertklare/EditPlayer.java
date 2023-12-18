package android.reserver.capstone_robertklare;

import android.content.Intent;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Database.parentViewModel;
import android.reserver.capstone_robertklare.Entities.Parent;
import android.reserver.capstone_robertklare.Entities.Player;
import android.reserver.capstone_robertklare.Enum.Role;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditPlayer extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText num;
    private EditText pos;
    private EditText parFirst;
    private EditText parLast;
    private EditText email;
    private EditText phone;
    private DatePicker date;
    private Repository repo;
    private Parent parent;
    private Player player;
    private String selectedDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player);
        repo = new Repository(getApplication());

        Intent getIntent = getIntent();

        //Preloading current info

        String pfname = getIntent.getStringExtra("firstName");
        firstName = findViewById(R.id.editPlayerFirstName);
        firstName.setText(pfname);

        String plname = getIntent.getStringExtra("lastName");
        lastName = findViewById(R.id.editLastName);
        lastName.setText(plname);

        int number = getIntent.getIntExtra("num", 0);
        num = findViewById(R.id.editNumber);
        num.setText(Integer.toString(number));

        String position = getIntent.getStringExtra("position");
        pos = findViewById(R.id.editPosition);
        pos.setText(position);

        date = findViewById(R.id.datePickerEdit);
        String dob = getIntent.getStringExtra("dob");
        String[] birthday;
        assert dob != null;
        birthday = dob.split("/", 3);
        int[] birthDays = new int[3];
        for(int i = 0; i < 3; i++) {
            birthDays[i] = Integer.parseInt(birthday[i]);
        }

        date.init(birthDays[2], (birthDays[0] - 1), birthDays[1], null);

        long parID = getIntent.getLongExtra("parid", 0);


        parentViewModel parentvm = new ViewModelProvider(this).get(parentViewModel.class);
        parentvm.getParentById(parID).observe(this, parent -> {
            if (parent !=null) {
                String pfirst = parent.getFirstName();
                String plast = parent.getLastname();
                String phonenum = parent.getPhone();
                String emailAdd = parent.getEmail();
                parFirst = findViewById(R.id.editParentFirst);
                parFirst.setText(pfirst);

                parLast = findViewById(R.id.editParentLast);
                parLast.setText(plast);

                phone = findViewById(R.id.editPhone);
                phone.setText(phonenum);

                email = findViewById(R.id.editEmailAddress);
                email.setText(emailAdd);
            }
        });


        Spinner rosterSpinner = findViewById(R.id.editrosterSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.yesNo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        rosterSpinner.setAdapter(adapter);

        boolean isOnRoster = getIntent.getBooleanExtra("isRostered", false);

        if (isOnRoster){
        rosterSpinner.setSelection(0);
        }
        else {
            rosterSpinner.setSelection(1);
        }

        Button cancelBtn = findViewById(R.id.cancelEditPlayer);
        cancelBtn.setOnClickListener(v -> finish());

        int teamID = getIntent.getIntExtra("teamid", 0);

        Button saveBtn = findViewById(R.id.savePlayer);
        saveBtn.setOnClickListener(v -> {

            String newParFName = sanitize(parFirst.getText().toString().trim());
            String newParLName = sanitize(parLast.getText().toString().trim());
            String newParPhone = phone.getText().toString().trim();
            String newEmail = email.getText().toString().trim();

            parent = new Parent(parID, newParFName, newParLName, teamID, newParPhone, newEmail, Role.PARENT);


            long newPlayerID = getIntent.getLongExtra("playerid", 0);
            String newPlayerFName = sanitize(firstName.getText().toString().trim());
            String newPlayerLName = sanitize(lastName.getText().toString().trim());
            String newPosition = pos.getText().toString().trim();
            String newNum = removeLetters(sanitize(num.getText().toString().trim()));
            int NewNum = Integer.parseInt(newNum);
            boolean rostered = rosterSpinner.getSelectedItem().toString().equals("Yes");


            date.init(date.getYear(), date.getMonth(), date.getDayOfMonth(), (view, year, monthOfYear, dayOfMonth) -> {
                // Convert the selected date to a string
                selectedDate = (monthOfYear + 1) + "/" + dayOfMonth + "/" + year;
            });

            if (selectedDate == null){
                selectedDate = dob;
            }

            player = new Player(newPlayerID, newPlayerFName, newPlayerLName, teamID, newPosition, NewNum, selectedDate, parID, rostered);

            if(isValidEmail(newEmail)) {

                repo.updateParent(parent);
                repo.updatePlayer(player);
                finish();
            }
            else {
                Toast.makeText(EditPlayer.this, "Check Your Email Address. example@gmail.com", Toast.LENGTH_SHORT).show();
            }

        });

    }

    //Data Sanitation Functions
    public String sanitize(String string) {
        String regex = "[^a-zA-Z0-9\\s]";
        return string.replaceAll(regex, "");
    }

    public String removeLetters(String string) {
        String regex = "\\D";
        return string.replaceAll(regex, "");
    }
    public boolean isValidEmail (String email) {
        final String EMAIL_REGULAR_EXPRESSION = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (email == null) {
            return false;
        }
        Pattern p = Pattern.compile(EMAIL_REGULAR_EXPRESSION);
        Matcher m = p.matcher(email);
        return m.matches();
    }

}