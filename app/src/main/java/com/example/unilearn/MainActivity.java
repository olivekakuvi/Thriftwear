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

public class MainActivity extends AppCompatActivity {


    private EditText emailAddress, Password;
    private Button signup;
     TextView tvSignin;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAddress = findViewById(R.id.email);
        Password = findViewById(R.id.password);

        tvSignin = findViewById(R.id.already);
        signup = findViewById(R.id.signup);

        auth = FirebaseAuth.getInstance();

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(k);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailAddress.getText().toString();
                String pwd = Password.getText().toString();

                if (email.isEmpty()) {
                    emailAddress.setError("Enter email!");
                    emailAddress.requestFocus();

                } else if (pwd.isEmpty()) {
                    Password.setError("Enter Password!");
                    Password.requestFocus();

                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();

                }
                else if (!(email.isEmpty() && pwd.isEmpty())) {


                    auth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign Up successful",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this,Year_Choice.class);
                                startActivity(i);

                            } else {


                                Toast.makeText(MainActivity.this, "Error Occurred failed", Toast.LENGTH_SHORT).show();

                            }
                        }


                    });

                } else {
                    Toast.makeText(MainActivity.this, "Error Occurred failed", Toast.LENGTH_SHORT).show();

                }


            }
        });




            }

}
