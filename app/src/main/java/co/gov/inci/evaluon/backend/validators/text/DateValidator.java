package co.gov.inci.evaluon.backend.validators.text;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class DateValidator extends TextValidator {
    SimpleDateFormat sdf;

    public DateValidator(Context context, View v, int errorId, String format) {
        super(context, v, errorId);
        sdf = new SimpleDateFormat(format);
    }

    @Override public boolean validate(View view) throws Exception {
        return sdf.parse(((TextView)view).getText().toString()) != null;
    }
}
