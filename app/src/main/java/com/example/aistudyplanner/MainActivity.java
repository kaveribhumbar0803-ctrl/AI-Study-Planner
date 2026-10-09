package com.example.aistudyplanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnAddSubject;
    Button btnAddTask;
    Button btnViewTasks;
    Button btnProgress;
    Button btnAISuggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddSubject = findViewById(R.id.btnAddSubject);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnViewTasks = findViewById(R.id.btnViewTasks);
        btnProgress = findViewById(R.id.btnProgress);
        btnAISuggestion = findViewById(R.id.btnAISuggestion);

        btnAddSubject.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        AddSubjectActivity.class
                )));

        btnAddTask.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        AddTaskActivity.class
                )));

        btnViewTasks.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        ViewTasksActivity.class
                )));

        btnProgress.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        ProgressActivity.class
                )));

        btnAISuggestion.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        AISuggestionActivity.class
                )));
    }
}