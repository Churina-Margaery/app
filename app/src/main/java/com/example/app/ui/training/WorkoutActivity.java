package com.example.app.ui.training;

import android.os.Bundle;
import static android.app.PendingIntent.getActivity;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.app.R;
import com.example.app.databinding.ActivityWorkoutBinding;
import com.example.app.ui.training.ConfirmExit;


public class WorkoutActivity extends AppCompatActivity {

    private ActivityWorkoutBinding binding;
    Button btnFinishTraining;
    TextView exercise;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnFinishTraining = (Button) findViewById(R.id.buttonFinish);
        View.OnClickListener oclBtnOk = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                ConfirmExit confirmExitFragment = new ConfirmExit();
                confirmExitFragment.show(manager, "Choice");
            }
        });
        btnFinishTraining.setOnClickListener(oclBtnOk);

        exercise = findViewById(R.id.workoutText);
        exercise.setText(extractData());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private String extractData() {
        return "Exercise text";
    }
}