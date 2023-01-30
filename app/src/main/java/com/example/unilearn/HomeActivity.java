package com.example.unilearn;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {


    private Button logout;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    Toolbar mToolbar;
    DBmain dbmain;
    SQLiteDatabase sqLiteDatabase;
    Button submit,display;
    CheckBox ccna,cisco,py,huawei,compApps;
    TextView heading;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //mToolbar = findViewById(R.id.toolbar2);

       // Bundle bundle = getIntent().getExtras();
       // if (bundle != null) {
       //     mToolbar.setTitle(bundle.getString("YearChoice"));

       // }


        dbmain = new DBmain(HomeActivity.this);
        submit = findViewById(R.id.sumbitBtn);
        display = findViewById(R.id.displayBtn);
        ccna = findViewById(R.id.CCNA);
        cisco = findViewById(R.id.CISCO);
        py = findViewById(R.id.PF);
        huawei = findViewById(R.id.HUAWEI);
        compApps = findViewById(R.id.CA);




            logout = findViewById(R.id.logout);

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FirebaseAuth.getInstance().signOut();


                    Intent backtoreg = new Intent(HomeActivity.this, MainActivity.class);
                    startActivity(backtoreg);
                }
            });


        insertData();
        }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = dbmain.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                if(ccna.isChecked())
                    contentValues.put("sub",ccna.getText().toString());
                if(cisco.isChecked())
                    contentValues.put("sub2",cisco.getText().toString());
                if(py.isChecked())
                    contentValues.put("sub3",py.getText().toString());
                if(huawei.isChecked())
                    contentValues.put("sub4",huawei.getText().toString());
                if(compApps.isChecked())
                    contentValues.put("sub5",compApps.getText().toString());

                Long rec = sqLiteDatabase.insert("course",null,contentValues);
                if (rec != null){
                    Toast.makeText(HomeActivity.this, "data inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(HomeActivity.this, "data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //display data
            display.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HomeActivity.this, DisplayData.class));
                }
            });

    }
}

