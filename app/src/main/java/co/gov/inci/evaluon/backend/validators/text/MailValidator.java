package co.gov.inci.evaluon.backend.validators.text;

import android.content.Context;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Pattern;

import co.gov.inci.evaluon.R;

public class MailValidator extends TextValidator {
    private static final Pattern pattern = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*" +
                    "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    );

    public MailValidator(Context context, View view) {
        super(context, view, R.string.validation_mail_required);
    }

    @Override public boolean validate(View textView) {
        String text = ((TextView)textView).getText().toString();

        return text.length() > 0 &&  pattern.matcher(text).matches();
    }

}
