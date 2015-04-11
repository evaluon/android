package co.gov.inci.evaluon.gui.controllers.tests.exams;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.ToggleButton;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.interfaces.Question;
import co.gov.inci.evaluon.backend.models.proxies.TestsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.DynamicSizedListView;
import co.gov.inci.evaluon.backend.services.gui.OnDataSetChangedListener;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.gui.adapters.listadapters.QuestionsListAdapter;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class QuestionsActivity extends ActionBarActivity
        implements Callback<ApiResponse<Question[]>>, CompoundButton.OnCheckedChangeListener {

    ListView listView;
    ToggleButton hideButton;
    ScrollView scrollView;
    Button sendButton;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getQuestions();
    }

    private void setControls(){
        listView = (ListView)findViewById(R.id.list_view_questions);

        hideButton = (ToggleButton)findViewById(R.id.toggle_hide_questions);
        hideButton.setOnCheckedChangeListener(this);

        scrollView = (ScrollView)findViewById(R.id.scroll_questions);

        sendButton = (Button)findViewById(R.id.button_send);
        //sendButton.setOnFocusChangeListener(this)
    }

    private void getQuestions(){
        int testId = getIntent().getIntExtra("test", 0);
        String areaId = getIntent().getStringExtra("area");
        new TestsProxy(this).getQuestions(testId, areaId, this);
    }

    @Override public void success(ApiResponse<Question[]> apiResponse, Response response) {
        Question[] questions = apiResponse.getData();

        setContentView(R.layout.activity_questions);
        setTitle(String.format(
                getString(R.string.title_activity_questions), questions[0].getAreaId()
        ));

        setControls();
        listView.setAdapter(new QuestionsListAdapter(this, questions));
    }

    @Override public void failure(RetrofitError error) {
        BoolException exception = (BoolException) BoolExceptionConverter.parse(error);
        if(exception != null)
            ToastService.byResource(this, BoolException.ERROR_DICT.get(exception.getMessage()));
        finish();
    }

    @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(listView.getAdapter() != null)
            ((QuestionsListAdapter)listView.getAdapter()).getFilter().filter(isChecked ?
                            "no" : ""
            );
    }

}
