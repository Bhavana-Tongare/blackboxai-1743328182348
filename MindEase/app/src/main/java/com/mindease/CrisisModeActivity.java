package com.mindease;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class CrisisModeActivity extends AppCompatActivity {
    private Button emergencyButton;
    private Button breathingButton;
    private Button groundingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crisis_mode);

        emergencyButton = findViewById(R.id.emergencyButton);
        breathingButton = findViewById(R.id.breathingButton);
        groundingButton = findViewById(R.id.groundingButton);

        emergencyButton.setOnClickListener(v -> callEmergency());
        breathingButton.setOnClickListener(v -> startBreathingExercise());
        groundingButton.setOnClickListener(v -> startGroundingExercise());
    }

    private void callEmergency() {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:911")); // Replace with actual crisis hotline number
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Could not initiate call", Toast.LENGTH_SHORT).show();
        }
    }

    private void startBreathingExercise() {
        Intent intent = new Intent(this, BreathingExerciseActivity.class);
        startActivity(intent);
    }

    private void startGroundingExercise() {
        // TODO: Implement grounding exercise activity
        Toast.makeText(this, "Starting grounding technique", Toast.LENGTH_SHORT).show();
    }
}