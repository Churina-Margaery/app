package com.example.app.ui.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.text.InputFilter;
import android.text.Spanned;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private String[] sexes = {"Мужчина", "Женщина"};
    private String[] activities = {"Низкий", "Умеренный", "Высокий"};

    Spinner sex;
    Spinner activity;
    EditText editAmount;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        sex = rootView.findViewById(R.id.sexes);
        initsexspinnerfooter();
        activity = rootView.findViewById(R.id.activities);
        initactivityspinnerfooter();
        editAmount = rootView.findViewById(R.id.editAmountText);
        editAmount.setFilters(new InputFilter[]{new MinMaxFilter(1, 30)});
        return rootView;
    }

    public class MinMaxFilter implements InputFilter {
        private int minValue;
        private int maxValue;

        public MinMaxFilter(int minValue, int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dStart, int dEnd) {
            try {
                String input = dest.toString() + source.toString();
                int value = Integer.parseInt(input);
                if (value >= minValue && value <= maxValue) {
                    return null; // Accept the input
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            // Reject the input
            return "";
        }
    }

    private void initsexspinnerfooter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, sexes);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        sex.setAdapter(adapter);
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void initactivityspinnerfooter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, activities);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        activity.setAdapter(adapter);
        activity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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