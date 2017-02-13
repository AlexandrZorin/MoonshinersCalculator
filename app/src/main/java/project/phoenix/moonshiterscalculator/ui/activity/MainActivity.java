package project.phoenix.moonshiterscalculator.ui.activity;

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

import project.phoenix.moonshiterscalculator.ui.activity.correctstregth.CorrectStrengthActivity;
import project.phoenix.moonshiterscalculator.ui.adapter.MoonshineArrayAdapter;
import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.adapter.Section;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Object> sections = new ArrayList<>();
    private MoonshineArrayAdapter listAdapter;
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
        sections.add(new Section(getResources().getString(R.string.alcohol_strength_title),
                getResources().getString(R.string.alcohol_strength_description),
                R.mipmap.ic_launcher));
        sections.add(new Section(getResources().getString(R.string.strength_correct_title),
                getResources().getString(R.string.strength_correct_description),
                R.mipmap.ic_launcher));
        listViewCreate();
    }

    @Override
    public void onBackPressed() {
        dialogQuitApplication();
    }

    private void listViewCreate() {
        listAdapter = new MoonshineArrayAdapter(context, sections);
        ListView listView = (ListView) findViewById(R.id.mainList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onListViewItemClick(position);
            }
        });
    }

    private void onListViewItemClick(int position) {
        if (position == 0) {
            Intent intent = new Intent(MainActivity.this, DilutionActivity.class);
            startActivity(intent);
        } else if (position == 1) {
            Intent intent = new Intent(MainActivity.this, BragaActivity.class);
            startActivity(intent);
        } else if (position == 2) {
            Intent intent = new Intent(MainActivity.this, AlcoholStrengthActivity.class);
            startActivity(intent);
        } else if (position == 3) {
            Intent intent = new Intent(MainActivity.this, CorrectStrengthActivity.class);
            startActivity(intent);
        }
    }

    private void dialogQuitApplication() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(MainActivity.this);
        quitDialog.setTitle("Выход: Вы уверены?");
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
