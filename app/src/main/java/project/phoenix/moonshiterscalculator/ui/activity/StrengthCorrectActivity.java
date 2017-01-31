package project.phoenix.moonshiterscalculator.ui.activity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
    private Context context = this;
    private ListView obj;
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
                String textAreometerStrength = editTextAreometerStrength.getText().toString();
                String textTemperature = editTextTemperature.getText().toString();
                if (textAreometerStrength.matches("")) {
                    Toast.makeText(context, "fff", Toast.LENGTH_LONG);
                } else if (textTemperature.matches("")) {
                    Toast.makeText(context, "ddd", Toast.LENGTH_LONG);
                } else {
                    Cursor cursor = moonshineDBHelper.getCorrectStrength(textAreometerStrength, textTemperature);
                    System.out.println(cursor.getCount());
                    /*if (cursor.getCount() > 0) {
                        cursor.moveToFirst();
                        String itemStrength = cursor.getString(cursor.getColumnIndex("strength"));
                        String itemTemperature = cursor.getString(cursor.getColumnIndex("temperature"));
                        String itemCorrectStrength = cursor.getString(cursor.getColumnIndex("correct_strength"));
                        textViewCorrectStrength.setText(itemCorrectStrength);
                    }*/
                }
            }
        });

        /*ArrayList arrayList = moonshineDBHelper.getAllTemperature();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        obj = (ListView) findViewById(R.id.listView);
        obj.setAdapter(arrayAdapter);*/
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
}
