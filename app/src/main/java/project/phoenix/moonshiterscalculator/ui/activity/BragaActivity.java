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


public class BragaActivity extends AppCompatActivity {
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
                textViewVolumeSolution.setText(String.format(Locale.getDefault(),"%.2f", volumeSolution(waterVolume, mgSugarPerMl(sugarWeight))));
                textViewDehydratedAlcohol.setText(String.format(Locale.getDefault(),"%.2f", dehydratedAlcohol(sugarWeight)));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void initViews() {
        buttonResult = (Button) findViewById(R.id.braga_button);
        editTextWeightSugar = (EditText) findViewById(R.id.braga_weight_sugar);
        editTextWaterVolume = (EditText) findViewById(R.id.braga_water_volume);
        textViewMaxStrength = (TextView) findViewById(R.id.braga_max_strength);
        textViewDehydratedAlcohol = (TextView) findViewById(R.id.braga_dehydrated_alcohol);
        textViewVolumeSolution = (TextView) findViewById(R.id.braga_volume_solution);
    }

    private void parserStringToDoubleForResources() {
        String textWeightSugar = editTextWeightSugar.getText().toString();
        String textWaterVolume = editTextWaterVolume.getText().toString();
        if (textWeightSugar.matches("")) {
            Toast.makeText(this, R.string.braga_exception_blank_field_weight_sugar, Toast.LENGTH_LONG).show();
        } else if (textWaterVolume.matches("")) {
            Toast.makeText(this, R.string.braga_exception_blank_field_water_volume, Toast.LENGTH_LONG).show();
        } else {
            sugarWeight = Double.parseDouble(textWeightSugar);
            waterVolume = Double.parseDouble(textWaterVolume);
        }
    }

    private double dehydratedAlcohol(double sugarWeight) {
        return sugarWeight * 0.62;
    }

    private double mgSugarPerMl(double sugarWeight) {
        return sugarWeight * 0.63;
    }

    private double volumeSolution(double volumeWater, double volumeSugar) {
        return volumeWater + volumeSugar;
    }
}
