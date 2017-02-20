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
            return String.format(Locale.US, "%.2f", determRoundAreometerStrength(
                    temperature,
                    areometerStrength,
                    context
                ));
        } else if (checkNumberAreometerStrength(areometerStrength) &&
                !checkNumberTemperature(temperature)) {
            return String.format(Locale.US, "%.2f", determRoundTemperature(
                    temperature,
                    areometerStrength,
                    context
            ));
        } else {
            return String.format(Locale.US, "%.2f", determRoundAll(
                    temperature,
                    areometerStrength,
                    context
            ));
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
        double doubleAreometerStrength = Double.parseDouble(areometerStrength);
        double result = Math.ceil(doubleAreometerStrength * 2) / 2;
        return String.valueOf(result);
    }

    private String areometerStrengthRoundDown(String areometerStrength) {
        double doubleAreometerStrength = Double.parseDouble(areometerStrength);
        double result = Math.floor(doubleAreometerStrength * 2) / 2;
        return String.valueOf(result);
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

    private double determRoundAreometerStrength
            (String temperature, String areometerStrength, Context context) {
        String cursorAreometerStrengthRoundUp = "";
        String cursorAreometerStrengthRoundDown = "";
        String cursorCorrectStrengthRoundUp = "";
        String cursorCorrectStrengthRoundDown = "";
        MoonshineDBHelper moonshineDBHelper = new MoonshineDBHelper(context);
        Formulas formulas = new Formulas();
        Cursor cursor;
        double result;

        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrengthRoundUp(areometerStrength),
                        temperature);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthRoundUp = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
            cursorAreometerStrengthRoundUp = cursor
                    .getString(cursor.getColumnIndex("strength"));
        }
        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrengthRoundDown(areometerStrength),
                        temperature);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthRoundDown = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
            cursorAreometerStrengthRoundDown = cursor
                    .getString(cursor.getColumnIndex("strength"));
        }
        result = formulas.formulaRoundingAreometerStrength(
                Double.parseDouble(areometerStrength),
                Double.parseDouble(cursorAreometerStrengthRoundDown),
                Double.parseDouble(cursorAreometerStrengthRoundUp),
                Double.parseDouble(cursorCorrectStrengthRoundDown),
                Double.parseDouble(cursorCorrectStrengthRoundUp)
        );
        cursor.close();
        return result;
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
                .getCorrectStrength(areometerStrength,
                        temperatureRoundUp(temperature));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthRoundUp = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
            cursorTemperatureRoundUp = cursor
                    .getString(cursor.getColumnIndex("temperature"));
        }
        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrength,
                        temperatureRoundDown(temperature));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthRoundDown = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
            cursorTemperatureRoundDown = cursor
                    .getString(cursor.getColumnIndex("temperature"));
            System.out.println(cursorTemperatureRoundDown);
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

    private double determRoundAll
            (String temperature, String areometerStrength, Context context) {
        String cursorTemperatureRoundUp = "";
        String cursorTemperatureRoundDown = "";
        String cursorAreometerStrengthRoundUp = "";
        String cursorAreometerStrengthRoundDown = "";
        String cursorCorrectStrengthArStrUpTempUp = "";
        String cursorCorrectStrengthArStrDownTempDown = "";
        String cursorCorrectStrengthArStrUpTempDown = "";
        String cursorCorrectStrengthArStrDownTempUp = "";
        MoonshineDBHelper moonshineDBHelper = new MoonshineDBHelper(context);
        Formulas formulas = new Formulas();
        Cursor cursor;
        double result;

        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrengthRoundUp(areometerStrength),
                        temperatureRoundUp(temperature));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthArStrUpTempUp = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
            cursorTemperatureRoundUp = cursor
                    .getString(cursor.getColumnIndex("temperature"));
            cursorAreometerStrengthRoundUp = cursor
                    .getString(cursor.getColumnIndex("strength"));
        }
        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrengthRoundDown(areometerStrength),
                        temperatureRoundDown(temperature));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthArStrDownTempDown = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
            cursorTemperatureRoundDown = cursor
                    .getString(cursor.getColumnIndex("temperature"));
            cursorAreometerStrengthRoundDown = cursor
                    .getString(cursor.getColumnIndex("strength"));
        }
        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrengthRoundUp(areometerStrength),
                        temperatureRoundDown(temperature));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthArStrUpTempDown = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
        }
        cursor = moonshineDBHelper
                .getCorrectStrength(areometerStrengthRoundDown(areometerStrength),
                        temperatureRoundUp(temperature));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            cursorCorrectStrengthArStrDownTempUp = cursor
                    .getString(cursor.getColumnIndex("correct_strength"));
        }
        result = formulas.formulaRoundingAll(
                Double.parseDouble(temperature),
                Double.parseDouble(areometerStrength),
                Double.parseDouble(cursorTemperatureRoundDown),
                Double.parseDouble(cursorTemperatureRoundUp),
                Double.parseDouble(cursorAreometerStrengthRoundDown),
                Double.parseDouble(cursorAreometerStrengthRoundUp),
                Double.parseDouble(cursorCorrectStrengthArStrUpTempDown),
                Double.parseDouble(cursorCorrectStrengthArStrUpTempUp),
                Double.parseDouble(cursorCorrectStrengthArStrDownTempDown),
                Double.parseDouble(cursorCorrectStrengthArStrDownTempUp)
        );
        cursor.close();
        return result;
    }
}
