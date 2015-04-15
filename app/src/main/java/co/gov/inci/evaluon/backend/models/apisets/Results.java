package co.gov.inci.evaluon.backend.models.apisets;

import co.gov.inci.evaluon.backend.models.classes.institutions.Institution;
import co.gov.inci.evaluon.backend.models.classes.results.Indicator;
import co.gov.inci.evaluon.backend.models.classes.results.TestResult;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface Results {

    @GET("/results") public void getInstitutions(Callback<ApiResponse<Institution[]>> callback);

    @GET("/results/{i}") public void testResults(@Path("i") int id,
                                                 Callback<ApiResponse<TestResult[]>> callback);

    @GET("/indicator") public void getIndicator(Callback<ApiResponse<Indicator>> indicator);

}
