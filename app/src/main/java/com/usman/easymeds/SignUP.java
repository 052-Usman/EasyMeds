package com.usman.easymeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUP  extends AppCompatActivity implements View.OnClickListener {

    ImageButton backPress;
    Button joinUS;
    TextView signIN;

    private EditText userName, accountName, password, phoneNumber;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //get the credentials from user pressed

        mAuth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.userName);
        accountName = findViewById(R.id.accountName);
        password = findViewById(R.id.password);
        phoneNumber = findViewById(R.id.phoneNumber);

        //Click Listener on button

        backPress = findViewById(R.id.backPress);
        backPress.setOnClickListener((View.OnClickListener) this);

        signIN = findViewById(R.id.signIN);
        signIN.setOnClickListener((View.OnClickListener) this);

        joinUS = findViewById(R.id.joinUS);
        joinUS.setOnClickListener((View.OnClickListener) this);


//        backPress = findViewById(R.id.backPress);
//        backPress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        signIN = findViewById(R.id.signIN);
//        signIN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        joinUS = findViewById(R.id.joinUS);
//        joinUS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backPress:
            case R.id.signIN:
                finish();
                break;
            case R.id.joinUS:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String UserName = userName.getText().toString().trim();
        String AccountName = accountName.getText().toString().trim();
        String Password = password.getText().toString().trim();
        String PhoneNumber = phoneNumber.getText().toString().trim();

        if(UserName.isEmpty()){
            userName.setError("User Name is required!");
            userName.requestFocus();
            return;
        }
        if(AccountName.isEmpty()){
            accountName.setError("Email is required!");
            accountName.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(AccountName).matches()){
            accountName.setError("Please provide valid Email!");
            accountName.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if(PhoneNumber.isEmpty()){
            phoneNumber.setError("Phone Number is required!");
            phoneNumber.requestFocus();
            return;
        }
        if (Password.length()<6){
            password.setError("Password length should be 6 characters or more!");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(AccountName,Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            User user = new User(UserName, AccountName, Password, PhoneNumber);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUP.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                        finish();
                                    }else{
                                        Toast.makeText(SignUP.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUP.this, "Failed to register! Try again.!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}