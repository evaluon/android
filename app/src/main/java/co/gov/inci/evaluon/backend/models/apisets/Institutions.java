package co.gov.inci.evaluon.backend.models.apisets;

import co.gov.inci.evaluon.backend.models.classes.institutions.Group;
import co.gov.inci.evaluon.backend.models.classes.institutions.Institution;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface Institutions {

    @GET("/institution") public void list(
            Callback<ApiResponse<Institution[]>> institutionsCallback
    );

    @GET("/group/{id}") public void getGroups(
            @Path("id") int id, Callback<ApiResponse<Group[]>> groupsCallback
    );

}
