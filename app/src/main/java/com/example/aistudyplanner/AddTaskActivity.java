package com.example.aistudyplanner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    EditText etTaskName;
    EditText etSubject;
    EditText etTaskHours;
    EditText etPriority;
    EditText etDeadline;
    Button btnSaveTask;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etTaskName = findViewById(R.id.etTaskName);
        etSubject = findViewById(R.id.etSubject);
        etTaskHours = findViewById(R.id.etTaskHours);
        etPriority = findViewById(R.id.etPriority);
        etDeadline = findViewById(R.id.etDeadline);
        btnSaveTask = findViewById(R.id.btnSaveTask);

        databaseHelper = new DatabaseHelper(this);

        btnSaveTask.setOnClickListener(v -> {

            String taskName = etTaskName.getText().toString().trim();
            String subject = etSubject.getText().toString().trim();
            String hours = etTaskHours.getText().toString().trim();
            String priority = etPriority.getText().toString().trim();
            String deadline = etDeadline.getText().toString().trim();

            if (taskName.isEmpty() ||
                    subject.isEmpty() ||
                    hours.isEmpty() ||
                    priority.isEmpty() ||
                    deadline.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            boolean inserted = databaseHelper.addTask(
                    taskName,
                    subject,
                    hours,
                    priority,
                    deadline
            );

            if (inserted) {

                Toast.makeText(
                        this,
                        "Study task saved successfully!",
                        Toast.LENGTH_SHORT
                ).show();

                etTaskName.setText("");
                etSubject.setText("");
                etTaskHours.setText("");
                etPriority.setText("");
                etDeadline.setText("");

            } else {

                Toast.makeText(
                        this,
                        "Failed to save task",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}