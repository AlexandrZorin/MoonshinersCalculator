package project.phoenix.moonshiterscalculator.ui.adapter;

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

import project.phoenix.moonshiterscalculator.R;
import project.phoenix.moonshiterscalculator.ui.Section;

/**
 * Custom ListView adapter based on ArrayAdapter.
 * This class expects that the provided objects list which have at least three fields
 * two String and one int for transmission resources in title, description and image.
 */

public class MoonshineArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final List<Object> sections;

    /**
     * Constructor
     *
     * @param context The current context.
     * @param sections Objects list which have fields for title, description and image.
     */
    public MoonshineArrayAdapter(Context context, List<Object> sections) {
        super(context, R.layout.rowlayout, sections);
        this.context = context;
        this.sections = sections;
    }

    /**
     * Returns the item of the position in the list.
     *
     * @param position The position to retrieve the item of.
     *
     * @return The item of the position.
     */
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
        ((TextView) rowView.findViewById(R.id.title)).setText(section.getSectionName());
        ((TextView) rowView.findViewById(R.id.description)).setText(section.getSectionDescription());
        ((ImageView) rowView.findViewById(R.id.image)).setImageResource(section.getImage());
        return rowView;
    }

    Section getSection(int position) {
        return ((Section) getItem(position));
    }
}
