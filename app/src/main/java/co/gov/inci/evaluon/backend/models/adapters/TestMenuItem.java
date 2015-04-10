package co.gov.inci.evaluon.backend.models.adapters;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

public class TestMenuItem extends MenuItem {

    private String title;

    public TestMenuItem(String title){
        this(title, null);
    }

    public TestMenuItem(String title, Intent intent) {
        super(intent);
        this.title = title;
    }

    @Override public void writeTitle(TextView et) {
        et.setText(title);
    }

    @Override public void drawImage(ImageView iv) {}
}
