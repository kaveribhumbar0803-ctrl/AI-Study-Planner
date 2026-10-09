package com.example.aistudyplanner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ViewTasksActivity extends AppCompatActivity {

    TextView tvNoTasks;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        tvNoTasks = findViewById(R.id.tvNoTasks);
        databaseHelper = new DatabaseHelper(this);

        showTasks();
    }

    private void showTasks() {

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id, task_name, subject, hours, priority, deadline, completed FROM tasks",
                null
        );

        if (cursor.getCount() == 0) {

            tvNoTasks.setText("No study tasks added yet.");

        } else {

            StringBuilder tasks = new StringBuilder();

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                String taskName = cursor.getString(1);
                String subject = cursor.getString(2);
                String hours = cursor.getString(3);
                String priority = cursor.getString(4);
                String deadline = cursor.getString(5);
                int completed = cursor.getInt(6);

                String status;

                if (completed == 1) {
                    status = "✓ Completed";
                } else {
                    status = "○ Pending";
                }

                tasks.append("Task ID: ")
                        .append(id)
                        .append("\nTask: ")
                        .append(taskName)
                        .append("\nSubject: ")
                        .append(subject)
                        .append("\nStudy Hours: ")
                        .append(hours)
                        .append("\nPriority: ")
                        .append(priority)
                        .append("\nDeadline: ")
                        .append(deadline)
                        .append("\nStatus: ")
                        .append(status)
                        .append("\n\n");
            }

            tvNoTasks.setText(tasks.toString());

            tvNoTasks.setOnLongClickListener(v -> {
                showTaskOptions();
                return true;
            });
        }

        cursor.close();
        db.close();
    }

    private void showTaskOptions() {

        String[] options = {
                "Mark Task Completed",
                "Update Task",
                "Delete Task"
        };

        new AlertDialog.Builder(this)
                .setTitle("Task Options")
                .setItems(options, (dialog, which) -> {

                    if (which == 0) {
                        showCompleteDialog();
                    } else if (which == 1) {
                        showUpdateDialog();
                    } else {
                        showDeleteDialog();
                    }
                })
                .show();
    }

    private void showCompleteDialog() {

        EditText input = new EditText(this);
        input.setHint("Enter Task ID");

        new AlertDialog.Builder(this)
                .setTitle("Complete Task")
                .setMessage("Enter the Task ID you completed.")
                .setView(input)
                .setPositiveButton("Complete", (dialog, which) -> {

                    String idText = input.getText().toString().trim();

                    if (idText.isEmpty()) {

                        Toast.makeText(
                                this,
                                "Please enter Task ID.",
                                Toast.LENGTH_SHORT
                        ).show();

                        return;
                    }

                    int id = Integer.parseInt(idText);

                    boolean completed = databaseHelper.markTaskCompleted(id);

                    if (completed) {

                        Toast.makeText(
                                this,
                                "Task marked as completed!",
                                Toast.LENGTH_SHORT
                        ).show();

                        showTasks();

                    } else {

                        Toast.makeText(
                                this,
                                "Task ID not found.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showUpdateDialog() {

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 10, 30, 10);

        EditText idInput = new EditText(this);
        idInput.setHint("Task ID");

        EditText taskInput = new EditText(this);
        taskInput.setHint("Task name");

        EditText subjectInput = new EditText(this);
        subjectInput.setHint("Subject");

        EditText hoursInput = new EditText(this);
        hoursInput.setHint("Study hours");

        EditText priorityInput = new EditText(this);
        priorityInput.setHint("Priority");

        EditText deadlineInput = new EditText(this);
        deadlineInput.setHint("Deadline");

        layout.addView(idInput);
        layout.addView(taskInput);
        layout.addView(subjectInput);
        layout.addView(hoursInput);
        layout.addView(priorityInput);
        layout.addView(deadlineInput);

        new AlertDialog.Builder(this)
                .setTitle("Update Study Task")
                .setView(layout)
                .setPositiveButton("Update", (dialog, which) -> {

                    String idText = idInput.getText().toString().trim();
                    String taskName = taskInput.getText().toString().trim();
                    String subject = subjectInput.getText().toString().trim();
                    String hours = hoursInput.getText().toString().trim();
                    String priority = priorityInput.getText().toString().trim();
                    String deadline = deadlineInput.getText().toString().trim();

                    if (idText.isEmpty() ||
                            taskName.isEmpty() ||
                            subject.isEmpty() ||
                            hours.isEmpty() ||
                            priority.isEmpty() ||
                            deadline.isEmpty()) {

                        Toast.makeText(
                                this,
                                "Please fill all fields.",
                                Toast.LENGTH_SHORT
                        ).show();

                        return;
                    }

                    int id = Integer.parseInt(idText);

                    boolean updated = databaseHelper.updateTask(
                            id,
                            taskName,
                            subject,
                            hours,
                            priority,
                            deadline
                    );

                    if (updated) {

                        Toast.makeText(
                                this,
                                "Task updated successfully!",
                                Toast.LENGTH_SHORT
                        ).show();

                        showTasks();

                    } else {

                        Toast.makeText(
                                this,
                                "Task ID not found.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showDeleteDialog() {

        EditText input = new EditText(this);
        input.setHint("Enter Task ID");

        new AlertDialog.Builder(this)
                .setTitle("Delete Study Task")
                .setMessage("Enter the Task ID you want to delete.")
                .setView(input)
                .setPositiveButton("Delete", (dialog, which) -> {

                    String idText = input.getText().toString().trim();

                    if (idText.isEmpty()) {

                        Toast.makeText(
                                this,
                                "Please enter Task ID.",
                                Toast.LENGTH_SHORT
                        ).show();

                        return;
                    }

                    int id = Integer.parseInt(idText);

                    boolean deleted = databaseHelper.deleteTask(id);

                    if (deleted) {

                        Toast.makeText(
                                this,
                                "Task deleted successfully!",
                                Toast.LENGTH_SHORT
                        ).show();

                        showTasks();

                    } else {

                        Toast.makeText(
                                this,
                                "Task ID not found.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}