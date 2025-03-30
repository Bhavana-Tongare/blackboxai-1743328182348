package com.mindease;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MoodTrackingActivity extends AppCompatActivity {
    private RadioGroup moodRadioGroup;
    private EditText notesEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_tracking);

        moodRadioGroup = findViewById(R.id.moodRadioGroup);
        notesEditText = findViewById(R.id.notesEditText);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> saveMoodEntry());
    }

    private void saveMoodEntry() {
        int selectedId = moodRadioGroup.getCheckedRadioButtonId();
        String notes = notesEditText.getText().toString();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (selectedId == -1) {
            Toast.makeText(this, "Please select a mood", Toast.LENGTH_SHORT).show();
            return;
        }

        String mood = "";
        switch (selectedId) {
            case R.id.happyRadio:
                mood = "happy";
                break;
            case R.id.sadRadio:
                mood = "sad";
                break;
            case R.id.anxiousRadio:
                mood = "anxious";
                break;
            case R.id.angryRadio:
                mood = "angry";
                break;
            case R.id.calmRadio:
                mood = "calm";
                break;
        }

        // TODO: Save to database
        Toast.makeText(this, "Mood saved: " + mood, Toast.LENGTH_SHORT).show();
        finish();
    }
}