package co.gov.inci.evaluon.backend.models.apisets;

import org.json.JSONObject;

import co.gov.inci.evaluon.backend.models.classes.test.Test;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface Groups {

    @GET("/test/group/{id}") public void getTests(@Path("id") int groupId,
                                                      Callback<ApiResponse<Test[]>> testsCallback);

    @GET("/test/group/{id}/active") public void getActiveTests(
            @Path("id") int groupId, Callback<ApiResponse<Test[]>> testsCallback);

}
