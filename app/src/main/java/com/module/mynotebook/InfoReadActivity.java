package com.module.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoReadActivity extends AppCompatActivity {
TextView titletv,descriptiontv,datetv;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_read);
        String title=getIntent().getStringExtra("title");
        String description=getIntent().getStringExtra("description");
        String date=getIntent().getStringExtra("date");
        String imageurl= getIntent().getStringExtra("image");

        titletv=findViewById(R.id.titletv);
        descriptiontv=findViewById(R.id.descriptiontv);
        datetv=findViewById(R.id.datetv);
        Uri uri = Uri.parse(imageurl);
        imageView=findViewById(R.id.image);

        titletv.setText(title);
        descriptiontv.setText(description);
        datetv.setText(date);
        imageView.setImageURI(uri);

    }
}