package project.phoenix.moonshiterscalculator.ui.activity.sugartomonosugar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.activity.template.TemplateActivity;


public class SugarToGlucoseActivity extends TemplateActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar_to_glucose);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
    }

    private double sugarToGlucose(double sugarWeight) {
        return sugarWeight * 1.125;
    }
}
