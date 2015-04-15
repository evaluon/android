package co.gov.inci.evaluon.gui.controllers.results.institutional;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.results.TestResult;
import co.gov.inci.evaluon.backend.models.proxies.ResultsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class TestResultsActivity extends ActionBarActivity
        implements Callback<ApiResponse<TestResult[]>> {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_results);
        new ResultsProxy(this).testResults(getIntent().getIntExtra("id", 0), this);
    }

    @Override public void success(ApiResponse<TestResult[]> apiResponse, Response response) {
        TestResult[] testResults = apiResponse.getData();
        ((ListView)findViewById(R.id.list_view_test_results)).setAdapter(new ResultsListAdapter(
                this,
                testResults
        ));
    }

    @Override public void failure(RetrofitError error) {
        ToastService.error(this, error);
    }

    private static class ResultsListAdapter extends ArrayAdapter<TestResult> {

        public ResultsListAdapter(Context context, TestResult[] objects) {
            super(context, android.R.layout.two_line_list_item, objects);
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            TestResult result = getItem(position);

            if(convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(
                        android.R.layout.two_line_list_item, parent, false
                );

            ((TextView)convertView.findViewById(android.R.id.text1)).setText(
                    result.getDescription()
            );
            ((TextView)convertView.findViewById(android.R.id.text2)).setText(String.format(
                    getContext().getString(R.string.test_results_label),
                    result.getRightQuestions(),
                    result.getTotalQuestions()
            ));


            return convertView;
        }

    }

}
