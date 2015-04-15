package co.gov.inci.evaluon.backend.models.adapters;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.general.Image;

public class ImageMenuItem extends MenuItem {

    private Image image;
    private String title;

    public ImageMenuItem(String title, Image image){
        this(title, image, null);
    }

    public ImageMenuItem(String title, Image image, Intent intent) {
        super(intent);
        this.title = title;
        this.image = image;
    }

    @Override public void writeTitle(TextView et) {
        et.setText(title);
    }

    @Override public void drawImage(ImageView iv) {

        iv.setContentDescription(String.format(
                "%s. %s.", title, getContext().getString(R.string.descriptor_button)
        ));
        if(getContext() != null) Picasso.with(
                getContext()
        ).load(image.getLocation(getContext())).into(iv);
    }

}
