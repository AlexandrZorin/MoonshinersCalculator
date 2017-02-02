package project.phoenix.moonshiterscalculator.ui.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.activity.template.TemplateActivity;
import project.phoenix.moonshiterscalculator.ui.db.MoonshineDBHelper;


public class StrengthCorrectActivity extends TemplateActivity {
    private Button buttonResult;
    private EditText editTextAreometerStrength;
    private EditText editTextTemperature;
    private TextView textViewCorrectStrength;
    private MoonshineDBHelper moonshineDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_correct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        moonshineDBHelper = new MoonshineDBHelper(this);
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parserCursorFromDatabase();
            }
        });
    }

    private void initViews() {
        buttonResult = (Button)
                findViewById(R.id.strength_correct_button);
        editTextAreometerStrength = (EditText)
                findViewById(R.id.strength_correct_areometer_strength);
        editTextTemperature = (EditText)
                findViewById(R.id.strength_correct_temperature);
        textViewCorrectStrength  = (TextView)
                findViewById(R.id.strength_correct);
    }

    private void parserCursorFromDatabase() {
        String textAreometerStrength = editTextAreometerStrength.getText().toString();
        String textTemperature = editTextTemperature.getText().toString();
        ArrayList<String> itemsCorrectStrength = new ArrayList<>();
        ArrayList<String> itemsAreometerStrength = new ArrayList<>();
        ArrayList<String> itemsTemperature = new ArrayList<>();
        Cursor cursor;

        if (textAreometerStrength.matches("")) {
            Toast.makeText(
                    this,
                    R.string.strength_correct_exception_blank_field_areometer_strength,
                    Toast.LENGTH_LONG).show();
        } else if (textTemperature.matches("")) {
            Toast.makeText(
                    this,
                    R.string.strength_correct_exception_blank_field_temperature,
                    Toast.LENGTH_LONG).show();
        }

        if (checkNumberAreometerStrength(textAreometerStrength) &&
                checkNumberTemperature(textTemperature)) {
            cursor = moonshineDBHelper
                    .getCorrectStrength(textAreometerStrength, textTemperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                itemsCorrectStrength.add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                textViewCorrectStrength.setText(itemsCorrectStrength.get(0));
                itemsCorrectStrength.clear();
                cursor.close();
            }
        } else if (!checkNumberAreometerStrength(textAreometerStrength) &&
                checkNumberTemperature(textTemperature)) {
            cursor = moonshineDBHelper
                    .getRoundingAreometerStrength(textAreometerStrength, textTemperature);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    itemsCorrectStrength.add(cursor.getString(cursor.getColumnIndex("correct_strength")));
                    itemsAreometerStrength.add(cursor.getString(cursor.getColumnIndex("strength")));
                    itemsTemperature.add(cursor.getString(cursor.getColumnIndex("temperature")));
                    cursor.moveToNext();
                }
                double y = Double.parseDouble(textAreometerStrength);
                double y2 = Double.parseDouble(itemsAreometerStrength.get(1));
                double y1 = Double.parseDouble(itemsAreometerStrength.get(0));
                double y2x = Double.parseDouble(itemsCorrectStrength.get(1));
                double y1x = Double.parseDouble(itemsCorrectStrength.get(0));
                double x = Double.parseDouble(itemsTemperature.get(0));
                double result;

                result = y2x - (((y2 - y) / (y2 - y1)) * (y2x - y1x));

                textViewCorrectStrength.setText(String.valueOf(result));
            }
        } else if (checkNumberAreometerStrength(textAreometerStrength) &&
                !checkNumberTemperature(textTemperature)) {
            Toast.makeText(
                    this,
                    "fff",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(
                    this,
                    "xxx",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkNumberAreometerStrength(String areometerStrength) {
        double areometerStrengthDouble = Double.parseDouble(areometerStrength);
        return areometerStrengthDouble % 1 == 0 || areometerStrengthDouble % 1 == 0.5;
    }

    private boolean checkNumberTemperature(String temperature) {
        double temperatureDouble = Double.parseDouble(temperature);
        return temperatureDouble % 1 == 0;
    }
}
