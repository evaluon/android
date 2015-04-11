package co.gov.inci.evaluon.gui.adapters.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.interfaces.Question;
import co.gov.inci.evaluon.backend.services.gui.OnDataSetChangedListener;

public class QuestionsListAdapter extends ArrayAdapter<Question> implements Filterable {

    public QuestionsListAdapter(Context context, Question[] questions) {
        super(context, R.layout.item_test_menu, questions);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        Question question = getItem(position);
        question.setContext(getContext());

        convertView = LayoutInflater.from(getContext()).inflate(
                R.layout.item_question, parent, false
        );

        return question.render(position, convertView);
    }

    @Override public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


}
