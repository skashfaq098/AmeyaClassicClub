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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class StaffRegisterationActivity extends AppCompatActivity {

    private EditText edit_txt_firstName, edit_txt_userName, edit_txt_mobile,edit_txt_email,edit_txt_Designation, edit_txt_pass, edit_txt_coPass;
    private RadioButton radioMale, radioFemale;
    private Button button_register;
    private TextView text_view_login;
    ProgressBar signUp_progress;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    String firstName, userName, email,mobile,Designation, password, co_password;
    String gender = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_registeration);
        signUp_progress = findViewById(R.id.staff_signUp_progress);
        edit_txt_firstName = findViewById(R.id.staffFirstName);
        edit_txt_userName = findViewById(R.id.staffUserName);
        edit_txt_email = findViewById(R.id.staffEmail);
        edit_txt_pass = findViewById(R.id.staffPass);
        edit_txt_coPass = findViewById(R.id.staffCoPass);
        edit_txt_Designation = findViewById(R.id.staffDesignation);
        edit_txt_mobile = findViewById(R.id.staffDesignation);

        radioMale = findViewById(R.id.staffRadioMale);
        radioFemale = findViewById(R.id.staffRadioFemale);
        text_view_login = findViewById(R.id.staff_text_view_login);
        button_register = findViewById(R.id.staff_button_register);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserData");
        text_view_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        //        handle user SignUp button
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatefirstName() | !validateuserName() | !validateEmail() | !validateMobile()|!validateDesignation()|!validatePassword() | checkUserGender()) {
                    return;
                }
                if (password.equals(co_password)) {
                    //    progressbar VISIBLE
                    signUp_progress.setVisibility(View.VISIBLE);
                    System.out.println("userDetails"+email+password);
                    mAuth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener
                            (new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        MemberRegisterationModel data = new MemberRegisterationModel(firstName,userName,mobile,email,Designation,gender);
                                        Map<String, MemberRegisterationModel> users = new HashMap<>();
                                        users.put(userName,data);
                                        FirebaseDatabase.getInstance().getReference("UserData")
                                                .setValue(users).
                                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        //    progressbar GONE
                                                        signUp_progress.setVisibility(View.GONE);
                                                        Toast.makeText(StaffRegisterationActivity.this, "Successful Registered", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(StaffRegisterationActivity.this, HomeActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                    } else {
                                        //    progressbar GONE
                                        signUp_progress.setVisibility(View.GONE);
                                        Toast.makeText(StaffRegisterationActivity.this, "Check Email id or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(StaffRegisterationActivity.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validatefirstName() {
        firstName = edit_txt_firstName.getText().toString().trim();
        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your Full Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateuserName() {
        userName = edit_txt_userName.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your User Name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateEmail() {
        email = edit_txt_email.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(StaffRegisterationActivity.this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validateMobile() {
        mobile = edit_txt_mobile.getText().toString().trim();
        return true;
    }
    private boolean validateDesignation(){
        Designation = edit_txt_Designation.getText().toString().trim();
        return true;
    }
    private boolean validatePassword() {
        password = edit_txt_pass.getText().toString().trim();
        co_password = edit_txt_coPass.getText().toString().toLowerCase();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(co_password)) {
            Toast.makeText(StaffRegisterationActivity.this, "Enter Your Co-Password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean checkUserGender() {
        if (radioMale.isChecked()) {
            gender = "Male";
            return false;
        }
        if (radioFemale.isChecked()) {
            gender = "Female";
            return false;
        } else {
            Toast.makeText(StaffRegisterationActivity.this, "Select Your Gender", Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    //    if the user already logged in then it will automatically send on Dashboard/MainActivity activity.
    @Override
    public void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(StaffRegisterationActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }
}