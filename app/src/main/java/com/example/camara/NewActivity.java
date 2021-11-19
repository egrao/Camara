package com.example.camara;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class NewActivity extends AppCompatActivity {
    static final int REQUEST_VIDEO_CAPTURE = 1;
    private ImageView imagen;
    private VideoView video;
    private Button button;
    private Button buttonVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

        imagen= findViewById(R.id.imagen1);
        button= findViewById(R.id.buttonImagen);
        buttonVideo= findViewById(R.id.buttonVideo);
        video = findViewById(R.id.video1);

        if (ContextCompat.checkSelfPermission(NewActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NewActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, 100);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

        buttonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(takeVideoIntent, 200);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.start();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imagen.setImageBitmap(bitmap);
        }
        if (requestCode == 200) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            VideoView videoView = new VideoView(this);
//            videoView.setVideoURI(data.getData());
//            videoView.start();
//            builder.setView(videoView).show();
            Uri videoUri = data.getData();
            video.setVideoURI(videoUri);
            video.start();


        }
        }

    }


