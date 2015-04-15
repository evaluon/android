package co.gov.inci.evaluon.gui.controllers.results.average;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.caverock.androidsvg.SVGImageView;

import java.text.SimpleDateFormat;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.results.Indicator;
import co.gov.inci.evaluon.backend.models.interfaces.User;
import co.gov.inci.evaluon.backend.models.proxies.ResultsProxy;
import co.gov.inci.evaluon.backend.models.proxies.UserProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class IndicatorActivity extends ActionBarActivity
        implements Callback<ApiResponse<Indicator>> {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        new UserProxy(this).retrieve(userCallback);
        new ResultsProxy(this).getIndicator(this);
    }

    private Callback<ApiResponse<User>> userCallback = new Callback<ApiResponse<User>>() {
        @Override public void success(ApiResponse<User> userApiResponse, Response response) {
            User user = userApiResponse.getData();
            ((TextView)findViewById(R.id.text_name)).setText(user.getName());
            ((TextView)findViewById(R.id.text_since_date)).setText(
                    new SimpleDateFormat("MMM dd, yyyy").format(user.getRegisterDate())
            );
        }

        @Override public void failure(RetrofitError error) {
            ToastService.error(IndicatorActivity.this, error);
        }
    };


    @Override public void success(ApiResponse<Indicator> apiResponse, Response response) {
        Indicator indicator = apiResponse.getData();

        ((SVGImageView)findViewById(R.id.image_badge)).setImageResource(
                Indicator.imagesMap.get(indicator.getLevelName())
        );
        ((TextView)findViewById(R.id.text_get_grade)).setText(
                String.format(
                        getString(R.string.label_get_grade),
                        Indicator.namesMap.get(indicator.getLevelName())
                )
        );
        ((TextView)findViewById(R.id.text_description)).setText(indicator.getDescription());
        ((TextView)findViewById(R.id.text_right_questions)).setText(""+indicator.getQuestions());
        ((TextView)findViewById(R.id.text_average)).setText(""+indicator.getAverage());
        ((TextView)findViewById(R.id.text_remaining_questions)).setText(
                ""+indicator.getRemainingQuestions()
        );

    }

    @Override public void failure(RetrofitError error) {
        ToastService.error(this, error);
    }

}
