package com.example.graduationproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.IOException;
import java.net.URL;

public class faceid extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100;
    private ImageView faceAnimation;
    private Button captureButton;
    private facenet facenet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faceid);

        faceAnimation = findViewById(R.id.face_animation);
        captureButton = findViewById(R.id.capture_button);

        try {
            facenet = new facenet(getAssets(), "facenet.tflite");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Model load error", Toast.LENGTH_LONG).show();
            return;
        }

        captureButton.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap capturedImage = (Bitmap) extras.get("data");

            faceAnimation.setImageBitmap(capturedImage); // عرض الصورة

            float[] capturedEmbedding = facenet.getFaceEmbedding(capturedImage); // استخراج الميزة

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users").get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        String imageUrl = document.getString("imageUrl");

                        new Thread(() -> {
                            try {
                                URL url = new URL(imageUrl);
                                Bitmap storedBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                float[] storedEmbedding = facenet.getFaceEmbedding(storedBitmap);

                                float similarity = facenet.cosineSimilarity(capturedEmbedding, storedEmbedding);

                                if (similarity > 0.90f) {
                                    runOnUiThread(() ->
                                            Toast.makeText(faceid.this, "Welcome " + name + "!", Toast.LENGTH_LONG).show()
                                    );
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
                }
            });
        }
    }
}
