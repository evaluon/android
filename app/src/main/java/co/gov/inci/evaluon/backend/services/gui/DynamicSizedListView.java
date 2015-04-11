package co.gov.inci.evaluon.backend.services.gui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DynamicSizedListView extends ListView {

    public DynamicSizedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int newHeight = 0;
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode != MeasureSpec.EXACTLY) {
            ListAdapter listAdapter = getAdapter();

            if (listAdapter != null && !listAdapter.isEmpty()) {
                int listPosition;
                for (listPosition = 0; listPosition < listAdapter.getCount(); listPosition++) {
                    View listItem = listAdapter.getView(listPosition, null, this);

                    if (listItem instanceof ViewGroup) listItem.setLayoutParams(new LayoutParams(
                            LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT
                    ));

                    listItem.measure(widthMeasureSpec, heightMeasureSpec);
                    newHeight += listItem.getMeasuredHeight();
                }

                newHeight += getDividerHeight() * listPosition;
            }

            if (heightMode == MeasureSpec.AT_MOST && newHeight > heightSize) {
                newHeight = heightSize;
            }

        } else {
            newHeight = getMeasuredHeight();
        }
        setMeasuredDimension(getMeasuredWidth(), newHeight);
    }

}
