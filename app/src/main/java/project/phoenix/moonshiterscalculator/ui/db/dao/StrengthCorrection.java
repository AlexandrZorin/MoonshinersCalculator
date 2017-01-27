package project.phoenix.moonshiterscalculator.ui.db.dao;

import android.content.Context;

import project.phoenix.moonshiterscalculator.ui.db.MoonshineDBHelper;

public class StrengthCorrection {
    String strengthAreometer;
    String temperature;
    Context context;
    MoonshineDBHelper moonshineDBHelper;

    public StrengthCorrection(Context context) {
        this.context = context;
    }

    private void initMoonshineDBHelper() {
        moonshineDBHelper = new MoonshineDBHelper(context);
    }
}
