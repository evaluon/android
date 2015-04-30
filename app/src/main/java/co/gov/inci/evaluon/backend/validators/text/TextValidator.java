package co.gov.inci.evaluon.backend.validators.text;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import co.gov.inci.evaluon.backend.validators.FieldValidator;

public abstract class TextValidator extends FieldValidator
        implements View.OnFocusChangeListener, TextWatcher{

    public TextValidator(Context context, View v, int errorId){
        super(context, v, errorId);
    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override public void afterTextChanged(Editable s) {
        try {
            ((TextView) getView()).setError(!validate(getView())? getError() : null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus) try {
            ((TextView) v).setError(!validate(v)? getError() : null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
