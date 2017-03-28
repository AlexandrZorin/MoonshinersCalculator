package project.phoenix.moonshinerscalculator.ui.activity.braga;

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

/**
 * Activity for calculating the maximum alcohol content in braga,
 * absolute alcohol and the resulting volume of wort.
 */

public class BragaActivity extends TemplateActivity {
    private Button buttonResult;
    private EditText editTextWeightSugar;
    private EditText editTextWaterVolume;
    private TextView textViewMaxStrength;
    private TextView textViewDehydratedAlcohol;
    private TextView textViewVolumeSolution;
    private double sugarWeight;
    private double waterVolume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braga);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parserStringToDoubleForResources();
                double sugarVolume = mgSugarPerMl(sugarWeight);
                double volumeSolution = volumeSolution(waterVolume, sugarVolume);
                double dehydratedAlcohol = dehydratedAlcohol(sugarWeight);
                textViewVolumeSolution
                        .setText(String.format(Locale.US,"%.2f", volumeSolution));
                textViewDehydratedAlcohol
                        .setText(String.format(Locale.US,"%.2f", dehydratedAlcohol));
                textViewMaxStrength
                        .setText(String.format(Locale.US, "%.2f",
                                maxStrength(dehydratedAlcohol, volumeSolution)));
            }
        });
    }

    /**
     * Method for initializing ui elements.
     */
    private void initViews() {
        buttonResult = (Button) findViewById(R.id.braga_button);
        editTextWeightSugar = (EditText) findViewById(R.id.braga_weight_sugar);
        editTextWaterVolume = (EditText) findViewById(R.id.braga_water_volume);
        textViewMaxStrength = (TextView) findViewById(R.id.braga_max_strength);
        textViewDehydratedAlcohol = (TextView) findViewById(R.id.braga_dehydrated_alcohol);
        textViewVolumeSolution = (TextView) findViewById(R.id.braga_volume_solution);
    }

    /**
     * Checking fields for empty values.
     * Change the type of the entered value to Double.
     */
    private void parserStringToDoubleForResources() {
        String textWeightSugar = editTextWeightSugar.getText().toString();
        String textWaterVolume = editTextWaterVolume.getText().toString();
        if (textWeightSugar.matches("")) {
            Toast.makeText(this, R.string.braga_exception_blank_field_weight_sugar,
                    Toast.LENGTH_LONG).show();
        } else if (textWaterVolume.matches("")) {
            Toast.makeText(this, R.string.braga_exception_blank_field_water_volume,
                    Toast.LENGTH_LONG).show();
        } else {
            sugarWeight = Double.parseDouble(textWeightSugar);
            waterVolume = Double.parseDouble(textWaterVolume);
        }
    }

    /**
     * Calculating of absolute alcohol.
     * @param sugarWeight Weight of sugar.
     */
    private double dehydratedAlcohol(double sugarWeight) {
        return sugarWeight * 0.62;
    }

    /**
     * Conversion from the weight of sugar to volume.
     * @param sugarWeight Weight of sugar.
     * @return Sugar volume in solution.
     */
    private double mgSugarPerMl(double sugarWeight) {
        return sugarWeight * 0.63;
    }

    /**
     * Calculating volume of wort.
     * @param volumeWater Water volume.
     * @param sugarVolume Sugar volume in solution.
     * @return Wort volume.
     */
    private double volumeSolution(double volumeWater, double sugarVolume) {
        return volumeWater + sugarVolume;
    }

    /**
     * Calculation of the maximum strength in the braga.
     * @param dehydratedAlcohol Absolute alcohol.
     * @param volumeSolution The total wort volume.
     * @return Maximum strength in the braga.
     */
    private double maxStrength(double dehydratedAlcohol, double volumeSolution) {
        return (dehydratedAlcohol / volumeSolution) * 100;
    }
}
