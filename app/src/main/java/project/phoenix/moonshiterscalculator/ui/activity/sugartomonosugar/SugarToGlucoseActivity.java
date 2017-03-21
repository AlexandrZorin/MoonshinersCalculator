package project.phoenix.moonshiterscalculator.ui.activity.sugartomonosugar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.activity.template.TemplateActivity;


public class SugarToGlucoseActivity extends TemplateActivity {
    private Button buttonResult;
    private EditText editTextSugarWeight;
    private TextView textViewResult;
    private double sugarWeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar_to_glucose);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parserStringToDoubleForResources();
                textViewResult.setText(String.format(Locale.US, "%.3f",
                        sugarToGlucose(sugarWeight)));
            }
        });
    }

    private void initViews() {
        buttonResult = (Button) findViewById(R.id.sugar_to_glucose_button);
        editTextSugarWeight = (EditText) findViewById(R.id.sugar_to_glucose_sugar_weight);
        textViewResult = (TextView) findViewById(R.id.sugar_to_glucose_result);
    }

    private void parserStringToDoubleForResources() {
        String sugarWeightText = editTextSugarWeight.getText().toString();
        if (sugarWeightText.matches("")) {
            Toast.makeText(this,
                    R.string.sugar_to_glucose_exception_blank_field_sugar_weight,
                    Toast.LENGTH_LONG).show();
        } else {
            sugarWeight = Double.parseDouble(sugarWeightText);
        }
    }

    private double sugarToGlucose(double sugarWeight) {
        return sugarWeight * 1.125;
    }
}
