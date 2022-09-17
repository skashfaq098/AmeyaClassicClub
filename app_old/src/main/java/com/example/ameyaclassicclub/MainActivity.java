package com.example.ameyaclassicclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ameyaclassicclub.config.ProjectConstants;
import com.example.ameyaclassicclub.utils.ProjectSharedPreference;

public class MainActivity extends AppCompatActivity {
    private Button member_button;
    private Button staff_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        member_button = findViewById(R.id.memberBtn);
        staff_button = findViewById(R.id.staffBtn);

        member_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                ProjectSharedPreference.getInstance(MainActivity.this).saveStringPreference(ProjectConstants.MEMBER_OR_STAFF,"member");
                intent.putExtra(ProjectConstants.MEMBER_OR_STAFF,"member");
                startActivity(intent);
            }
        });
        staff_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                ProjectSharedPreference.getInstance(MainActivity.this).saveStringPreference(ProjectConstants.MEMBER_OR_STAFF,"staff");
                intent.putExtra(ProjectConstants.MEMBER_OR_STAFF,"staff");
                startActivity(intent);
            }
        });

    }
}