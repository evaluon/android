package co.gov.inci.evaluon.backend.models.apisets;

import co.gov.inci.evaluon.backend.models.classes.user.Evaluee;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.PUT;

/**
 * Created by pandres95 on 4/3/15.
 */
public interface Evaluees {

    @PUT("/evaluee") public void update(@Body Evaluee.Info evaluee,
                                        Callback<ApiResponse<Void>> evalueeCallback);

}
