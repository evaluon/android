package co.gov.inci.evaluon.gui.adapters.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.caverock.androidsvg.SVGImageView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.MenuItem;

public class TestMenuListAdapter extends ArrayAdapter<MenuItem> {

    public TestMenuListAdapter(Context context, MenuItem[] objects) {
        super(context, R.layout.item_test_menu, objects);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        MenuItem item = getItem(position);
        item.setContext(getContext());

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_test_menu, parent, false
            );

        item.writeTitle((TextView) convertView.findViewById(R.id.button_item_label));

        convertView.findViewById(R.id.button_item).setOnClickListener(item);
        return convertView;

    }
}
