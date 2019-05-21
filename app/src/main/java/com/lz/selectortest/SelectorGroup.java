package com.lz.selectortest;

import android.view.View;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2019/5/21.
 */

public class SelectorGroup {

    public static final int MODE_SINGLE_CHOICE = 1;
    public static final int MODE_MULTIPLE_CHOICE = 2;

    private Set<Selector> selectors = new HashSet<>();

    private ChoiceAction choiceMode;


    //注入具体选中行为
    public void setChoiceMode(ChoiceAction choiceMode) {
        this.choiceMode = choiceMode;
    }

    //通过这个方法设置默认行为
    public void setChoiceMode(int mode) {
        switch (mode) {
            case MODE_MULTIPLE_CHOICE:
                choiceMode = new MultipleAction();
                break;
            case MODE_SINGLE_CHOICE:
                choiceMode = new SingleAction();
                break;
        }
    }



    public void addSelector(Selector selector){
        selectors.add(selector);
    }



    //遍历所有按钮，将之前选中的按钮设置为未选中
    private void cancelPreSelector(View selector, Set<Selector> selectors) {
        for (Selector s : selectors) {
            if (!s.equals(selector) && s.isSelected()) {
                s.setSelected(false);
            }
        }
    }

    //选中后的行为被抽象成接口
    public interface ChoiceAction {
        void onChoose(Set<Selector> selectors, Selector selector);
    }


    //单选行为
    private class SingleAction implements ChoiceAction {
        @Override
        public void onChoose(Set<Selector> selectors, Selector selector) {
            //将自己选中
            selector.setSelected(true);
            //将除了自己外的其他按钮设置为未选中
            cancelPreSelector(selector, selectors);
        }
    }

    //多选行为
    private class MultipleAction implements ChoiceAction {
        @Override
        public void onChoose(Set<Selector> selectors, Selector selector) {
            //反转自己的选中状态
            boolean isSelected = selector.isSelected();
            selector.setSelected(!isSelected);
        }
    }



}
