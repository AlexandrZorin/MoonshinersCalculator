package project.phoenix.moonshinerscalculator.db;

import android.content.Context;
import android.database.Cursor;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import project.phoenix.moonshinerscalculator.datamodel.Recipe;

public class RecipesDBHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "recipes.db";
    private static final int DATABASE_VERSION = 1;

    public RecipesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addRecipe(Recipe recipe) {

    }

    public Cursor getRecipe(Recipe recipe) {
        return null;
    }
}
