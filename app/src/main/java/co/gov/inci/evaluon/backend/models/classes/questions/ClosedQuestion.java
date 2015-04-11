package co.gov.inci.evaluon.backend.models.classes.questions;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.picasso.Picasso;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.general.Image;
import co.gov.inci.evaluon.backend.models.interfaces.Question;

public class ClosedQuestion extends Question implements RadioGroup.OnCheckedChangeListener {

    @JsonProperty("answers") private QuestionOption[] questionOptions;

    public ClosedQuestion() {
        this(-1, null, null, null, null);
    }

    public ClosedQuestion(int id, String descriptionText, String areaId, Image image,
                          QuestionOption[] questionOptions) {
        super(id, descriptionText, areaId, image);
        this.questionOptions = questionOptions;
    }

    private static final String[] literals = new String[]{ "a", "b", "c", "d" };

    private static final int[] options = new int[]{
            R.id.option_answer_a,
            R.id.option_answer_b,
            R.id.option_answer_c,
            R.id.option_answer_d
    };

    private static final int[] images = new int[]{
            R.id.image_answer_a,
            R.id.image_answer_b,
            R.id.image_answer_c,
            R.id.image_answer_d
    };

    private int lookFor(int id){
        for(int i = 0; i < options.length; i++){
            if(id == options[i]) return i;
        }
        return -1;
    }

    private int lookAnswer(int id){
        for(int i = 0; i < questionOptions.length; i++){
            if(questionOptions[i].getId() == id) return i;
        }
        return -1;
    }

    @Override public View render(int position, View view) {
        view = super.render(position, view);
        view.findViewById(R.id.option_group_closed).setVisibility(View.VISIBLE);

        for(int i = 0; i < questionOptions.length; i++){
            RadioButton rb = (RadioButton)view.findViewById(options[i]);
            RadioGroup rg = (RadioGroup)view.findViewById(R.id.option_group_closed);
            ImageView iv = (ImageView)view.findViewById(images[i]);

            rb.setText(String.format(
                    "%s. %s", literals[i], questionOptions[i].getDescriptionText()
            ));

            if(questionOptions[i].getImage() != null){
                Image img = questionOptions[i].getImage();
                Picasso.with(getContext()).load(img.getLocation(getContext())).into(iv);
                iv.setContentDescription(img.getDescription());
            }

            if(getAnswer() != null){
                rb = (RadioButton)view.findViewById(options[lookAnswer(getAnswer().getAnswerId())]);
                rb.setChecked(true);
            }

            rg.setOnCheckedChangeListener(this);

        }

        return view;
    }

    @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
        int testId = ((Activity)getContext()).getIntent().getIntExtra("test", 0);
        int questionId = getId();
        int answerId = questionOptions[lookFor(checkedId)].getId();

        setAnswer(new Answer(testId, questionId, answerId));

    }


}
