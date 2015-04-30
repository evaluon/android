package co.gov.inci.evaluon.backend.validators.text;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import co.gov.inci.evaluon.R;

public class PasswordValidator extends TextValidator {
    private final int minChars;

    public PasswordValidator(Context context, View v, int minChars) {
        super(context, v, R.string.validation_pass_required);
        this.minChars = minChars;
    }

    @Override public boolean validate(View view) throws Exception {
        return ((TextView)view).length() >= minChars;
    }

}
