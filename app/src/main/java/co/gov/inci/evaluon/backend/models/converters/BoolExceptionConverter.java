package co.gov.inci.evaluon.backend.models.converters;

import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.RetrofitError;

/**
 * Created by pandres95 on 4/3/15.
 */
public class BoolExceptionConverter {

    public static Exception parse(RetrofitError error){
        return (error.getBody() != null && ((ApiResponse<Void>)error.getBody()).getError() != null ?
                ((ApiResponse<Void>)error.getBody()).getError() :
                error
        );
    }

}
