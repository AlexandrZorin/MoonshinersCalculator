package project.phoenix.moonshiterscalculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MoonshineArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final List<Section> sections;

    public MoonshineArrayAdapter(Context context, List<Section> sections) {
        super(context, R.layout.rowlayout, sections);
        this.context = context;
        this.sections = sections;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return sections.get(position);
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = convertView;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        }

        Section section = getSection(position);
        ((TextView) rowView.findViewById(R.id.label)).setText(section.getSectionName());
        ((TextView) rowView.findViewById(R.id.description)).setText(section.getSectionDescription());
        ((ImageView) rowView.findViewById(R.id.image)).setImageResource(section.getImage());
        return rowView;
    }

    Section getSection(int position) {
        return ((Section) getItem(position));
    }
}
