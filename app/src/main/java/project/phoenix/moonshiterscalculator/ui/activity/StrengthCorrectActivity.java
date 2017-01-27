package project.phoenix.moonshiterscalculator.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.activity.template.TemplateActivity;
import project.phoenix.moonshiterscalculator.ui.db.MoonshineDBHelper;


public class StrengthCorrectActivity extends TemplateActivity {
    private ListView obj;
    MoonshineDBHelper moonshineDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_correct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        moonshineDBHelper = new MoonshineDBHelper(this);
        ArrayList arrayList = moonshineDBHelper.getAllTemperature();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        obj = (ListView) findViewById(R.id.listView);
        obj.setAdapter(arrayAdapter);
    }
}
