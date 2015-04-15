package co.gov.inci.evaluon.backend.validators;

import android.content.Context;
import android.widget.TextView;

import java.util.regex.Pattern;

import co.gov.inci.evaluon.R;

public class MailValidator extends TextValidator {
    private static final Pattern pattern = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" +
                    "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    );

    public MailValidator(Context context) {
        super(context, R.string.validation_mail_required);
    }

    @Override public boolean validate(TextView textView) {
        String text = textView.getText().toString();

        return text.length() > 0 &&  pattern.matcher(text).matches();
    }

}
