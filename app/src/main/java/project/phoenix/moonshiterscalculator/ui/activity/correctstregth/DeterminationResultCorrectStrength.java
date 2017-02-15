package project.phoenix.moonshiterscalculator.ui.activity.correctstregth;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Locale;

import project.phoenix.moonshiterscalculator.ui.db.MoonshineDBHelper;

public class DeterminationResultCorrectStrength {
    public String determResult(String areometerStrength, String temperature, Context context) {
        MoonshineDBHelper moonshineDBHelper = new MoonshineDBHelper(context);
        Formulas formulas = new Formulas();
        ArrayList<String> itemsCorrectStrength = new ArrayList<>();
        ArrayList<String> itemsAreometerStrength = new ArrayList<>();
        ArrayList<String> itemsTemperature = new ArrayList<>();
        Cursor cursor;

        if (checkNumberAreometerStrength(areometerStrength) &&
                checkNumberTemperature(temperature)) {
            return determCorrectStrengthWithoutRound(
                    temperature,
                    areometerStrength,
                    context
            );
        } else if (!checkNumberAreometerStrength(areometerStrength) &&
                checkNumberTemperature(temperature)) {
            double result;
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
            return String.format(Locale.US, "%.2f", determRoundTemperature(
                    temperature,
                    areometerStrength,
                    context
            ));
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

    private String areometerStrengthRoundUp(String areometerStrength) {
        return "";
    }

    private String areometerStrengthRoundDown(String areometerStrength) {
        return "";
    }

    private String temperatureRoundUp(String temperature) {
        return String.valueOf(Math.ceil(Double.parseDouble(temperature)));
    }

    private String temperatureRoundDown(String temperature) {
        return String.valueOf(Math.floor(Double.parseDouble(temperature)));
    }

    private String determCorrectStrengthWithoutRound
            (String temperature, String areometerStrength, Context context) {
        String cursorCorrectStrength = "";
        MoonshineDBHelper moonshineDBHelper = new MoonshineDBHelper(context);
        Cursor cursor;

        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrength, temperature);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrength = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
        }
        cursor.close();
        return cursorCorrectStrength;
    }

    private double determRoundTemperature
            (String temperature, String areometerStrength, Context context) {
        String cursorTemperatureRoundUp = "";
        String cursorTemperatureRoundDown = "";
        String cursorCorrectStrengthRoundUp = "";
        String cursorCorrectStrengthRoundDown = "";
        MoonshineDBHelper moonshineDBHelper = new MoonshineDBHelper(context);
        Formulas formulas = new Formulas();
        Cursor cursor;
        double result;

        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrength, temperatureRoundUp(temperature));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthRoundUp = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
            cursorTemperatureRoundUp = cursor
                    .getString(cursor.getColumnIndex("temperature"));
        }
        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrength, temperatureRoundDown(temperature));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthRoundDown = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
            cursorTemperatureRoundDown = cursor
                    .getString(cursor.getColumnIndex("temperature"));
        }
        result = formulas.formulaRoundingTemperature(
                Double.parseDouble(temperature),
                Double.parseDouble(cursorTemperatureRoundDown),
                Double.parseDouble(cursorTemperatureRoundUp),
                Double.parseDouble(cursorCorrectStrengthRoundDown),
                Double.parseDouble(cursorCorrectStrengthRoundUp)
        );
        cursor.close();
        return result;
    }
}
