package com.redpepper.fooble;

import android.app.Activity;
import android.os.Bundle;

public class AllCategoriesList extends Activity {

    String agegroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories_list);

        agegroup = getIntent().getStringExtra("agegroup");
    }
}
