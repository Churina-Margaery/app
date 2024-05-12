package com.example.app;

public class DataSingleton {

    private static final DataSingleton INSTANCE = new DataSingleton();


    private Boolean[][] training = new Boolean[12][31];

    private DataSingleton(){}


    public static DataSingleton getInstance(){
        return INSTANCE;
    }

    public void updateCalendar(int month, int day) {
        if ((month <= 12) & (day <= 31)) {
            training[month][day] = true;
        }
    }

    public Boolean[][] getCalendar(int month, int day) {
        return training;
    }


}
