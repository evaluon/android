package co.gov.inci.evaluon.backend.services.gui;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import retrofit.RetrofitError;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class ToastService {

    public static void byResource(Context ctx, int res){
        Toast.makeText(ctx, ctx.getString(res), Toast.LENGTH_SHORT).show();
    }

    public static void success(Context ctx){
        byResource(ctx, R.string.message_success_update);
    }

    public static void error(Context ctx, RetrofitError error){
        BoolException exception = (BoolException) BoolExceptionConverter.parse(error);
        if(exception != null){
            Log.e("Error", error.getUrl(), exception);
            try {
                ToastService.byResource(ctx, BoolException.ERROR_DICT.get(exception.getMessage()));
            } catch(Exception e) {
                ToastService.byResource(ctx, R.string.error_unknown);
            }
        } else
            Toast.makeText(ctx, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

}
