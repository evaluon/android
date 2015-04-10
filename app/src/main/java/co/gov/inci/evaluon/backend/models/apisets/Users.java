package co.gov.inci.evaluon.backend.models.apisets;

import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;

public interface Users {

    @GET("/user") public void retrieve(Callback<ApiResponse<User>> userCallback);
    @PUT("/user") public void update(@Body User user, Callback<ApiResponse<User>> userCallback);

}
