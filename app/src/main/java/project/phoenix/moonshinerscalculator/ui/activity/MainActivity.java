package project.phoenix.moonshinerscalculator.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import project.phoenix.moonshinerscalculator.R;
import project.phoenix.moonshinerscalculator.ui.activity.braga.BragaActivity;
import project.phoenix.moonshinerscalculator.ui.activity.correctstregth.CorrectStrengthActivity;
import project.phoenix.moonshinerscalculator.ui.activity.dilution.DilutionActivity;
import project.phoenix.moonshinerscalculator.ui.activity.strengthbytemperature.StrengthByTemperatureActivity;
import project.phoenix.moonshinerscalculator.ui.activity.sugartomonosugar.SugarToGlucoseActivity;
import project.phoenix.moonshinerscalculator.ui.adapter.MoonshineArrayAdapter;
import project.phoenix.moonshinerscalculator.ui.adapter.Section;

/**
 * Activity with main menu.
 */

public class MainActivity extends AppCompatActivity {
    private ArrayList<Object> sections = new ArrayList<>();
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        sections.add(new Section(getResources().getString(R.string.dilution_title),
                getResources().getString(R.string.dilution_description),
                R.mipmap.ic_launcher));
        sections.add(new Section(getResources().getString(R.string.braga_title),
                getResources().getString(R.string.braga_description),
                R.mipmap.ic_launcher));
        sections.add(new Section(getResources().getString(R.string.strength_correct_title),
                getResources().getString(R.string.strength_correct_description),
                R.mipmap.ic_launcher));
        sections.add(new Section(getResources().getString(R.string.sugar_to_glucose_title),
                getResources().getString(R.string.sugar_to_glucose_description),
                R.mipmap.ic_launcher));
        sections.add(new Section(getResources().getString(R.string.strength_by_temperature_title),
                getResources().getString(R.string.strength_by_temperature_description),
                R.mipmap.ic_launcher));
        listViewCreate();
    }

    @Override
    public void onBackPressed() {
        dialogQuitApplication();
    }

    /**
     * Create ListView with MoonshineArrayAdapter
     */
    private void listViewCreate() {
        MoonshineArrayAdapter listAdapter = new MoonshineArrayAdapter(context, sections);
        ListView listView = (ListView) findViewById(R.id.mainList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListViewItemClick(position);
            }
        });
    }

    /**
     * Method for starting needed activity.
     * @param position Position on listView element which pressed.
     */
    private void onListViewItemClick(int position) {
        if (position == 0) {
            Intent intent = new Intent(MainActivity.this, DilutionActivity.class);
            startActivity(intent);
        } else if (position == 1) {
            Intent intent = new Intent(MainActivity.this, BragaActivity.class);
            startActivity(intent);
        } else if (position == 2) {
            Intent intent = new Intent(MainActivity.this, CorrectStrengthActivity.class);
            startActivity(intent);
        } else if (position == 3) {
            Intent intent = new Intent(MainActivity.this, SugarToGlucoseActivity.class);
            startActivity(intent);
        }
         else if (position == 4) {
            Intent intent = new Intent(MainActivity.this, StrengthByTemperatureActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Dialog menu for quit application.
     */
    private void dialogQuitApplication() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(MainActivity.this);
        quitDialog.setTitle("Закрыть приложение?");
        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        quitDialog.show();
    }
}
