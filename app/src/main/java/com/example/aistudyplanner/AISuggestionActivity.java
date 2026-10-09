package com.example.aistudyplanner;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AISuggestionActivity extends AppCompatActivity {

    EditText etStudyTopic;
    Button btnGenerateSuggestion;
    TextView tvAISuggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aisuggestion);

        etStudyTopic = findViewById(R.id.etStudyTopic);
        btnGenerateSuggestion = findViewById(R.id.btnGenerateSuggestion);
        tvAISuggestion = findViewById(R.id.tvAISuggestion);

        btnGenerateSuggestion.setOnClickListener(v -> {

            String topic = etStudyTopic.getText()
                    .toString()
                    .trim();

            if (topic.isEmpty()) {

                tvAISuggestion.setText(
                        "Please enter a subject or topic."
                );

                return;
            }

            String lowerTopic = topic.toLowerCase();

            String suggestion;

            if (lowerTopic.contains("math")
                    || lowerTopic.contains("mathematics")) {

                suggestion =
                        "AI Study Plan for " + topic + "\n\n" +
                                "1. Revise important formulas.\n" +
                                "2. Understand the basic concept.\n" +
                                "3. Solve practice problems.\n" +
                                "4. Review your mistakes.\n" +
                                "5. Take a short self-test.";

            } else if (lowerTopic.contains("mad")
                    || lowerTopic.contains("android")
                    || lowerTopic.contains("mobile")) {

                suggestion =
                        "AI Study Plan for " + topic + "\n\n" +
                                "1. Revise Android fundamentals.\n" +
                                "2. Study Activities and Intents.\n" +
                                "3. Practice XML UI components.\n" +
                                "4. Practice SQLite database operations.\n" +
                                "5. Build and test a small feature.";

            } else if (lowerTopic.contains("dbms")
                    || lowerTopic.contains("database")
                    || lowerTopic.contains("sql")) {

                suggestion =
                        "AI Study Plan for " + topic + "\n\n" +
                                "1. Revise database concepts.\n" +
                                "2. Practice SQL queries.\n" +
                                "3. Study keys and relationships.\n" +
                                "4. Practice CRUD operations.\n" +
                                "5. Solve practical questions.";

            } else if (lowerTopic.contains("java")
                    || lowerTopic.contains("python")
                    || lowerTopic.contains("programming")
                    || lowerTopic.contains("coding")) {

                suggestion =
                        "AI Study Plan for " + topic + "\n\n" +
                                "1. Revise basic syntax.\n" +
                                "2. Understand the main concepts.\n" +
                                "3. Write small programs.\n" +
                                "4. Practice debugging.\n" +
                                "5. Create a small practical project.";

            } else {

                suggestion =
                        "AI Study Plan for " + topic + "\n\n" +
                                "1. Understand the basic concepts.\n" +
                                "2. Study the important topics.\n" +
                                "3. Practice questions.\n" +
                                "4. Revise your notes.\n" +
                                "5. Take a short self-test.";
            }

            tvAISuggestion.setText(suggestion);
        });
    }
}