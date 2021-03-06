package com.redpepper.fooble;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.redpepper.fooble.RecycleViewsAdapters.CategoryRecViewAdapter;
import com.redpepper.fooble.myclasses.Category;
import com.redpepper.fooble.myclasses.HttpConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class AllCategoriesActivity extends Activity  {

    private int selectedAge;

    private RecyclerView categoryList;

    //private CategoryRecViewAdapter mAdapter;

    private Context context;

    private List<Category> allCategories;

    private HttpConnection jParser;

    private RelativeLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_categories_list);

        findTheViews();

        context = this;

        jParser = new HttpConnection();

        SharedPreferences prefs = context.getSharedPreferences("prefs",Context.MODE_PRIVATE);

        selectedAge = prefs.getInt("age",0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        allCategories = new ArrayList<>();

        new GetAllCategories().execute();

    }

    private void findTheViews(){

        loadingLayout = findViewById(R.id.catListLoadingLay);
        categoryList = findViewById(R.id.cat_recycleview);

    }

    private class GetAllCategories extends AsyncTask<String,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingLayout.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            categoryList.setHasFixedSize(true);
            CategoryRecViewAdapter mAdapter = new CategoryRecViewAdapter(context,allCategories);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            categoryList.setLayoutManager(mLayoutManager);
            categoryList.setItemAnimator(new DefaultItemAnimator());
            categoryList.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
            categoryList.setAdapter(mAdapter);
            categoryList.setNestedScrollingEnabled(false);

            mAdapter.notifyDataSetChanged();

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingLayout.setVisibility(View.GONE);
                }
            },2000);


        }

        @Override
        protected String doInBackground(String... strings) {

            URL url = null;

            try{

                 url = new URL("http://165.227.168.146/api/get-categories");

            }catch (MalformedURLException ex){
                ex.printStackTrace();
            }

            HashMap<String,String> data = new HashMap<>();
            data.put("lang",String.valueOf(Locale.getDefault().getLanguage()));
            data.put("age",String.valueOf(selectedAge));

            try{

                JSONObject jObj = jParser.makeHttpUrlRequest(url,data,"Post");

                JSONArray categoriesArray = jObj.getJSONArray("categories");

                for (int i = 0; i < categoriesArray.length(); i++){

                    JSONObject oneCategory = categoriesArray.getJSONObject(i);

                    Category category = new Category(
                            Integer.parseInt(oneCategory.get("id").toString()),
                            oneCategory.get("name").toString(),
                            oneCategory.get("description").toString(),
                            oneCategory.get("count").toString(),
                            null);

                    allCategories.add(category);
                }

            }catch (JSONException ex){
                ex.printStackTrace();
            }

            return null;

        }
    }
}
