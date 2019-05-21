package com.lz.selectortest;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2019/5/21.
 */

public abstract class Selector extends FrameLayout implements View.OnClickListener {


    private String tag;
    private SelectorGroup selectorGroup;


    public Selector(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public Selector(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Selector);
            int tagResId = typedArray.getResourceId(R.styleable.Selector_tag, 0);
            tag = context.getString(tagResId);
            onObtainAttrs(typedArray);
            typedArray.recycle();
        } else {
            tag = "default tag";
        }
        initView(context);
    }

    public void onObtainAttrs(TypedArray typedArray) {
    }

    public Selector(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        View v = onCreateView();
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(v, params);
        this.setOnClickListener(this);
    }

    protected abstract View onCreateView();

    //按钮选中状态变更（在子类中自定义变更效果）
    protected abstract void onSwitchSelected(boolean isSelect);


    @Override
    public void onClick(View view) {
        //通知选中组，当前按钮被选中
        if (selectorGroup != null) {
            selectorGroup.onSelectorClick(this);
        }
    }


    @Override
    public void setSelected(boolean selected) {
        boolean isPreSelected = isSelected();
        super.setSelected(selected);
        if (isPreSelected != selected) {
            onSwitchSelected(selected);
        }
    }

    //将按钮添加到组
    public Selector setGroup(SelectorGroup selectorGroup) {
        this.selectorGroup = selectorGroup;
        selectorGroup.addSelector(this);
        return this;
    }


    @Override
    public int hashCode() {
        return this.tag.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Selector) {
            return ((Selector) obj).tag.equals(this.tag);
        }
        return false;
    }

    public String getSelectorTag() {
        return tag;
    }
}
