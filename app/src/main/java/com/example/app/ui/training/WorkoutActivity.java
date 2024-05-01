package com.example.app.ui.training;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import static android.app.PendingIntent.getActivity;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.app.R;
import com.example.app.databinding.ActivityWorkoutBinding;


public class WorkoutActivity extends AppCompatActivity {

    private ActivityWorkoutBinding binding;
    Button btnFinishTraining;
    TextView exercise;
    com.example.app.ui.training.DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;

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

        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();

        exercise = findViewById(R.id.workoutText);
        exercise.setText(extractData());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        db.close();
        userCursor.close();
    }

    private String extractData() {
        String str = "not found";
        try {
            // открываем подключение
            db = databaseHelper.open();
            //получаем данные из бд в виде курсора
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
            // определяем, какие столбцы из курсора будут выводиться в ListView
            String[] headers = new String[]{DatabaseHelper.COLUMN_TYPE, DatabaseHelper.COLUMN_CONTENT};
            // создаем адаптер, передаем в него курсор
            userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                    userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                    DatabaseHelper.COLUMN_TYPE + "=?", new String[]{"сила"});
            userCursor.moveToFirst();

            return userCursor.getString(2);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }
}