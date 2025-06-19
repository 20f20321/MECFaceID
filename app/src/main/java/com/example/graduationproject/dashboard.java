package com.example.graduationproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);


        Button btnTake1 = findViewById(R.id.btnTake1);
        Button btnView1 = findViewById(R.id.btnView1);
        Button btnTake2 = findViewById(R.id.btnTake2);
        Button btnView2 = findViewById(R.id.btnView2);
        Button btnTake3 = findViewById(R.id.btnTake3);
        Button btnView3 = findViewById(R.id.btnView3);


        btnTake1.setOnClickListener(v ->
                Toast.makeText(this, "Taking Attendance for COMP 30081", Toast.LENGTH_SHORT).show());

        btnView1.setOnClickListener(v ->
                Toast.makeText(this, "Viewing Records for COMP 30081", Toast.LENGTH_SHORT).show());

        btnTake2.setOnClickListener(v ->
                Toast.makeText(this, "Taking Attendance for PROJS 30001", Toast.LENGTH_SHORT).show());

        btnView2.setOnClickListener(v ->
                Toast.makeText(this, "Viewing Records for PROJS 30001", Toast.LENGTH_SHORT).show());

        btnTake3.setOnClickListener(v ->
                Toast.makeText(this, "Taking Attendance for PROJS 30002.1", Toast.LENGTH_SHORT).show());

        btnView3.setOnClickListener(v ->
                Toast.makeText(this, "Viewing Records for PROJS 30002.1", Toast.LENGTH_SHORT).show());
    }
}
