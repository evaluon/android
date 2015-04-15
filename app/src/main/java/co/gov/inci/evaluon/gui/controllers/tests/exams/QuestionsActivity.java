package co.gov.inci.evaluon.gui.controllers.tests.exams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.classes.questions.Answer;
import co.gov.inci.evaluon.backend.models.classes.test.KnowledgeArea;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.interfaces.Question;
import co.gov.inci.evaluon.backend.models.proxies.TestsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.DynamicSizedListView;
import co.gov.inci.evaluon.backend.services.gui.OnDataSetChangedListener;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.gui.adapters.listadapters.QuestionsListAdapter;
import co.gov.inci.evaluon.gui.controllers.home.MainActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class QuestionsActivity extends ActionBarActivity
        implements Callback<ApiResponse<Question[]>>, CompoundButton.OnCheckedChangeListener,
        View.OnClickListener {

    ListView listView;
    ToggleButton hideButton;
    ScrollView scrollView;
    Button sendButton;
    List<Answer> sendList = new ArrayList<>();

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
        sendButton.setOnClickListener(this);
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

    @Override public void onClick(View v) {

        if(!listView.getAdapter().isEmpty()){
            for(int i = 0; i < listView.getAdapter().getCount(); i++){
                Question question = (Question)listView.getAdapter().getItem(i);
                if(question.getAnswer() != null) sendList.add(question.getAnswer());
            }
            closeNextQuestion();
        } else {
            closeArea();
        }

    }

    private void closeNextQuestion() {

        if(!sendList.isEmpty()){
            new TestsProxy(this).answerQuestion(sendList.get(0), answerCallback);
        } else {
            closeArea();
        }

    }

    private Callback<ApiResponse<Void>> answerCallback = new Callback<ApiResponse<Void>>() {
        @Override public void success(ApiResponse<Void> voidApiResponse, Response response) {
            if(!sendList.isEmpty()) sendList.remove(0);
            closeNextQuestion();
        }

        @Override public void failure(RetrofitError error) {
            ToastService.error(QuestionsActivity.this, error);
            if(!sendList.isEmpty()) sendList.remove(0);
            closeNextQuestion();
        }
    };

    private void closeArea() {
        Intent intent = new Intent(this, KnowledgeAreasActivity.class);
        intent.putExtra("id", getIntent().getIntExtra("test", 0));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
