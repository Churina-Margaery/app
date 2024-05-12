package com.example.app.ui.training;

import static com.example.app.R.id.button;
import static com.example.app.R.id.button2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
    com.google.android.material.textfield.MaterialAutoCompleteTextView type1;
    com.google.android.material.textfield.MaterialAutoCompleteTextView level1;
    Button btnStartTraining;
    Button btnStartHealth;

    AutoCompleteTextView autoCompleteTxtType;
    ArrayAdapter<String> adapterItemsType;
    AutoCompleteTextView autoCompleteTxtLev;
    ArrayAdapter<String> adapterItemsLev;


    @SuppressLint("WrongViewCast")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_training, container, false);

        type1 = rootView.findViewById(R.id.type);

        level1 = rootView.findViewById(R.id.level);

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

    @Override
    public void onResume() {
        super.onResume();
        autoCompleteTxtType = requireView().findViewById(R.id.type);
        adapterItemsType = new ArrayAdapter<String>(requireContext(), R.layout.drop_down_item, types);
        autoCompleteTxtType.setAdapter(adapterItemsType);
        autoCompleteTxtType.requestFocus();

        autoCompleteTxtLev = requireView().findViewById(R.id.level);
        adapterItemsLev = new ArrayAdapter<String>(requireContext(), R.layout.drop_down_item, levels);
        autoCompleteTxtLev.setAdapter(adapterItemsLev);
        autoCompleteTxtLev.requestFocus();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}