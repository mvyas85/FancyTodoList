package com.vyas.customViews;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomSizeTextView extends TextView {
    boolean isMaximized = false;

    public CustomSizeTextView(Context canvas, AttributeSet attributeSet) {
        super(canvas, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();//var2.getDefaultDisplay().getWidth();
        int height = getHeight();//var2.getDefaultDisplay().getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int scrollY = getScrollY();
        int scrollX = getScrollX();
        int lineHeight = getLineHeight();

        int grossWidth = width + scrollX;
        int grossHeight = height + scrollY;

        int netViewHeight = lineHeight + (grossHeight - paddingTop - paddingBottom);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        //paint.setColor(1722066084);
        paint.setColor(1711276032);

        float fontAdjustment = 15.826172F - (paint.getFontMetrics().bottom - paint.getFontMetrics().top);
        float baseline = (float)(scrollY + lineHeight - scrollY % lineHeight) - fontAdjustment;
        float linePadding;
        if(width != 800 && height != 480) {
            linePadding = 2;
        } else {
            linePadding = 4;
        }

        while(baseline < (float)netViewHeight) {
            canvas.drawLine((float)paddingLeft, baseline + (float)paddingTop + linePadding, (float)grossWidth, baseline + (float)paddingTop + linePadding, paint);
            baseline += (float)lineHeight;
        }
        super.onDraw(canvas);
    }

    public boolean isMaximized(){
        return isMaximized;
    }

    public void setMaximized(boolean maximized){
        isMaximized = maximized;
        if(!isMaximized){
            setLines(1);
        }else{
            setSingleLine(false);
        }
    }
}