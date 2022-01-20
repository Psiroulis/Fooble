package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class AgeInfoActivity extends Activity {
    private TextView title;

    private TextView infoText;

    private Button goNext;

    private int selectedAge;

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_information);

        FindTheViews();

        context = this;

        SharedPreferences prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);

        selectedAge = prefs.getInt("age",13);

        if(selectedAge>= 8 && selectedAge <= 10){

            infoText.setText(R.string.info_810);

            title.setText("ΗΛΙΚΙΑΚΟ GROUP 8-10 ΕΤΩΝ");

        }else if(selectedAge>= 11 && selectedAge <= 12){

            infoText.setText(R.string.info_1112);

            title.setText("ΗΛΙΚΙΑΚΟ GROUP 11-12 ΕΤΩΝ");

        }else if(selectedAge>= 13 && selectedAge <= 15){

            infoText.setText(R.string.info_1315);

            title.setText("ΗΛΙΚΙΑΚΟ GROUP 13-15 ΕΤΩΝ");

        }else if(selectedAge>= 16 && selectedAge <= 17){

            infoText.setText(R.string.info_1617);

            title.setText("ΗΛΙΚΙΑΚΟ GROUP 16-17 ΕΤΩΝ");

        }else if(selectedAge > 0 && selectedAge >= 18){

            infoText.setText(R.string.info_18plus);

            title.setText("ΗΛΙΚΙΑΚΟ GROUP 18+ ΕΤΩΝ");
        }else{

            infoText.setText(R.string.info_pre8);
            title.setText("ΗΛΙΚΙΑΚΟ GROUP - ΕΤΩΝ");
        }

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgeInfoActivity.this,AllCategoriesActivity.class);

                intent.putExtra("selectedAge",13);

                startActivity(intent);
            }
        });
    }

    private void FindTheViews(){
       title = findViewById(R.id.titleAgeInfo);
       infoText = findViewById(R.id.mainTextInfo);
       goNext = findViewById(R.id.infoNextButton);

    }

    @Override
    protected void onResume() {
        super.onResume();
   }
}
