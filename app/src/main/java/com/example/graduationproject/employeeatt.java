package com.example.graduationproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class employeeatt extends AppCompatActivity {

    ImageView backArrow;
    TextView userNameText;
    TextView attendancePercentage;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employeeatt);

        backArrow = findViewById(R.id.backArrow);
        userNameText = findViewById(R.id.btn_employee);
        attendancePercentage = findViewById(R.id.percentageText);

        userNameText.setText("Maryam Mohammed");
        attendancePercentage.setText("70%");

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


