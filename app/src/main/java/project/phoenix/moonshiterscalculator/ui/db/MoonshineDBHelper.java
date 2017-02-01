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

    public Cursor getCorrectStrengthWithoutRounding(String areometerStrength, String temperature) {
        database = this.getReadableDatabase();
        return database.rawQuery(
                "SELECT correct_strength " +
                "FROM areometer_strength, table_temperature " +
                "JOIN correct_strength_with_temperature ON areometer_strength._id = " +
                        "correct_strength_with_temperature._id_areometer_strength " +
                "AND table_temperature._id = " +
                        "correct_strength_with_temperature._id_table_temperature " +
                "WHERE strength = ? AND temperature = ?;",
                new String[]{areometerStrength, temperature});
    }

    public Cursor getCorrectStrengthWithRoundingUp(String areometerStrength, String temperature) {
        database = this.getReadableDatabase();
        return database.rawQuery(
                "SELECT correct_strength " +
                "FROM ",
                null
        );
    }
}
