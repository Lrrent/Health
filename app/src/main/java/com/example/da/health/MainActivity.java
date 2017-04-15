package com.example.da.health;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.da.health.chat.ButlerActivity;

public class MainActivity extends AppCompatActivity {
    private CirclePercentView mCirclePercentView;

    private ImageView heartRate;
    private ImageView personMsg;
    private ImageView data;
    private ImageView search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findAllView();
        setListener();
        mCirclePercentView.setPercent(45);
    }
    private void findAllView(){
        heartRate = (ImageView) findViewById(R.id.heartRate);
        personMsg = (ImageView) findViewById(R.id.personMsg);
        data = (ImageView) findViewById(R.id.data);
        search = (ImageView) findViewById(R.id.search);
        mCirclePercentView = (CirclePercentView) findViewById(R.id.circleView);
    }
    private void setListener(){
        heartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        personMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,personal_info_activity.class);
               // MainActivity.this.finish();
                startActivity(intent);
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ButlerActivity.class);
               // MainActivity.this.finish();
                startActivity(intent);
            }
        });
    }

    //图片上传

}
