package com.example.graduationproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class facultyatt extends AppCompatActivity {

    ImageView backArrow;
    TextView userNameText;
    TextView attendancePercentage;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facultyatt);

        backArrow = findViewById(R.id.backArrow);
        userNameText = findViewById(R.id.btn_faculty);
        attendancePercentage = findViewById(R.id.percentageText);

        userNameText.setText("Ms. Dhanalakshmi Venugopal.");
        attendancePercentage.setText("90.5%");

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


