package project.phoenix.moonshiterscalculator.ui.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class MoonshineDBHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "moonshine.db";
    private static final int DATABASE_VERSION = 1;

    public MoonshineDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
