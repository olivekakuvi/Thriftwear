package com.example.unilearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {


    private EditText emailAddress2, Password2;
    private Button signup2;
    private TextView tvSignup;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth =FirebaseAuth.getInstance();

        emailAddress2 = findViewById(R.id.email2);
        Password2 = findViewById(R.id.password2);

        tvSignup = findViewById(R.id.notsigned);
        signup2 = findViewById(R.id.signin2);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                if( user != null){
                    Toast.makeText(LoginActivity.this,"You are Logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(LoginActivity.this,"Please login",Toast.LENGTH_SHORT).show();

                }

            }
        };



        signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = emailAddress2.getText().toString();
                String pwd2 = Password2.getText().toString();

                if (email2.isEmpty()) {
                    emailAddress2.setError("Enter email!");
                    emailAddress2.requestFocus();

                } else if (pwd2.isEmpty()) {
                    Password2.setError("Enter Password!");
                    Password2.requestFocus();

                } else if (email2.isEmpty() && pwd2.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();

                } else if (!(email2.isEmpty() && pwd2.isEmpty())) {

                    mFirebaseAuth.signInWithEmailAndPassword(email2, pwd2).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Error,Try again", Toast.LENGTH_SHORT).show();
                            }else{
                                Intent toHome = new Intent(LoginActivity.this,Year_Choice.class);
                                startActivity(toHome);
                            }

                        }
                    });

                } else {
                    Toast.makeText(LoginActivity.this, "Error Occurred failed", Toast.LENGTH_SHORT).show();

                }

            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignup = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(toSignup);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
       // mFirebaseAuth.addAuthStateListener(mAuthListener);
    }
}
