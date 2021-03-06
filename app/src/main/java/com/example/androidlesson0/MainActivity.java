package com.example.androidlesson0;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import static com.example.androidlesson0.R.id.AnonymousOrNormal;

public class MainActivity extends AppCompatActivity {
    static final int CAMERA_REQUEST = 1;
    Button buttonTags;
    Button buttonPostAs;
    ImageButton cameraButton;
    ConstraintLayout.LayoutParams layoutParams;
    ConstraintLayout anonymousOrNot;
    ConstraintLayout mainLayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anonymousOrNot = findViewById(AnonymousOrNormal);
        mainLayout = findViewById(R.id.MainLayout);

        buttonTags = findViewById(R.id.buttonTags);

        buttonTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonTags.setText("Clicked");
            }
        });

        buttonPostAs = findViewById(R.id.buttonPostAs);

        buttonPostAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPostAs.setText("Clicked");
                //Snackbar.make(findViewById(R.id.AnonymousOrNormal),Snackbar.LENGTH_SHORT).show();
            }
        });

        cameraButton = findViewById(R.id.cameraButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

}
