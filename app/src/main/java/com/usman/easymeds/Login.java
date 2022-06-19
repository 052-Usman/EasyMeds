package com.usman.easymeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    boolean passwordVisible;
    EditText userName, password;
    TextView register, forgotPassword;
    Button signIN;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//Click Listener

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);

        signIN = findViewById(R.id.SignIN);
        signIN.setOnClickListener((View.OnClickListener) this);

        register = findViewById(R.id.Register);
        register.setOnClickListener((View.OnClickListener) this);

        mAuth = FirebaseAuth.getInstance();

        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener((View.OnClickListener) this);


//        register = findViewById(R.id.Register);
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, SignUP.class);
//                startActivity(intent);
//            }
//        });
//
//        signIN = findViewById(R.id.SignIN);
//        signIN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Login.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });


        password = findViewById(R.id.password);

        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right = 2;
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX() >= password.getRight() - password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection = password.getSelectionEnd();
                        if (passwordVisible) {
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visibility_off, 0);
                            //for hide password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible = false;
                        }
                        else{
                            //set drawable image here
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.visibility_on, 0);
                            //for show password
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible = true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Register:
                startActivity(new Intent(this,SignUP.class));
                break;
            case R.id.SignIN:
                userLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String UserName = userName.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(UserName.isEmpty()){
            userName.setError("UserName is required!");
            userName.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(UserName).matches()){
            userName.setError("Please provide valid UserName!");
            userName.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if (password.length()<6){
            password.setError("Password length should be 6 characters or more!");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(UserName,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(Login.this,MainActivity.class));
                        finish();
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(Login.this, "Check your email to verify your account.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }else{
                    Toast.makeText(Login.this, "Failed to login please check credentials!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}