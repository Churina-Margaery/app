package com.example.app.ui.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private String[] sexes = {"Мужчина", "Женщина"};
    private String[] activities = {"Низкий", "Умеренный", "Высокий"};

    ImageView days5;
    ImageView days10;
    ImageView days30;
    ImageView strength5;
    ImageView strength10;
    ImageView strength30;
    Spinner sexS;
    Spinner activityS;
    Button saveButton;
    EditText editAmount;
    EditText forName;
    EditText forAge;
    Integer amountOfSessions;
    Integer strengthSessions;
    Integer age;
    Integer neededSessions;
    String sex;
    String name;
    String levelOfActivity;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        sexS = rootView.findViewById(R.id.sexes);
        initsexspinnerfooter();
        activityS = rootView.findViewById(R.id.activities);
        initactivityspinnerfooter();
        editAmount = rootView.findViewById(R.id.editAmountText);
        editAmount.setFilters(new InputFilter[]{new MinMaxFilter(1, 30)});
        forName = rootView.findViewById(R.id.editNameText);
        forAge = rootView.findViewById(R.id.editAgeText);
        saveButton = rootView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = Integer.parseInt(String.valueOf(forAge.getText()));
                name = String.valueOf(forName.getText());
                sex = sexS.getSelectedItem().toString();
                levelOfActivity = activityS.getSelectedItem().toString();
                neededSessions = Integer.parseInt(String.valueOf(editAmount.getText()));
            }
        });
        days5 = rootView.findViewById(R.id.imageView);
        days10 = rootView.findViewById(R.id.imageView2);
        days30 = rootView.findViewById(R.id.imageView3);
        amountOfSessions = 13;
        if (amountOfSessions >= 5 )
        {
            days5.setImageResource(R.drawable.fivedays);
        }
        if (amountOfSessions >= 10)
        {
            days10.setImageResource(R.drawable.tendays);
        }
        if (amountOfSessions >= 30)
        {
            days30.setImageResource(R.drawable.thirtydays);
        }
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
        sexS.setAdapter(adapter);
        sexS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        activityS.setAdapter(adapter);
        activityS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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