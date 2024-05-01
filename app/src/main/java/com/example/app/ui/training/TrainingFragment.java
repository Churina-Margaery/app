package com.example.app.ui.training;

import static com.example.app.R.id.button;
import static com.example.app.R.id.button2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app.R;
import com.example.app.databinding.FragmentTrainingBinding;

public class TrainingFragment extends Fragment {

    private FragmentTrainingBinding binding;
    private String[] types = {"Выносливость", "Сила", "Равновесие"};
    private String[] levels = {"Легкий", "Умеренный", "Интенсивный"};
    Spinner type1;
    Spinner level1;
    Button btnStartTraining;
    Button btnStartHealth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_training, container, false);

        type1 = rootView.findViewById(R.id.type);
        inittypespinnerfooter();

        level1 = rootView.findViewById(R.id.level);
        initlevelspinnerfooter();

        btnStartTraining = (Button) rootView.findViewById(button2);
        btnStartHealth = (Button) rootView.findViewById(button);

        View.OnClickListener oclBtnOk = (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), WorkoutActivity.class);
                startActivity(intent);
            }
        });

        View.OnClickListener onClkBtnHealth = (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHealth = new Intent(getActivity(), EstimationOfHealth.class);
                startActivity(intentHealth);
            }
        });

        btnStartHealth.setOnClickListener(onClkBtnHealth);
        btnStartTraining.setOnClickListener(oclBtnOk);

        return rootView;
    }

    private void inittypespinnerfooter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        type1.setAdapter(adapter);
        type1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void initlevelspinnerfooter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, levels);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        level1.setAdapter(adapter);
        level1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}