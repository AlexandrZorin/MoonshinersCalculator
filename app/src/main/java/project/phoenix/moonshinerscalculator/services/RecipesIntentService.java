package project.phoenix.moonshinerscalculator.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.phoenix.moonshinerscalculator.datamodel.Recipe;


public class RecipesIntentService extends IntentService {
    private static final String LOG_TAG = "myLog";
    private Recipe recipe;

    public RecipesIntentService() {
        super("RecipesIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        jsonParser(jsonUrlToString("1"));

    }

    private String jsonUrlToString(String recipeId) {
        HttpURLConnection urlConnection;
        BufferedReader reader;
        String resultJson = "";
        try {
            URL url = new URL("http://192.168.101.220:8182/SpringRest/recipes/" + recipeId);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            resultJson = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }

    private void jsonParser(String strJson) {
        Log.d(LOG_TAG, strJson);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        recipe = gson.fromJson(strJson, Recipe.class);
    }
}
