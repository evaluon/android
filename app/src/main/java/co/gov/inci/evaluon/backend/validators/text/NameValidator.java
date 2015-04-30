package co.gov.inci.evaluon.backend.validators.text;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class NameValidator extends TextValidator {

    public NameValidator(Context context, View v, int errorId) {
        super(context, v, errorId);
    }

    @Override public boolean validate(View view) {
        return ((TextView)view).length() > 0;
    }

}
