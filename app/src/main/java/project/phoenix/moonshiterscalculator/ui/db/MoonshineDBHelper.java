package project.phoenix.moonshiterscalculator.ui.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class MoonshineDBHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "moonshine.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase database;

    public MoonshineDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getCorrectStrength(String areometerStrength, String temperature) {
        database = this.getReadableDatabase();
        return database.rawQuery(
                "SELECT correct_strength " +
                "FROM table_temperature, areometer_strength, correct_strength_with_temperature " +
                "WHERE areometer_strength._id = " +
                        "correct_strength_with_temperature._id_areometer_strength " +
                "AND table_temperature._id = " +
                        "correct_strength_with_temperature._id_table_temperature " +
                "AND strength = ? " +
                "AND temperature = ?;",
                new String[] {areometerStrength, temperature}
        );
    }

    public Cursor getRoundingAreometerStrength(String areometerStrength, String temperature) {
        database = this.getReadableDatabase();
        return database.rawQuery(
                "SELECT temperature, MAX(strength) AS strength, correct_strength " +
                "FROM table_temperature, areometer_strength, correct_strength_with_temperature " +
                "WHERE areometer_strength._id = " +
                        "correct_strength_with_temperature._id_areometer_strength " +
                "AND table_temperature._id = " +
                        "correct_strength_with_temperature._id_table_temperature " +
                "AND strength < " + areometerStrength + " " +
                "AND temperature = " + temperature + " " +
                "UNION " +
                "SELECT temperature, MIN(strength) AS strength, correct_strength " +
                "FROM table_temperature, areometer_strength, correct_strength_with_temperature " +
                "WHERE areometer_strength._id = " +
                        "correct_strength_with_temperature._id_areometer_strength " +
                "AND table_temperature._id = " +
                        "correct_strength_with_temperature._id_table_temperature " +
                "AND strength > ? " +
                "AND temperature = ?;",
                new String[] {areometerStrength, temperature}
        );
    }

    public Cursor getRoundingTemperature(String areometerStrength, String temperature) {
        database = this.getReadableDatabase();
        return database.rawQuery(
                "SELECT MAX(temperature) AS temperature, strength, correct_strength " +
                "FROM table_temperature, areometer_strength, correct_strength_with_temperature " +
                "WHERE areometer_strength._id = " +
                        "correct_strength_with_temperature._id_areometer_strength " +
                "AND table_temperature._id = " +
                        "correct_strength_with_temperature._id_table_temperature " +
                "AND strength = " + areometerStrength + " " +
                "AND temperature < " + temperature + " " +
                "UNION " +
                "SELECT MIN(temperature), strength, correct_strength " +
                "FROM table_temperature, areometer_strength, correct_strength_with_temperature " +
                "WHERE areometer_strength._id = " +
                        "correct_strength_with_temperature._id_areometer_strength " +
                "AND table_temperature._id = " +
                        "correct_strength_with_temperature._id_table_temperature " +
                "AND strength = ? " +
                "AND temperature > ?;",
                new String[] {areometerStrength, temperature}
        );
    }

    public Cursor getRoundingAll(String areometerStrength, String temperature) {
        database = this.getReadableDatabase();
        return database.rawQuery(
                "SELECT MAX(temperature), MAX(strength) AS strength, correct_strength " +
                "FROM table_temperature, areometer_strength, correct_strength_with_temperature " +
                "WHERE areometer_strength._id = " +
                        "correct_strength_with_temperature._id_areometer_strength " +
                "AND table_temperature._id = " +
                        "correct_strength_with_temperature._id_table_temperature " +
                "AND strength < " + areometerStrength + " " +
                "AND temperature < " + temperature + " " +
                "UNION " +
                "SELECT MIN(temperature), MIN(strength) AS strength, correct_strength " +
                "FROM table_temperature, areometer_strength, correct_strength_with_temperature " +
                "WHERE areometer_strength._id = " +
                        "correct_strength_with_temperature._id_areometer_strength " +
                "AND table_temperature._id = " +
                        "correct_strength_with_temperature._id_table_temperature " +
                "AND strength > ? " +
                "AND temperature > ?;",
                new String[] {areometerStrength, temperature}
        );
    }
}
