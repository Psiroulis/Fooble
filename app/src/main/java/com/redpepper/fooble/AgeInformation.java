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

   private Context context = this;

   private TextView title,infoText;

   private Button goNext;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_information);

        FindTheViews();

        Intent intent = getIntent();

        final String ageGroup = intent.getStringExtra("agegroup");

        title.setText(ageGroup);

        switch (ageGroup){
            case "8-10":
                infoText.setText(R.string.info_810);
                break;
            case "11-12":
                infoText.setText(R.string.info_1112);
                break;
            case "13-15":
                infoText.setText(R.string.info_1315);
                break;
            case "16-17":
                infoText.setText(R.string.info_1617);
                break;
            case "18+":
                infoText.setText(R.string.info_18plus);
                break;
            default:
                Toast.makeText(context,"No AgeGroup Selected",Toast.LENGTH_SHORT).show();
                break;
        }

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgeInformation.this,AllCategoriesList.class);

                intent.putExtra("agegroup",ageGroup);

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
