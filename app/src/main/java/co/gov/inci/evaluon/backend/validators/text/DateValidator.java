package co.gov.inci.evaluon.backend.validators.text;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DateValidator extends TextValidator {
    ArrayList<SimpleDateFormat> formatters;

    public DateValidator(Context context, View v, int errorId, final String[] formats) {
        super(context, v, errorId);
        formatters = new ArrayList<>();
        for(int i = 0; i < formats.length; i++){
            formatters.add(new SimpleDateFormat(formats[i]));
        }
    }

    public DateValidator(Context context, View v, int errorId, final String format) {
        super(context, v, errorId);
        formatters = new ArrayList<SimpleDateFormat>(){{
            add(new SimpleDateFormat(format));
        }};
    }

    @Override public boolean validate(View view) throws Exception {
        String text = ((TextView)view).getText().toString();
        for(SimpleDateFormat formatter : formatters){
            try {
                if(formatter.parse(text) != null) return true;
            } catch (Exception e){}
        }
        return false;
    }
}
