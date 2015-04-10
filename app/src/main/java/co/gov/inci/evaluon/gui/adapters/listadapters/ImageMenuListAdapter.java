package co.gov.inci.evaluon.gui.adapters.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.caverock.androidsvg.SVGImageView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.MenuItem;

public class ImageMenuListAdapter extends ArrayAdapter<MenuItem> {

    public ImageMenuListAdapter(Context context, MenuItem[] items){
        super(context, R.layout.item_main_menu, items);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        MenuItem item = getItem(position);
        item.setContext(getContext());

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_main_menu, parent, false
            );

        item.writeTitle((TextView) convertView.findViewById(R.id.button_item_label));
        item.drawImage((SVGImageView) convertView.findViewById(R.id.button_item));

        convertView.setOnClickListener(item);
        return convertView;
    }

}
