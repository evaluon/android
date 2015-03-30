package co.gov.inci.evaluon.backend.services.gui;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Pablo Andrés Dorado Suárez <pandres95@boolinc.co>
 */
public class ToastService {

    public static void byResource(Context ctx, int res){
        Toast.makeText(ctx, ctx.getString(res), Toast.LENGTH_SHORT).show();
    }

}
