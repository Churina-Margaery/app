package com.example.app.ui.training;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import java.util.Calendar;


import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.app.DataSingleton;

public class ConfirmExit extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Завершение";
        String message = "Вы хотите завершить тренировку?";
        String button1String = "Да";
        String button2String = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                getActivity().finish();
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Calendar calendar = Calendar.getInstance();
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DATE);

                DataSingleton.getInstance().updateCalendar(month, day);
                getActivity().finish();
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}