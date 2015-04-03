package co.gov.inci.evaluon.backend.models.adapters;

import android.content.Intent;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class MainMenuItem {

    private int labelText;
    private int descriptorText;
    private int image;
    private Intent intent;

    public MainMenuItem(int labelText, int descriptorText, int image) {
        this(labelText, descriptorText, image, null);
    }

    public MainMenuItem(int labelText, int descriptorText, int image, Intent intent) {
        this.labelText = labelText;
        this.descriptorText = descriptorText;
        this.image = image;
        this.intent = intent;
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

    public Intent getIntent(){
        return intent;
    }
}
