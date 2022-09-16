package com.example.ameyaclassicclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ameyaclassicclub.config.ProjectConstants;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private EditText txtemail, txtpassoword;
    private Button login_btn;
    private TextView text_view_signup, forgot_password;
    ProgressBar login_progress;
    FirebaseAuth mAuth;
    String loginemail, loginpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtemail = findViewById(R.id.edit_txt_login_email);
        txtpassoword = findViewById(R.id.edit_txt_login_pass);
        forgot_password = findViewById(R.id.text_view_forget_password);
        login_progress = findViewById(R.id.login_progress);
        text_view_signup = findViewById(R.id.text_view_signup);
        login_btn = findViewById(R.id.button_login);
        //        Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        //        handle login button
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()) {
                    return;
                }
                //    progressbar VISIBLE
                login_progress.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(loginemail, loginpassword).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //    progressbar GONE
                                login_progress.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    //    progressbar GONE
                                    login_progress.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        //        handle forgot button
//        forgot_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
//                startActivity(intent);
//            }
//        });
//        text_view_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), MemberRegisterationActivity.class);
//                startActivity(intent);
//            }
//        });

        }
    private boolean validateEmail() {
        loginemail = txtemail.getText().toString().trim();
        if (TextUtils.isEmpty(loginemail)) {
            Toast.makeText(LoginActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(loginemail).matches()) {
            Toast.makeText(LoginActivity.this, "Please enter valid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    private boolean validatePassword() {
        loginpassword = txtpassoword.getText().toString().trim();
        if (TextUtils.isEmpty(loginpassword)) {
            Toast.makeText(LoginActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
    public void registerMember(View v)
    {
        TextView tv= (TextView) findViewById(R.id.text_view_signup);

        //alter text of textview widget
        tv.setText("Register Member");

        //assign the textview forecolor
        tv.setTextColor(Color.GREEN);
        Intent intent = new Intent(getApplicationContext(), MemberRegisterationActivity.class);
        startActivity(intent);
    }
}