package com.mindease;

import com.mindease.R;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class BreathingExerciseActivity extends AppCompatActivity {
    private TextView instructionText;
    private TextView timerText;
    private CountDownTimer breathingTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_exercise);

        instructionText = findViewById(R.id.instructionText);
        timerText = findViewById(R.id.timerText);
        Button stopButton = findViewById(R.id.stopButton);

        stopButton.setOnClickListener(v -> {
            if (breathingTimer != null) {
                breathingTimer.cancel();
            }
            finish();
        });

        startBreathingExercise();
    }

    private void startBreathingExercise() {
        instructionText.setText(R.string.breathe_in);
        
        breathingTimer = new CountDownTimer(19000, 1000) { // Total 19s per cycle (4+7+8)
            int cycle = 0;
            int phase = 0; // 0=inhale, 1=hold, 2=exhale
            
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = (millisUntilFinished / 1000) + 1;
                timerText.setText(String.valueOf(secondsRemaining));
                
                if (phase == 0 && secondsRemaining <= 15) { // After 4s inhale
                    instructionText.setText(R.string.hold_breath);
                    phase = 1;
                } else if (phase == 1 && secondsRemaining <= 8) { // After 7s hold
                    instructionText.setText(R.string.breathe_out);
                    phase = 2;
                }
            }

            @Override
            public void onFinish() {
                cycle++;
                if (cycle < 4) { // 4 cycles of 4-7-8
                    phase = 0;
                    instructionText.setText(R.string.breathe_in);
                    startBreathingExercise();
                } else {
                    instructionText.setText(R.string.exercise_complete);
                    timerText.setText("");
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (breathingTimer != null) {
            breathingTimer.cancel();
        }
    }
}