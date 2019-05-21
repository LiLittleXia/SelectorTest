package com.lz.selectortest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/5/21.
 */

public class TextSelector extends Selector {

    private TextView tv;
    private ImageView iv;

    private String text;
    private int iconResId;
    private int indicatorResId;
    private int textColor;
    private int textSize;

    public TextSelector(@NonNull Context context) {
        super(context);
    }

    public TextSelector(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextSelector(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onObtainAttrs(TypedArray typedArray) {
        text = typedArray.getString(R.styleable.Selector_text);
        iconResId = typedArray.getResourceId(R.styleable.Selector_img, 0);
        indicatorResId = typedArray.getResourceId(R.styleable.Selector_indicator, 0);
        textColor = typedArray.getColor(R.styleable.Selector_text_color, Color.parseColor("#FF222222"));
        textSize = typedArray.getInteger(R.styleable.Selector_text_size, 15);
    }

    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.view_text, null);
        tv = view.findViewById(R.id.tv);
        iv = view.findViewById(R.id.iv);
        onBindView(text, iconResId, indicatorResId, textColor, textSize);
        return view;
    }

    private void onBindView(String text, int iconResId, int indicatorResId, int textColor, int textSize) {
        if (tv != null) {
            tv.setText(text);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            tv.setTextColor(textColor);
        }
//        if (iv != null) {
//            iv.setImageResource(indicatorResId);
//            iv.setAlpha(0);
//        }
    }


    @Override
    protected void onSwitchSelected(boolean isSelect) {
        if (isSelect) {
            tv.setText("11111111111111111111111");
        } else {
            tv.setText("22222222222222222222222");
        }
    }


}
