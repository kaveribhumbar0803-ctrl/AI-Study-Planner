package com.example.aistudyplanner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddSubjectActivity extends AppCompatActivity {

    EditText etSubjectName;
    EditText etStudyHours;
    Button btnSaveSubject;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        etSubjectName = findViewById(R.id.etSubjectName);
        etStudyHours = findViewById(R.id.etStudyHours);
        btnSaveSubject = findViewById(R.id.btnSaveSubject);

        databaseHelper = new DatabaseHelper(this);

        btnSaveSubject.setOnClickListener(v -> {

            String subjectName =
                    etSubjectName.getText().toString().trim();

            String studyHours =
                    etStudyHours.getText().toString().trim();

            if (subjectName.isEmpty() || studyHours.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            boolean inserted =
                    databaseHelper.addSubject(
                            subjectName,
                            studyHours
                    );

            if (inserted) {

                Toast.makeText(
                        this,
                        "Subject saved successfully!",
                        Toast.LENGTH_SHORT
                ).show();

                etSubjectName.setText("");
                etStudyHours.setText("");

            } else {

                Toast.makeText(
                        this,
                        "Failed to save subject",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}