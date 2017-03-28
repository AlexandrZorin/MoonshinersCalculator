package project.phoenix.moonshinerscalculator.ui.activity.strengthbytemperature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import project.phoenix.moonshinerscalculator.R;
import project.phoenix.moonshinerscalculator.ui.activity.template.TemplateActivity;

public class StrengthByTemperatureActivity extends TemplateActivity {
    private Button buttonResult;
    private EditText editTextTemperatureBoiling;
    private TextView textViewResult;
    private double temperatureBoiling;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_by_temperature);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parserStringToDoubleForResources();
                textViewResult.setText(String.format(Locale.US, "%.2f",
                        temperatureBouling(temperatureBoiling)));
            }
        });
    }

    private void initViews() {
        buttonResult =
                (Button) findViewById(R.id.strength_by_temperature_button);
        editTextTemperatureBoiling =
                (EditText) findViewById(R.id.strength_by_temperature_temperature_boiling);
        textViewResult =
                (TextView) findViewById(R.id.strength_by_temperature_result);
    }

    private void parserStringToDoubleForResources() {
        String temperatureBoilingText = editTextTemperatureBoiling.getText().toString();
        if (temperatureBoilingText.matches("")) {
            Toast.makeText(this,
                    R.string.strength_by_temperature_exception_blank_field_temperature_boiling,
                    Toast.LENGTH_LONG).show();
        } else {
            temperatureBoiling = Double.parseDouble(temperatureBoilingText);
        }
    }

    private double temperatureBouling(double temperature) {
        double ti = (temperature - 89.03) / 6.54;
        return 16.75 - 19.05 * ti + 12.64 * Math.pow(ti, 2)
                - 3.69 * Math.pow(ti, 3) - 0.38 * Math.pow(ti, 4);
    }
}
