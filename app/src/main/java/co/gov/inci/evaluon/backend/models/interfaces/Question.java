package co.gov.inci.evaluon.backend.models.interfaces;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import co.gov.inci.evaluon.R;
import co.gov.inci.evaluon.backend.models.classes.general.Image;
import co.gov.inci.evaluon.backend.models.classes.questions.Answer;
import co.gov.inci.evaluon.backend.models.classes.questions.ClosedQuestion;
import co.gov.inci.evaluon.backend.models.classes.questions.OpenQuestion;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "open")
@JsonSubTypes({
        @JsonSubTypes.Type(name= "0", value = ClosedQuestion.class),
        @JsonSubTypes.Type(name= "1", value = OpenQuestion.class)

})
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Question implements Serializable {

    @JsonProperty("id") private int id;
    @JsonProperty("description_text") private String descriptionText;
    @JsonProperty("knowledge_area_id") private String areaId;
    @JsonProperty("image") private Image image;

    private Answer answer;
    private Context context;

    public Question(){
        this(-1, null, null);
    }

    public Question(int id, String descriptionText, String areaId){
        this(id, descriptionText, areaId, null);
    }

    public Question(int id, String descriptionText, String areaId, Image image) {
        this.id = id;
        this.descriptionText = descriptionText;
        this.areaId = areaId;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getAreaId(){
        return areaId;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public Image getImage() {
        return image;
    }

    @JsonIgnore public Context getContext() {
        return context;
    }

    @JsonIgnore public void setContext(Context ctx){
        this.context = ctx;
    }

    @JsonIgnore public Answer getAnswer(){
        return answer;
    }

    @JsonIgnore public void setAnswer(Answer answer){
        this.answer = answer;
    }

    public View render(int position, View view){
        TextView questionNumber = ((TextView)view.findViewById(R.id.text_question_number));
        TextView questionTitle = ((TextView)view.findViewById(R.id.text_question_description));
        ImageView img = (ImageView)view.findViewById(R.id.image_question);

        questionTitle.setText(getDescriptionText());
        questionNumber.setText(String.format("%d.", position + 1));

        if(image != null) {
            Picasso.with(getContext()).load(image.getLocation(getContext())).into(img);
            img.setContentDescription(image.getDescription());
        }

        return view;
    }

    @Override public String toString() {
        return getAnswer() != null ? "yes" : "no";
    }
}
