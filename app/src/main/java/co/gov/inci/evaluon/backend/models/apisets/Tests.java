package co.gov.inci.evaluon.backend.models.apisets;

import co.gov.inci.evaluon.backend.models.classes.test.KnowledgeArea;
import co.gov.inci.evaluon.backend.models.classes.test.Test;
import co.gov.inci.evaluon.backend.models.interfaces.Question;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface Tests {

    @POST("/test/{id}/open") public void openTest(@Path("id") int id, @Body Test test,
                                                  Callback<ApiResponse<Void>> callback);

    @GET("/test/{id}/knowledgearea") public void getKnowledgeAreas(
            @Path("id") int id, Callback<ApiResponse<KnowledgeArea[]>> callback
    );

    @GET("/test/{id}/question/{ka}") public void getQuestions(
            @Path("id") int id, @Path("ka") String knowledgeArea,
            Callback<ApiResponse<Question[]>> callback
    );

}
