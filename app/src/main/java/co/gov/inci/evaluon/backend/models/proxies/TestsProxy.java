package co.gov.inci.evaluon.backend.models.proxies;

import android.content.Context;

import co.gov.inci.evaluon.backend.models.apisets.Tests;
import co.gov.inci.evaluon.backend.models.classes.test.KnowledgeArea;
import co.gov.inci.evaluon.backend.models.classes.test.Test;
import co.gov.inci.evaluon.backend.models.classes.questions.Answer;
import co.gov.inci.evaluon.backend.models.interfaces.Question;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import retrofit.Callback;

public class TestsProxy extends ApiProxy<Tests> {

    public TestsProxy(Context context) {
        super(context, Tests.class, true);
    }

    public void getSelfTest(Callback<ApiResponse<Test[]>> callback){
        api.getSelfTest(callback);
    }

    public void createSelfTest(Callback<ApiResponse<Test>> callback){
        api.createSelfTest(callback);
    }

    public void openTest(int id, Test test, Callback<ApiResponse<Void>> callback){
        api.openTest(id, test, callback);
    }

    public void closeTest(int id, Callback<ApiResponse<Void>> callback){
        api.closeTest(id, callback);
    }

    public void getKnowledgeAreas(int id, Callback<ApiResponse<KnowledgeArea[]>> callback){
        api.getKnowledgeAreas(id, callback);
    }

    public void getQuestions(int id, String knowledgeArea,
                             Callback<ApiResponse<Question[]>> callback){
        api.getQuestions(id, knowledgeArea, callback);
    }

    public void answerQuestion(Answer answer, Callback<ApiResponse<Void>> callback){
        api.answerQuestion(answer, callback);
    }

}
