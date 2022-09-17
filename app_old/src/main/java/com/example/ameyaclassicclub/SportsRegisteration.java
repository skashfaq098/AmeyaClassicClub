package com.example.ameyaclassicclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ameyaclassicclub.model.member.MemberRegisterationModel;
import com.example.ameyaclassicclub.model.sports.SportsRegisterationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SportsRegisteration extends AppCompatActivity {

    private EditText edit_txt_sportsName, edit_txt_sportsId, edit_txt_sportsTimeSlot,edit_txt_sportsDaysInAweek,edit_txt_sportsCoachingFees;
    private TextView sports_registeration_btn;
    ProgressBar signUp_progress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    String sportsName, sportsId, sportsTimeSlot,sportsDaysInAweek,sportsCoachingFees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_registeration);
        signUp_progress = findViewById(R.id.sports_signUp_progress);
        edit_txt_sportsName = findViewById(R.id.sportsName);
        edit_txt_sportsId = findViewById(R.id.sportsId);
        edit_txt_sportsTimeSlot = findViewById(R.id.sportsTimeSlot);
        edit_txt_sportsDaysInAweek = findViewById(R.id.sportsDaysInAweek);
        edit_txt_sportsCoachingFees = findViewById(R.id.sportsCoachingFees);


        sports_registeration_btn = findViewById(R.id.sportsRegisteraionBtn);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");

        sports_registeration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportsName = edit_txt_sportsName.getText().toString().trim();
                sportsId = edit_txt_sportsId.getText().toString().trim();
                sportsTimeSlot = edit_txt_sportsTimeSlot.getText().toString().trim();
                sportsDaysInAweek = edit_txt_sportsDaysInAweek.getText().toString().trim();
                sportsCoachingFees = edit_txt_sportsCoachingFees.getText().toString().trim();


                SportsRegisterationModel data = new SportsRegisterationModel(sportsName, sportsId, sportsTimeSlot,sportsDaysInAweek,sportsCoachingFees);
                Map<String, SportsRegisterationModel> users = new HashMap<>();
                users.put(sportsId,data);
                FirebaseDatabase.getInstance().getReference("Sports")
                        .setValue(users).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //    progressbar GONE
                                signUp_progress.setVisibility(View.GONE);
                                Toast.makeText(SportsRegisteration.this, "Successful Added", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SportsRegisteration.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        });
    }


    //    if the user already logged in then it will automatically send on Dashboard/MainActivity activity.
}