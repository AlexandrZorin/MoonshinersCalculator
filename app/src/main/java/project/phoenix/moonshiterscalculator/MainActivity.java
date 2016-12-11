package project.phoenix.moonshiterscalculator;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    ArrayList<Section> sections = new ArrayList<>();
    {
        sections.add(new Section("Раздел 1", "описание", R.mipmap.ic_launcher));
        sections.add(new Section("Раздел 1", "описание", R.mipmap.ic_launcher));
        sections.add(new Section("Раздел 1", "описание", R.mipmap.ic_launcher));
        sections.add(new Section("Раздел 1", "описание", R.mipmap.ic_launcher));
        sections.add(new Section("Раздел 1", "описание", R.mipmap.ic_launcher));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MoonshineArrayAdapter adapter = new MoonshineArrayAdapter(this, sections);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, "selected", Toast.LENGTH_LONG).show();
    }
}
