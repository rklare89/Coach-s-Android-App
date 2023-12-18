package android.reserver.capstone_robertklare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Database.parentViewModel;
import android.reserver.capstone_robertklare.Entities.Parent;
import android.widget.DatePicker;
import android.widget.EditText;

public class EditPlayer extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText num;
    EditText pos;
    EditText parFirst;
    EditText parLast;
    EditText email;
    EditText phone;

    DatePicker date;

    Repository repo;
    Parent parent;

    private parentViewModel parentvm;


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
        String birthday[] = new String[3];
        birthday = dob.split("/", 3);
        int birthDays[] = new int[3];
        for(int i = 0; i < 3; i++) {
            birthDays[i] = Integer.valueOf(birthday[i]);
        }

        date.init(birthDays[2], (birthDays[0] - 1), birthDays[1], null);

        long parID = getIntent.getLongExtra("parid", 0);


        parentvm = new ViewModelProvider(this).get(parentViewModel.class);
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


    }
}