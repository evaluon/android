package co.gov.inci.evaluon.backend.validators.text;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import co.gov.inci.evaluon.R;

public class RepeatPasswordValidator extends TextValidator {
    private final View mainPass;

    public RepeatPasswordValidator(Context context, View v, View mainPass) {
        super(context, v, R.string.validation_pass_repeat_required);
        this.mainPass = mainPass;
    }

    @Override public boolean validate(View view) throws Exception {
        return ((TextView)view).getText().toString().equals(
                ((TextView)mainPass).getText().toString()
        );
    }

}
