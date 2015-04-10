package co.gov.inci.evaluon.backend.models.proxies;

import android.content.Context;

import org.json.JSONObject;

import co.gov.inci.evaluon.backend.models.apisets.Groups;
import co.gov.inci.evaluon.backend.models.classes.test.Test;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;

public class GroupsProxy extends ApiProxy<Groups> {

    public GroupsProxy(Context context) {
        super(context, Groups.class, true);
    }

    public void getTests(int groupId, Callback<ApiResponse<Test[]>> responseCallback){
        api.getTests(groupId, responseCallback);
    }

    public void getActiveTests(int groupId, Callback<ApiResponse<Test[]>> responseCallback){
        api.getActiveTests(groupId, responseCallback);
    }

}
