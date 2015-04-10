package co.gov.inci.evaluon.backend.models.proxies;

import android.content.Context;

import co.gov.inci.evaluon.backend.models.apisets.Institutions;
import co.gov.inci.evaluon.backend.models.classes.institutions.Group;
import co.gov.inci.evaluon.backend.models.classes.institutions.Institution;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;

public class InstitutionsProxy extends ApiProxy<Institutions> {

    public InstitutionsProxy(Context context) {
        super(context, Institutions.class, true);
    }

    public void list(Callback<ApiResponse<Institution[]>> institutionsCallback){
        api.list(institutionsCallback);
    }

    public void getGroups(int id, Callback<ApiResponse<Group[]>> groupsCallback){
        api.getGroups(id, groupsCallback);
    }

}
