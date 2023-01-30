package com.example.unilearn;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Year_Choice extends AppCompatActivity {

    ListView listview;
    Toolbar toolbar;




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year__choice);

        listview = findViewById(R.id.listview);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(Year_Choice.this
                ,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.YearChoice));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i =new Intent(Year_Choice.this,HomeActivity.class);
                i.putExtra("YearChoice",listview.getItemAtPosition(position).toString());
                startActivity(i);
            }
        });
        listview.setAdapter(mAdapter);




    }
}