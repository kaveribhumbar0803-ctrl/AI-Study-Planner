package com.example.aistudyplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressActivity extends AppCompatActivity {

    ProgressBar studyProgress;
    TextView tvProgress;
    TextView tvMotivation;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        studyProgress = findViewById(R.id.studyProgress);
        tvProgress = findViewById(R.id.tvProgress);
        tvMotivation = findViewById(R.id.tvMotivation);

        databaseHelper = new DatabaseHelper(this);

        calculateProgress();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (databaseHelper != null) {
            calculateProgress();
        }
    }

    private void calculateProgress() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*), " +
                        "SUM(CASE WHEN completed = 1 THEN 1 ELSE 0 END) " +
                        "FROM tasks",
                null
        );

        int totalTasks = 0;
        int completedTasks = 0;

        if (cursor.moveToFirst()) {

            totalTasks = cursor.getInt(0);

            if (!cursor.isNull(1)) {
                completedTasks = cursor.getInt(1);
            }
        }

        cursor.close();
        db.close();

        int progress = 0;

        if (totalTasks > 0) {
            progress = (completedTasks * 100) / totalTasks;
        }

        studyProgress.setProgress(progress);

        tvProgress.setText(
                progress + "% Complete\n" +
                        completedTasks + " of " +
                        totalTasks + " tasks completed"
        );

        if (totalTasks == 0) {

            tvMotivation.setText(
                    "Start by adding your first study task!"
            );

        } else if (progress < 30) {

            tvMotivation.setText(
                    "Good start! Keep working on your study plan."
            );

        } else if (progress < 70) {

            tvMotivation.setText(
                    "Great progress! Keep going!"
            );

        } else if (progress < 100) {

            tvMotivation.setText(
                    "Almost there! Finish your remaining tasks."
            );

        } else {

            tvMotivation.setText(
                    "Excellent! You completed your study plan!"
            );
        }
    }
}