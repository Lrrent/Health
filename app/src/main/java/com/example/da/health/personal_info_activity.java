package com.example.da.health;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pc on 2017/4/15.
 */
public class personal_info_activity extends AppCompatActivity {
    private myDB database;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info_layout);

        final EditText gender = (EditText)findViewById(R.id.gender);
        final EditText height = (EditText)findViewById(R.id.height);
        final EditText weight = (EditText)findViewById(R.id.weight);

        database = new myDB(personal_info_activity.this);

        //从数据库加载最新记录到EditText
        final Cursor cursor = database.queryPerInfoTable();
        if(cursor.moveToFirst()) {
            int genderIndex = cursor.getColumnIndex("gender");
            int heightIndex = cursor.getColumnIndex("height");
            int weightIndex = cursor.getColumnIndex("weight");
            String genderFromDB = cursor.getString(genderIndex);
            String heightFromDB = cursor.getString(heightIndex);
            String weightFromDB = cursor.getString(weightIndex);
            cursor.close();//注意这里游标要关掉
            gender.setText(genderFromDB);
            height.setText(heightFromDB);
            weight.setText(weightFromDB);
        }
        Button saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
                Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                String date = formatter.format(curDate);
                if(database.isDuplicated_PerInfoTable(date)){ //当天信息已记录，update
                    database.update2PerInfoTable(date,gender.getText().toString(),height.getText().toString(),weight.getText().toString());
                }
                else{ //当天信息还未记录，insert
                    database.insert2PerInfoTable(date,gender.getText().toString(),height.getText().toString(),weight.getText().toString());
                }

                personal_info_activity.this.finish();
            }
        });
    }
}
