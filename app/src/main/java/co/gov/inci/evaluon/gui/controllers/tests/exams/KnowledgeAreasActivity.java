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
    }

    @Override public void failure(RetrofitError error) {
        BoolException exception = (BoolException) BoolExceptionConverter.parse(error);
        ToastService.byResource(this, BoolException.ERROR_DICT.get(exception.getMessage()));
        finish();
    }
}
