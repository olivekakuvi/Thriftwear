package com.example.unilearn;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayData extends AppCompatActivity {

    DBmain dbmain;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    String[]name,name2,name3,name4,name5;
    int[]id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dbmain = new DBmain(DisplayData.this);
        findid();
        displayData();

    }

    private void displayData() {
        sqLiteDatabase = dbmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from course",null);
        if(cursor.getCount()>0){
            id = new int[cursor.getCount()];
            name = new String[cursor.getCount()];
            name2 = new String[cursor.getCount()];
            name3 = new String[cursor.getCount()];
            name4 = new String[cursor.getCount()];
            name5 = new String[cursor.getCount()];
            int i = 0;
            while(cursor.moveToNext()){
                id[i]= cursor.getInt(0);
                name[i]= cursor.getString(1);
                name2[i]= cursor.getString(2);
                name3[i]= cursor.getString(3);
                name4[i]= cursor.getString(4);
                name5[i]= cursor.getString(5);
                i++;
            }
            Custom custom = new Custom();
            listView.setAdapter(custom);
        }
    }

    private void findid() {
        listView = findViewById(R.id.lv);


    }

    private class Custom extends BaseAdapter {

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView,textView1,textView2,textView3,textView4;
            convertView = LayoutInflater.from(DisplayData.this).inflate(R.layout.singledata,parent,false);
            textView = (TextView)convertView.findViewById(R.id.tv1);
            textView1 = (TextView)convertView.findViewById(R.id.tv2);
            textView2 = (TextView)convertView.findViewById(R.id.tv3);
            textView3 = (TextView)convertView.findViewById(R.id.tv4);
            textView4 = (TextView)convertView.findViewById(R.id.tv5);

            textView.setText(name[position]);
            textView1.setText(name2[position]);
            textView2.setText(name3[position]);
            textView3.setText(name4[position]);
            textView4.setText(name5[position]);

            return convertView;
        }
    }
}