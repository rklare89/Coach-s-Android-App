package project.reserver.capstone_robertklare;

import android.content.Intent;
import android.os.Bundle;
import project.reserver.capstone_robertklare.Database.parentViewModel;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ParentDetails extends AppCompatActivity {

    private TextView name;
    private TextView phone;
    private TextView email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_details);
        name = findViewById(R.id.parentNameDetails);
        phone = findViewById(R.id.phoneContent);
        email = findViewById(R.id.eMailContent);



        Intent intent = getIntent();
        long parentID = intent.getLongExtra("parentID", 0);

        parentViewModel parentvm = new ViewModelProvider(this).get(parentViewModel.class);

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

        Button doneBtn = findViewById(R.id.doneBtnPD);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    }
