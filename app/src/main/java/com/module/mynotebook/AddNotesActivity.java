package com.module.mynotebook;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddNotesActivity extends AppCompatActivity {

Button Savebtn,add_image;
EditText titleet,descriptionet;
String saveCurrentDate;
    private ImageView image;
    private static final int GalleryPick=1;
     Uri imageuri;

    Adapter adapter;
    private ArrayList<Note> courseModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        Savebtn=findViewById(R.id.savebtn);
        titleet=findViewById(R.id.title);
        descriptionet=findViewById(R.id.description);
        image=findViewById(R.id.imagev);
        add_image=findViewById(R.id.add_image);

        loadData();
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());

        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });
        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                courseModalArrayList.add(new Note(titleet.getText().toString(),
                        descriptionet.getText().toString(),saveCurrentDate,imageuri.toString()));

                saveData();

                startActivity(new Intent(getApplicationContext(),HomeScreenActivity.class));
            }
        });
    }


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(courseModalArrayList);

        editor.putString("courses", json);

        editor.apply();
    }
    private void loadData() {

        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        String json = sharedPreferences.getString("courses", null);
        Type type = new TypeToken<ArrayList<Note>>() {}.getType();

        courseModalArrayList = gson.fromJson(json, type);

        if (courseModalArrayList == null) {
            courseModalArrayList = new ArrayList<>();
        }
    }
    private void OpenGallery() {
        Intent galleryIntent =new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GalleryPick && resultCode==RESULT_OK && data!=null){
            imageuri = data.getData();
            image.setImageURI(imageuri);
        }
    }

}