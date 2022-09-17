package com.example.ameyaclassicclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ameyaclassicclub.model.sports.SportsRegisterationModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EventRegisteration extends AppCompatActivity {

    private EditText edit_txt_eventName, edit_txt_eventId, edit_txt_eventTimeSlot,edit_txt_eventDaysInAweek,edit_txt_eventCoachingFees;
    private TextView event_registeration_btn;
    ProgressBar signUp_progress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    String eventName, eventId, eventTimeSlot,eventDaysInAweek,eventCoachingFees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registeration);
        signUp_progress = findViewById(R.id.event_signUp_progress);
        edit_txt_eventName = findViewById(R.id.eventName);
        edit_txt_eventId = findViewById(R.id.eventId);
        edit_txt_eventTimeSlot = findViewById(R.id.eventTimeSlot);
        edit_txt_eventDaysInAweek = findViewById(R.id.eventDaysInAweek);
        edit_txt_eventCoachingFees = findViewById(R.id.eventCoachingFees);


        event_registeration_btn = findViewById(R.id.eventRegisteraionBtn);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");

        event_registeration_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = edit_txt_eventName.getText().toString().trim();
                eventId = edit_txt_eventId.getText().toString().trim();
                eventTimeSlot = edit_txt_eventTimeSlot.getText().toString().trim();
                eventDaysInAweek = edit_txt_eventDaysInAweek.getText().toString().trim();
                eventCoachingFees = edit_txt_eventCoachingFees.getText().toString().trim();


                SportsRegisterationModel data = new SportsRegisterationModel(eventName, eventId, eventTimeSlot,eventDaysInAweek,eventCoachingFees);
                Map<String, SportsRegisterationModel> users = new HashMap<>();
                users.put(eventId,data);
                FirebaseDatabase.getInstance().getReference("event")
                        .setValue(users).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //    progressbar GONE
                                signUp_progress.setVisibility(View.GONE);
                                Toast.makeText(EventRegisteration.this, "Successful Added", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(EventRegisteration.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }
        });
    }



}