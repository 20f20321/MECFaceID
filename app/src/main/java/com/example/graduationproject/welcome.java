package com.example.graduationproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends Activity {

    Button btnStudent, btnFaculty, btnEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        btnStudent = findViewById(R.id.btn_student);
        btnFaculty = findViewById(R.id.btn_faculty);
        btnEmployee = findViewById(R.id.btn_employee);

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent studentIntent = new Intent(welcome.this,login.class);
                startActivity(studentIntent);
            }
        });

        btnFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent facultyIntent = new Intent(welcome.this, login.class);
                startActivity(facultyIntent);
            }
        });

        btnEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent employeeIntent = new Intent(welcome.this, login.class);
                startActivity(employeeIntent);
            }
        });
    }
}

