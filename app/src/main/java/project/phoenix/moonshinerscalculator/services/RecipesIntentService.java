package project.phoenix.moonshinerscalculator.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RecipesIntentService extends IntentService {
    public RecipesIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    private String jsonParser() {
        HttpURLConnection urlConnection;
        BufferedReader reader;
        String resultJson = "";
        try {
            URL url = new URL("192.168.101.220");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            resultJson = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultJson;
    }
}
