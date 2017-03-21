package project.phoenix.moonshiterscalculator.ui.activity.alcoholstrength;

import android.os.Bundle;
import android.support.annotation.Nullable;

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.activity.template.TemplateActivity;

public class AlcoholStrengthActivity extends TemplateActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_strength);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private double density(double mass, double volume) {
        return mass / volume;
    }
}
