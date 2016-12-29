package project.phoenix.moonshiterscalculator.sectionone;

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

import project.phoenix.moonshiterscalculator.MainActivity;
import project.phoenix.moonshiterscalculator.R;

public class SectionOneActivity extends AppCompatActivity {
    private Button buttonResult;
    private EditText editTextStrength;
    private EditText editTextVolume;
    private EditText editTextRequiredResultStrength;
    private TextView textViewResultVolumeDiluent;
    private TextView textViewResultVolume;
    private String textStrength;
    private String textVolume;
    private String textRequiredResultStrength;
    private double strength;
    private double volume;
    private double requiredResultStrength;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_one);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initializeViews();
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parserStringToIntForResources();
                textViewResultVolumeDiluent.setText(calculateResultVolumeDiluent());
                textViewResultVolume.setText(calculateResultVolume(volume, requiredResultStrength));
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

    private void initializeViews() {
        buttonResult = (Button) findViewById(R.id.button);
        editTextStrength = (EditText) findViewById(R.id.strength);
        editTextVolume = (EditText) findViewById(R.id.volume);
        editTextRequiredResultStrength = (EditText) findViewById(R.id.required_result_strength);
        textViewResultVolumeDiluent = (TextView) findViewById(R.id.result_volume_diluent);
        textViewResultVolume = (TextView) findViewById(R.id.result_volume);
        textStrength = editTextStrength.getText().toString();
        textVolume = editTextVolume.getText().toString();
        textRequiredResultStrength = editTextRequiredResultStrength.getText().toString();
    }

    private void parserStringToIntForResources() {
        if (textStrength.matches("") || textVolume.matches("") ||
                textRequiredResultStrength.matches("")) {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_LONG).show();
        } else {
            strength = Double.parseDouble(editTextStrength.getText().toString());
            volume = Double.parseDouble(editTextVolume.getText().toString());
            requiredResultStrength = Double.parseDouble(editTextRequiredResultStrength.getText().toString());
        }
    }

    private String calculateResultVolume(double volume, double volumeDiluent) {
        return String.valueOf(volume + volumeDiluent);
    }

    private String calculateResultVolumeDiluent() {
        double result = strength * volume / requiredResultStrength - volume;
        return String.valueOf(result);
    }
}