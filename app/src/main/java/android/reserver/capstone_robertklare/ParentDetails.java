package android.reserver.capstone_robertklare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.reserver.capstone_robertklare.DAO.ParentDAO;
import android.reserver.capstone_robertklare.Database.Repository;
import android.reserver.capstone_robertklare.Database.parentViewModel;
import android.reserver.capstone_robertklare.Entities.Parent;

import android.util.Log;
import android.widget.TextView;

public class ParentDetails extends AppCompatActivity {

    private TextView name;
    private TextView phone;
    private TextView email;
    private Parent parent;
    private Repository repo;
    private parentViewModel parentvm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_details);
        repo = new Repository(getApplication());
        name = findViewById(R.id.parentNameDetails);
        phone = findViewById(R.id.phoneContent);
        email = findViewById(R.id.eMailContent);

        Intent intent = getIntent();
        long parentID = intent.getLongExtra("parentID", 0);

        parentvm = new ViewModelProvider(this).get(parentViewModel.class);

        parentvm.getParentById(parentID).observe(this, parent -> {
            if (parent !=null) {
                String parentFullName = parent.getFirstName() + " " + parent.getLastname();
                String parentPhone = parent.getPhone();
                String parentEmail = parent.getEmail();
                name.setText((parentFullName));
                phone.setText(parentPhone);
                email.setText(parentEmail);
            }
        });


        Log.d("Name", "onCreate: " + name);

    }



    }
