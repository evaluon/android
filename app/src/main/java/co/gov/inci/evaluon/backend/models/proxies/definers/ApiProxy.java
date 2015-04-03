package co.gov.inci.evaluon.backend.models.proxies.definers;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.converters.JacksonConverter;
import co.gov.inci.evaluon.backend.services.accounts.helpers.AccountHelper;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * @author Pablo Dorado <pandres95@boolinc.co>
 */
public abstract class ApiProxy<T> {

    protected String clientId, clientSecret, endpointUrl;

    private RestAdapter restAdapter;
    private boolean needsAuthentication;
    protected Context context;
    protected T api;

    public Context getContext(){
        return context;
    }

    public ApiProxy(Context context, Class<T> type, boolean needsAuthentication){
        this.needsAuthentication = needsAuthentication;
        this.context = context;
        clientId = context.getString(R.string.client_id);
        clientSecret = context.getString(R.string.client_secret);
        endpointUrl = context.getString(R.string.endpointUrl);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpointUrl)
                .setConverter(new JacksonConverter(new ObjectMapper()))
                .setRequestInterceptor(interceptor)
                .build();

        api = restAdapter.create(type);

    }

    private RequestInterceptor interceptor = new RequestInterceptor() {
        @Override public void intercept(RequestFacade request) {
            if(needsAuthentication) request.addHeader(
                    "Authorization",
                    String.format("Bearer %s", AccountHelper.getPassword(getContext()))
            );
        }
    };

}
