package co.gov.inci.evaluon.backend.models.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import co.gov.inci.evaluon.R;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class ResourcesMenuItem extends MenuItem implements View.OnClickListener {

    private int labelText;
    private int descriptorText;
    private int image;

    public ResourcesMenuItem(int labelText, int descriptorText, int image) {
        this(labelText, descriptorText, image, null);
    }

    public ResourcesMenuItem(int labelText, int descriptorText, int image, Intent intent) {
        super(intent);
        this.labelText = labelText;
        this.descriptorText = descriptorText;
        this.image = image;
    }

    public int getLabelText() {
        return labelText;
    }

    public int getDescriptorText() {
        return descriptorText;
    }

    public int getImage() {
        return image;
    }

    @Override public void writeTitle(TextView et) {
        et.setText(getLabelText());
    }

    @Override public void drawImage(ImageView iv) {
        iv.setContentDescription(String.format(
                "%s. %s.",
                getContext().getString(getDescriptorText()),
                getContext().getString(R.string.descriptor_button)
        ));
        iv.setImageResource(getImage());
    }

}
