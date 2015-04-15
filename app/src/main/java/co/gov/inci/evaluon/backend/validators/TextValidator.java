package co.gov.inci.evaluon.backend.validators;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public abstract class TextValidator implements View.OnFocusChangeListener {
    private final Context context;
    private final int errorId;

    public TextValidator(Context context, int errorId){
        this.context = context;
        this.errorId = errorId;
    }

    @Override public void onFocusChange(View v, boolean hasFocus) {
        Log.d("TextValidator", "TextView has focus: " + hasFocus);
        if(!hasFocus) {
            boolean isValid = validate((TextView) v);
            ((TextView) v).setError(!isValid ? context.getString(errorId) : null);
        }
    }

    public abstract boolean validate(TextView textView);

}
