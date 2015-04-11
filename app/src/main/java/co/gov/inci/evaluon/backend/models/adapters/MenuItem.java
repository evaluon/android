package co.gov.inci.evaluon.backend.models.adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class MenuItem implements View.OnClickListener {

    private Context context;
    private Intent intent;

    protected MenuItem(Intent intent) {
        this.intent = intent;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public abstract void writeTitle(TextView et);
    public abstract void drawImage(ImageView iv);

    @Override public void onClick(View v) {
        if(intent != null){
            context.startActivity(intent);
            if(intent.getBooleanExtra("finish_parent", false)) ((Activity)context).finish();
        }
    }
}
