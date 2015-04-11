package co.gov.inci.evaluon.backend.models.classes.questions;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.interfaces.Question;

public class OpenQuestion extends Question implements TextWatcher {

    @Override public View render(int position, View view) {
        view = super.render(position, view);
        view.findViewById(R.id.text_open).setVisibility(View.VISIBLE);

        ((EditText)view.findViewById(R.id.text_open)).addTextChangedListener(this);
        return view;
    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override public void afterTextChanged(Editable s) {
        int testId = ((Activity)getContext()).getIntent().getIntExtra("test", 0);
        int questionId = getId();
        String text = s.toString();
        setAnswer(new Answer(testId, questionId, text));
    }
}
