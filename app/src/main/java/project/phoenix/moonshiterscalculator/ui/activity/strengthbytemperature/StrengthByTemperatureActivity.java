package project.phoenix.moonshiterscalculator.ui.activity.strengthbytemperature;

import android.os.Bundle;
import android.support.annotation.Nullable;

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.activity.template.TemplateActivity;

public class StrengthByTemperatureActivity extends TemplateActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_by_temperature);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
