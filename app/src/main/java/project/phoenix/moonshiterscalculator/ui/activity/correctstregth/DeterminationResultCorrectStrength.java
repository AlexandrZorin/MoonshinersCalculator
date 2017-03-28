package project.phoenix.moonshiterscalculator.ui.activity.correctstregth;

import android.content.Context;
import android.database.Cursor;

import java.util.Locale;

import project.phoenix.moonshiterscalculator.ui.db.MoonshineDBHelper;

/**
 * Класс для определения скорректированной крепости спиртового раствора.
 * В классе вытаскиваются нужные данные из базы данных, если введенные пользователем данные
 * не ровные, они округляются и ответ пересчитывается по формулам вынесенным в отдельный класс.
 */

public class DeterminationResultCorrectStrength {

    /**
     * Метод для определения и возврата результата в виде строки.
     * @param areometerStrength Крепость спиртового раствора показанная ареометром.
     * @param temperature Температура спиртового раствора во время измерений ареометром.
     * @param context Контекст.
     * @return Возврат вычисленного результата в виде строки.
     */
    public String determResult(String areometerStrength, String temperature, Context context) {
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

    /**
     * Метод проверяет введенные данные пользователем на соответствие ровности
     * (ровным значением в данном случае является целое или 0.5).
     * @param areometerStrength Крепость спиртового раствора определенная ареометром.
     * @return Возвращает значение true если значение ровное, false если значение не ровное.
     */
    private boolean checkNumberAreometerStrength(String areometerStrength) {
        double areometerStrengthDouble = Double.parseDouble(areometerStrength);
        return areometerStrengthDouble % 1 == 0 || areometerStrengthDouble % 1 == 0.5;
    }

    /**
     * Метод проверяет введенные данные пользователем на соответствие ровности
     * (ровным значением в данном случае является только целое)
     * @param temperature Температура спиртового раствора во время замеров крепости ареометром.
     * @return Возвращает значение true если значение ровное, false если значение не ровное.
     */
    private boolean checkNumberTemperature(String temperature) {
        double temperatureDouble = Double.parseDouble(temperature);
        return temperatureDouble % 1 == 0;
    }

    /**
     * Округление крепости спиртового раствора до 0.5 в большую сторону.
     * @param areometerStrength Крепость спиртового раствора определенная ареометром.
     * @return Возвращает округленное значение в виде строки.
     */
    private String areometerStrengthRoundUp(String areometerStrength) {
        double doubleAreometerStrength = Double.parseDouble(areometerStrength);
        double result = Math.ceil(doubleAreometerStrength * 2) / 2;
        return String.valueOf(result);
    }

    /**
     * Округление крепости спиртового раствора до 0.5 в меньшую сторону.
     * @param areometerStrength Крепость спиртового раствора определенная ареометром.
     * @return Возвращает округленное значение в виде строки.
     */
    private String areometerStrengthRoundDown(String areometerStrength) {
        double doubleAreometerStrength = Double.parseDouble(areometerStrength);
        double result = Math.floor(doubleAreometerStrength * 2) / 2;
        return String.valueOf(result);
    }

    /**
     * Округление температуры спиртового раствора до целого в большую сторону.
     * @param temperature Температура спиртового раствора во время измерений ареометром.
     * @return Возвращает округленное значение в виде строки.
     */
    private String temperatureRoundUp(String temperature) {
        return String.valueOf(Math.ceil(Double.parseDouble(temperature)));
    }

    /**
     * Округление температуры спиртового раствора до целого в меньшую сторону.
     * @param temperature Температура спиртового раствора во время измерений ареометром.
     * @return Возвращает округленное значение в виде строки.
     */
    private String temperatureRoundDown(String temperature) {
        return String.valueOf(Math.floor(Double.parseDouble(temperature)));
    }

    /**
     * Определение скорректированной крепости спиртового раствора, если все введенные данные
     * оказались ровными.
     * @param temperature Температура спиртового раствора во время измерений ареометром.
     * @param areometerStrength Крепость спиртового раствора определенная ареометром.
     * @param context Контекст.
     * @return Возвращает скорректированную крепость спиртового раствора в виде строки.
     */
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

    /**
     * Определение скорректированной крепости спиртового раствора, если ровным оказалась только
     * введенная температура, а крепость показанную ареометром пришлось округлять.
     * @param temperature Температура спиртового раствора во время измерений ареометром.
     * @param areometerStrength Крепость спиртового раствора определенная ареометром.
     * @param context Контекст.
     * @return Возвращает скорректированную крепость спиртового раствора в виде double.
     */
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

    /**
     * Определение скорректированной крепости спиртового раствора, если ровным оказалась только
     * введенная крепость спиртового раствора, а температуру пришлось округлять.
     * @param temperature Температура спиртового раствора во время измерений ареометром.
     * @param areometerStrength Крепость спиртового раствора определенная ареометром.
     * @param context Контекст.
     * @return Возвращает скорректированную крепость спиртового раствора в виде double.
     */
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

    /**
     * Определение скорректированной крепости спиртового раствора, округлять пришлось и
     * температуру и крепость спиртового раствора.
     * @param temperature Температура спиртового раствора во время измерений ареометром.
     * @param areometerStrength Крепость спиртового раствора определенная ареометром.
     * @param context Контекст.
     * @return Возвращает скорректированную крепость спиртового раствора в виде double.
     */
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
