package project.phoenix.moonshiterscalculator.ui.activity.correctstregth;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Locale;

import project.phoenix.moonshiterscalculator.ui.db.MoonshineDBHelper;

public class DeterminationResultCorrectStrength {
    public String determResult(String areometerStrength, String temperature, Context context) {
        MoonshineDBHelper moonshineDBHelper = new MoonshineDBHelper(context);
        ArrayList<String> itemsCorrectStrength = new ArrayList<>();
        ArrayList<String> itemsAreometerStrength = new ArrayList<>();
        ArrayList<String> itemsTemperature = new ArrayList<>();
        Formulas formulas = new Formulas();
        Cursor cursor;
        double result;
        if (checkNumberAreometerStrength(areometerStrength) &&
                checkNumberTemperature(temperature)) {
            cursor = moonshineDBHelper
                    .getCorrectStrength(areometerStrength, temperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                itemsCorrectStrength
                        .add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                cursor.close();
                return itemsCorrectStrength.get(0);
            }
            return "cursor = 0";
        } else if (!checkNumberAreometerStrength(areometerStrength) &&
                checkNumberTemperature(temperature)) {
            cursor = moonshineDBHelper
                    .getRoundingAreometerStrength(areometerStrength, temperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    itemsAreometerStrength
                            .add(cursor.getString(cursor.getColumnIndex("strength")));
                    itemsCorrectStrength
                            .add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                    cursor.moveToNext();
                }
                result = formulas.formulaRoundingAreometerStrength(
                        Double.parseDouble(temperature),
                        Double.parseDouble(itemsAreometerStrength.get(0)),
                        Double.parseDouble(itemsAreometerStrength.get(1)),
                        Double.parseDouble(itemsCorrectStrength.get(0)),
                        Double.parseDouble(itemsCorrectStrength.get(1))
                );
                cursor.close();
                return String.format(Locale.US, "%.2f", result);
            }
            return "cursor = 0";
        } else if (checkNumberAreometerStrength(areometerStrength) &&
                !checkNumberTemperature(temperature)) {
            cursor = moonshineDBHelper
                    .getRoundingTemperature(areometerStrength, temperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    itemsTemperature
                            .add(cursor.getString(cursor.getColumnIndex("temperature")));
                    itemsCorrectStrength
                            .add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                    cursor.moveToNext();
                }
                result = formulas.formulaRoundingTemperature(
                        Double.parseDouble(temperature),
                        Double.parseDouble(itemsTemperature.get(0)),
                        Double.parseDouble(itemsTemperature.get(1)),
                        Double.parseDouble(itemsCorrectStrength.get(0)),
                        Double.parseDouble(itemsCorrectStrength.get(1))
                );
                cursor.close();
                return String.format(Locale.US, "%.2f", result);
            }
            return "cursor = 0";
        } else {
            cursor = moonshineDBHelper
                    .getRoundingAll(areometerStrength, temperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    itemsCorrectStrength
                            .add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                    itemsAreometerStrength
                            .add(cursor.getString(cursor.getColumnIndex("strength")));
                    itemsTemperature
                            .add(cursor.getString(cursor.getColumnIndex("temperature")));
                    cursor.moveToNext();
                }
                cursor.close();
            }
            return "cursor = 0";
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
