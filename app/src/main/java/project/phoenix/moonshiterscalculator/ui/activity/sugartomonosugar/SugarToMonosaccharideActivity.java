package project.phoenix.moonshiterscalculator.ui.activity.sugartomonosugar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.activity.template.TemplateActivity;


public class SugarToMonosaccharideActivity extends TemplateActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar_to_monosaccharide);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
