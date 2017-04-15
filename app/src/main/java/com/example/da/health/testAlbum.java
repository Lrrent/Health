package com.example.da.health;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class testAlbum extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_album);

        Button button = (Button)findViewById(R.id.b01);
        button.setText("选择图片");
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                linkAlbum();
            }

        });
    }
    private void linkAlbum(){
        Intent intent = new Intent();
		        /* 开启Pictures画面Type设定为image */
        intent.setType("image/*");
		        /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_GET_CONTENT);
		        /* 取得相片后返回本画面 */
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
           // File file = new File(uri.getPath());
            AsyncTask<String,String,String> test = new AsyncTask<String, String, String>() {
                @Override
                protected String doInBackground(String... params) {
                    try {
                        UploadUtil.uploadFile(new File(params[0].toString()),"http://www.baidu.com");
                    } catch (IOException e) {
                        e.printStackTrace();
                     //   Log.i(e.getMessage(), e.getMessage().toString());
                    }
                    return null;
                }
            };
            test.execute(uri.getPath());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                ImageView imageView = (ImageView) findViewById(R.id.iv01);
				/* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}