package co.gov.inci.evaluon.backend.models.adapters;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class MainMenuItem {

    private int labelText;
    private int descriptorText;
    private int image;

    public MainMenuItem(int labelText, int descriptorText, int image) {
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
}
