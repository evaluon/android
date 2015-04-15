package co.gov.inci.evaluon.backend.models.proxies;

import android.content.Context;

import co.gov.inci.evaluon.backend.models.apisets.Results;
import co.gov.inci.evaluon.backend.models.classes.institutions.Institution;
import co.gov.inci.evaluon.backend.models.classes.results.Indicator;
import co.gov.inci.evaluon.backend.models.classes.results.TestResult;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;

public class ResultsProxy extends ApiProxy<Results> {

    public ResultsProxy(Context context) {
        super(context, Results.class, true);
    }

    public void getInstitutions(Callback<ApiResponse<Institution[]>> callback){
        api.getInstitutions(callback);
    }

    public void testResults(int id, Callback<ApiResponse<TestResult[]>> callback){
        api.testResults(id, callback);
    }

    public void getIndicator(Callback<ApiResponse<Indicator>> callback){
        api.getIndicator(callback);
    }

}
