package project.phoenix.moonshiterscalculator.ui.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;


public class MoonshineDBHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "moonshine.db";
    private static final int DATABASE_VERSION = 1;
    private Cursor cursor;

    public MoonshineDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ArrayList<String> getAllTemperature() {
        ArrayList<String> arrayList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor res = database.rawQuery("select * from temperature", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            arrayList.add(res.getString(res.getColumnIndex("temperature")));
            res.moveToNext();
        }
        return arrayList;
    }

    public double getCorrectStrength(String strengthAreometer, String temperature) {
        SQLiteDatabase database = this.getReadableDatabase();
        cursor = database.rawQuery(
                "SELECT correct_strength" +
                "FROM correct_strength_with_temperature" +
                "WHERE ",
                null);
        return 0;
    }
}
