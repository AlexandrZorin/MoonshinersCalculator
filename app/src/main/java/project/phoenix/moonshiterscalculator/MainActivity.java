package project.phoenix.moonshiterscalculator;

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
import android.widget.Toast;

import java.util.ArrayList;

import project.phoenix.moonshiterscalculator.braga.BragaActivity;
import project.phoenix.moonshiterscalculator.dilution.DilutionActivity;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Object> sections = new ArrayList<>();
    private MoonshineArrayAdapter listAdapter;
    private ListView listView;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        sections.add(new Section(getResources().getString(R.string.dilution_title), getResources().getString(R.string.dilution_description), R.mipmap.ic_launcher));
        sections.add(new Section(getResources().getString(R.string.braga_title), getResources().getString(R.string.braga_description), R.mipmap.ic_launcher));
        sections.add(new Section("Раздел 3", "описание", R.mipmap.ic_launcher));
        sections.add(new Section("Раздел 4", "описание", R.mipmap.ic_launcher));
        sections.add(new Section("Раздел 5", "описание", R.mipmap.ic_launcher));
        listViewCreate();
    }

    @Override
    public void onBackPressed() {
        dialogQuitApplication();
    }

    private void listViewCreate() {
        listAdapter = new MoonshineArrayAdapter(context, sections);
        listView = (ListView) findViewById(R.id.mainList);
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
        } else {
            Toast.makeText(context, "selected " + (position + 1) + " element", Toast.LENGTH_SHORT).show();
        }
    }

    private void dialogQuitApplication() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(MainActivity.this);
        quitDialog.setTitle("Выход: Вы уверены?");
        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });
        quitDialog.show();
    }
}
