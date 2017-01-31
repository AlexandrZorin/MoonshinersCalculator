package project.phoenix.moonshiterscalculator.ui.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class MoonshineDBHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "moonshine.db";
    private static final int DATABASE_VERSION = 1;

    public MoonshineDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<String> getAllTemperature() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("select * from temperature", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            arrayList.add(res.getString(res.getColumnIndex("temperature")));
            res.moveToNext();
        }
        return arrayList;
    }

    public Cursor getCorrectStrength(String areometerStrength, String temperature) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT strength, temperature, correct_strength " +
                "FROM areometer_strength, temperature " +
                "JOIN correct_strength_with_temperature ON areometer_strength._id = " +
                "correct_strength_with_temperature.id_areometer_strength " +
                "AND temperature._id = correct_strength_with_temperature.id_temperature " +
                "WHERE strength = ? AND temperature = ?;",
                new String[]{areometerStrength, temperature});
        cursor.moveToFirst();
        System.out.println(areometerStrength);
        System.out.println(temperature);
        System.out.println("cursor count: " + cursor.getCount());
        return cursor;
    }
}
