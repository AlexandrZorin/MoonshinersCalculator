package project.phoenix.moonshiterscalculator.ui.activity.strengthcorrect;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Locale;

import project.phoenix.moonshiterscalculator.ui.db.MoonshineDBHelper;

public class InitCorrectStrengthDBCursor {
    public String initCursor(String areometerStrengh, String temperature, Context context) {
        MoonshineDBHelper moonshineDBHelper = new MoonshineDBHelper(context);
        ArrayList<String> itemsCorrectStrength = new ArrayList<>();
        ArrayList<String> itemsAreometerStrength = new ArrayList<>();
        ArrayList<String> itemsTemperature = new ArrayList<>();
        Cursor cursor;
        if (checkNumberAreometerStrength(areometerStrengh) &&
                checkNumberTemperature(temperature)) {
            cursor = moonshineDBHelper
                    .getCorrectStrength(areometerStrengh, temperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                itemsCorrectStrength.add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                cursor.close();
                return itemsCorrectStrength.get(0);
            }
            return "cursor = 0";
        } else if (!checkNumberAreometerStrength(areometerStrengh) &&
                checkNumberTemperature(temperature)) {
            cursor = moonshineDBHelper
                    .getRoundingAreometerStrength(areometerStrengh, temperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    itemsCorrectStrength.add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                    itemsAreometerStrength.add(cursor.getString(cursor.getColumnIndex("strength")));
                    cursor.moveToNext();
                }
                double y = Double.parseDouble(areometerStrengh);
                double y1 = Double.parseDouble(itemsAreometerStrength.get(0));
                double y2 = Double.parseDouble(itemsAreometerStrength.get(1));
                double y1x = Double.parseDouble(itemsCorrectStrength.get(0));
                double y2x = Double.parseDouble(itemsCorrectStrength.get(1));
                double result;

                result = y2x - (((y2 - y) / (y2 - y1)) * (y2x - y1x));

                cursor.close();
                return String.format(Locale.getDefault(), "%.2f", result);
            }
            return "cursor = 0";
        } else if (checkNumberAreometerStrength(areometerStrengh) &&
                !checkNumberTemperature(temperature)) {
            cursor = moonshineDBHelper
                    .getRoundingTemperature(areometerStrengh, temperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    itemsCorrectStrength.add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                    itemsTemperature.add(cursor.getString(cursor.getColumnIndex("temperature")));
                    cursor.moveToNext();
                }
                double x = Double.parseDouble(temperature);
                double x1 = Double.parseDouble(itemsTemperature.get(0));
                double x2 = Double.parseDouble(itemsTemperature.get(1));
                double x1y = Double.parseDouble(itemsCorrectStrength.get(0));
                double x2y = Double.parseDouble(itemsCorrectStrength.get(1));
                double result;

                result = x1y - (((x1 - x) / (x1 - x2)) * (x1y - x2y));

                cursor.close();
                return String.format(Locale.getDefault(), "%.2f", result);
            }
            return "cursor = 0";
        } else {
            cursor = moonshineDBHelper
                    .getRoundingAll(areometerStrengh, temperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    itemsCorrectStrength.add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                    itemsAreometerStrength.add(cursor.getString(cursor.getColumnIndex("strength")));
                    itemsTemperature.add(cursor.getString(cursor.getColumnIndex("temperature")));
                    cursor.moveToNext();
                }
            }
            return "хз";
        }
    }

    private boolean checkNumberAreometerStrength(String areometerStrength) {
        double areometerStrengthDouble = Double.parseDouble(areometerStrength);
        return areometerStrengthDouble % 1 == 0 || areometerStrengthDouble % 1 == 0.5;
    }

    private boolean checkNumberTemperature(String temperature) {
        double temperatureDouble = Double.parseDouble(temperature);
        return temperatureDouble % 1 == 0;
    }
}
