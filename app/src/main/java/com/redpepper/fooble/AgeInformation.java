package com.redpepper.fooble;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class AgeInformation extends Activity {
    private TextView title;

    private TextView infoText;

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
