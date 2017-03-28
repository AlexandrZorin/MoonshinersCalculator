package project.phoenix.moonshiterscalculator.ui.activity.correctstregth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.activity.template.TemplateActivity;

/**
 * Activity for correcting the readings of the hydrometer by temperature.
 */

public class CorrectStrengthActivity extends TemplateActivity {
    private Button buttonResult;
    private EditText editTextAreometerStrength;
    private EditText editTextTemperature;
    private TextView textViewCorrectStrength;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_correct);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parserStringForResources();
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

    private void parserStringForResources() {
        String textAreometerStrength = editTextAreometerStrength.getText().toString();
        String textTemperature = editTextTemperature.getText().toString();
        DeterminationResultCorrectStrength determinationResultCorrectStrength =
                new DeterminationResultCorrectStrength();

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

        String textResult = determinationResultCorrectStrength
                .determResult(textAreometerStrength, textTemperature, this);
        textViewCorrectStrength.setText(textResult);
    }
}
