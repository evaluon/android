package co.gov.inci.evaluon.gui.adapters.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.caverock.androidsvg.SVGImageView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.MainMenuItem;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class MainMenuListAdapter extends ArrayAdapter<MainMenuItem> {

    public MainMenuListAdapter(Context context, MainMenuItem[] items) {
        super(context, R.layout.item_main_menu, items);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MainMenuItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(
                    getContext()
            ).inflate(R.layout.item_main_menu, parent, false);
        }
        // Lookup view for data population
        TextView label = (TextView) convertView.findViewById(R.id.button_item_label);
        SVGImageView svg = (SVGImageView) convertView.findViewById(R.id.button_item);

        label.setText(item.getLabelText());
        svg.setContentDescription(getContext().getString(item.getDescriptorText()));
        svg.setImageResource(item.getImage());

        // Return the completed view to render on screen
        return convertView;
    }

}
