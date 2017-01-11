package project.phoenix.moonshiterscalculator.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import project.phoenix.moonshiterscalculator.R;

/**
 * Activity for calculation to dilution solutions
 */

public class DilutionActivity extends AppCompatActivity {
    private Button buttonResult;
    private EditText editTextStrength;
    private EditText editTextVolume;
    private EditText editTextRequiredResultStrength;
    private TextView textViewResultVolumeDiluent;
    private TextView textViewResultVolume;
    private double strength;
    private double volume;
    private double requiredResultStrength;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dilution);

        //set on ActionBar homeAsUp button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initialization views for UI elements
        initViews();

        //initialization clicking on the button for calculating and showing it
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parserStringToDoubleForResources();
                textViewResultVolumeDiluent.setText(String.format(Locale.getDefault(),"%.2f",
                        calculateResultVolumeDiluent()));
                textViewResultVolume.setText(String.format(Locale.getDefault(),"%.2f",
                        calculateResultVolume(volume, requiredResultStrength)));
            }
        });
    }

    /**
     * Adding logic to homeAsUp button (return to ActivityMain)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Change logic for Back button. Instead of returning to the previous activity returns
     * to MainActivity.
     */
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    /**
     * Initialization views for UI elements.
     */
    private void initViews() {
        buttonResult = (Button)
                findViewById(R.id.dilution_button);
        editTextStrength = (EditText)
                findViewById(R.id.dilution_strength);
        editTextVolume = (EditText)
                findViewById(R.id.dilution_volume);
        editTextRequiredResultStrength = (EditText)
                findViewById(R.id.dilution_required_result_strength);
        textViewResultVolumeDiluent = (TextView)
                findViewById(R.id.dilution_result_volume_diluent);
        textViewResultVolume = (TextView)
                findViewById(R.id.dilution_result_volume);
    }

    /**
     * Check fields for filling.
     * Migration of fields from String to a double.
     */
    private void parserStringToDoubleForResources() {
        String textStrength = editTextStrength.getText().toString();
        String textVolume = editTextVolume.getText().toString();
        String textRequiredResultStrength = editTextRequiredResultStrength.getText().toString();
        if (textStrength.matches("")) {
            Toast.makeText(this, R.string.dilution_exception_blank_field_strength,
                    Toast.LENGTH_LONG).show();
        } else if (textVolume.matches("")) {
            Toast.makeText(this, R.string.dilution_exception_blank_field_volume,
                    Toast.LENGTH_LONG).show();
        } else if (textRequiredResultStrength.matches("")) {
            Toast.makeText(this, R.string.dilution_exception_blank_field_required_result_strength,
                    Toast.LENGTH_LONG).show();
        } else {
            strength = Double.parseDouble(textStrength);
            volume = Double.parseDouble(textVolume);
            requiredResultStrength = Double.parseDouble(textRequiredResultStrength);
        }
    }

    /**
     * Volume calculation by folding
     *
     * @return result calculation volume
     */
    private double calculateResultVolume(double volume, double volumeDiluent) {
        return volume + volumeDiluent;
    }

    /**
     * Quantity of water to be added to the solution and achieve the required fort.
     *
     * @return volume water for adding to the solution.
     */
    private double calculateResultVolumeDiluent() {
        double result = ((strength / requiredResultStrength) * volume) - volume;
        return result;
    }
}