package co.gov.inci.evaluon.gui.controllers.tests.exams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.adapters.ImageMenuItem;
import co.gov.inci.evaluon.backend.models.adapters.MenuItem;
import co.gov.inci.evaluon.backend.models.classes.exceptions.BoolException;
import co.gov.inci.evaluon.backend.models.classes.test.KnowledgeArea;
import co.gov.inci.evaluon.backend.models.converters.BoolExceptionConverter;
import co.gov.inci.evaluon.backend.models.proxies.TestsProxy;
import co.gov.inci.evaluon.backend.models.proxies.definers.ApiResponse;
import co.gov.inci.evaluon.backend.services.gui.ToastService;
import co.gov.inci.evaluon.gui.adapters.listadapters.ImageMenuListAdapter;
import co.gov.inci.evaluon.gui.controllers.home.MainActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KnowledgeAreasActivity extends ActionBarActivity
        implements Callback<ApiResponse<KnowledgeArea[]>> {

    int testId;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge_areas);
        testId = getIntent().getIntExtra("id", 0);
    }

    @Override protected void onResume() {
        super.onResume();
        new TestsProxy(this).getKnowledgeAreas(testId, this);
    }

    @Override public void success(ApiResponse<KnowledgeArea[]> apiResponse, Response response) {
        KnowledgeArea[] knowledgeAreas = apiResponse.getData();

        if(knowledgeAreas.length > 0){
            MenuItem[] items = new MenuItem[knowledgeAreas.length];

            for(int i = 0; i < knowledgeAreas.length; i++){
                Intent intent = new Intent(this, QuestionsActivity.class);
                intent.putExtra("test", testId);
                intent.putExtra("area", knowledgeAreas[i].getId());

                items[i] = new ImageMenuItem(
                        knowledgeAreas[i].getId(), knowledgeAreas[i].getImage(), intent
                );
            }

            ((GridView)findViewById(R.id.knowledge_areas_menu_grid)).setAdapter(
                    new ImageMenuListAdapter(this, items)
            );

        } else {
            new TestsProxy(this).closeTest(testId, testCloseCallback);
        }
    }

    @Override public void failure(RetrofitError error) {
        ToastService.error(this, error);
        finish();
    }

    private Callback<ApiResponse<Void>> testCloseCallback = new Callback<ApiResponse<Void>>() {
        @Override public void success(ApiResponse<Void> voidApiResponse, Response response) {
            Intent intent = new Intent(KnowledgeAreasActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            ToastService.byResource(KnowledgeAreasActivity.this, R.string.message_test_finished);
        }

        @Override public void failure(RetrofitError error) {
            ToastService.error(KnowledgeAreasActivity.this, error);
        }
    };

}
