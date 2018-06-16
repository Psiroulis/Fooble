package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AgeInformation extends Activity {
    private TextView title,infoText;

    private Button goNext;

    private int selectedAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_information);

        FindTheViews();

        Intent intent = getIntent();

        selectedAge = intent.getIntExtra("selectedAge",0);


        if(selectedAge>= 8 && selectedAge <= 10){

            infoText.setText(R.string.info_810);

            title.setText("8-10");

        }else if(selectedAge>= 11 && selectedAge <= 12){

            infoText.setText(R.string.info_1112);

            title.setText("11-12");

        }else if(selectedAge>= 13 && selectedAge <= 15){

            infoText.setText(R.string.info_1315);

            title.setText("13-15");

        }else if(selectedAge>= 16 && selectedAge <= 17){

            infoText.setText(R.string.info_1617);

            title.setText("16-17");

        }else if(selectedAge > 0 && selectedAge >= 18){

            infoText.setText(R.string.info_18plus);

            title.setText("18 +");
        }

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgeInformation.this,AllCategoriesList.class);

                intent.putExtra("selectedAge",selectedAge);

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
