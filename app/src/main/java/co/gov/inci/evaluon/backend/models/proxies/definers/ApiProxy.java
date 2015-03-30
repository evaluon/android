package co.gov.inci.evaluon.backend.models.proxies.definers;

import android.content.Context;
import android.util.TypedValue;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.converters.JacksonConverter;
import retrofit.RestAdapter;

/**
 * @author Pablo Dorado <pandres95@boolinc.co>
 */
public abstract class ApiProxy<T> {

    protected String clientId, clientSecret, endpointUrl;

    private RestAdapter restAdapter;
    protected Context context;
    protected T api;

    public Context getContext(){
        return context;
    }

    public ApiProxy(Context context, Class<T> type){
        this.context = context;
        clientId = context.getString(R.string.client_id);
        clientSecret = context.getString(R.string.client_secret);
        endpointUrl = context.getString(R.string.endpointUrl);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpointUrl)
                .setConverter(new JacksonConverter(new ObjectMapper()))
                .build();

        api = restAdapter.create(type);

    }

}
