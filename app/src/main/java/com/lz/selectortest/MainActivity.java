package com.lz.selectortest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SelectorGroup multipleGroup = new SelectorGroup();
        multipleGroup.setChoiceMode(SelectorGroup.MODE_SINGLE_CHOICE);
        ((Selector) findViewById(R.id.ts1)).setGroup(multipleGroup);
        ((Selector) findViewById(R.id.ts2)).setGroup(multipleGroup);
    }
}
