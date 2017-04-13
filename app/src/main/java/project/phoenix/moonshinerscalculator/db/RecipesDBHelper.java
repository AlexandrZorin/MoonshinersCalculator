package project.phoenix.moonshinerscalculator.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import project.phoenix.moonshinerscalculator.datamodel.Recipe;

public class RecipesDBHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "recipes.db";
    private static final int DATABASE_VERSION = 1;

    public RecipesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addRecipe(Recipe recipe) {
        SQLiteDatabase database;
        try {
            database = this.getWritableDatabase();
            database.rawQuery(
                    "INSERT INTO table_recipes (name, description, text) " +
                    "VALUES ('?', '?', '?');",
                    new String[]{recipe.getName(), recipe.getDescription(), recipe.getText()}
            );
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public Cursor getRecipe(Recipe recipe) {
        return null;
    }
}
