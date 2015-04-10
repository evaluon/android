package co.gov.inci.evaluon.backend.models.proxies;

import android.content.Context;

import co.gov.inci.evaluon.backend.models.apisets.Evaluees;
import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;

/**
 * Created by pandres95 on 4/3/15.
 */
public class EvalueesProxy extends ApiProxy<Evaluees> {

    public EvalueesProxy(Context context) {
        super(context, Evaluees.class, true);
    }

    public void update(Evaluee.Info evaluee, Callback<ApiResponse<Void>> evalueeCallback){
        api.update(evaluee, evalueeCallback);
    }

}
