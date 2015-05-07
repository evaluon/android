package co.gov.inci.evaluon.backend.validators;

import android.content.Context;
import android.view.View;

import java.text.ParseException;

public abstract class FieldValidator {
    private final Context context;
    private final View view;
    private final int errorId;

    public FieldValidator(Context context, View view, int errorId) {
        this.context = context;
        this.view = view;
        this.errorId = errorId;
    }

    public String getString(int resId){
        return context.getString(resId);
    }

    public String getError(){
        return getString(errorId);
    }

    public View getView(){
        return view;
    }

    public abstract boolean validate(View view) throws Exception;

    public boolean check() throws Exception{
        return validate(getView());
    }

}
