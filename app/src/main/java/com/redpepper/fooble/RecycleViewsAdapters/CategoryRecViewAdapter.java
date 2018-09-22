package com.redpepper.fooble.RecycleViewsAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.redpepper.fooble.Category;
import com.redpepper.fooble.ExercicesList;
import com.redpepper.fooble.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryRecViewAdapter extends RecyclerView.Adapter<CategoryRecViewAdapter.MyViewHolder> {

    private List<Category> allCatEntities;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView categoryId;
        public TextView categoryTitle;
        public ImageView categoryBack;
        public TextView categoryShortDescr;
        public Button openCategoryButton;
        public TextView categoryCounter;



        MyViewHolder(View view) {
            super(view);

            categoryId = view.findViewById(R.id.catitemidtxt);
            categoryTitle = view.findViewById(R.id.litCategoryTitleTxt);
            categoryShortDescr = view.findViewById(R.id.catListItemShortDescr);
            categoryBack = view.findViewById(R.id.catlistitem_back);
            openCategoryButton = view.findViewById(R.id.catListItemButton);
            categoryCounter = view.findViewById(R.id.catItemCounter);

        }

    }

    public CategoryRecViewAdapter(Context context,List<Category> allCategories) {
        this.allCatEntities = allCategories;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return allCatEntities.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Category category = allCatEntities.get(position);

        holder.categoryId.setText(String.valueOf(category.getId()));
        holder.categoryTitle.setText(String.valueOf(category.getTitle()));
        holder.categoryShortDescr.setText(String.valueOf(category.getShortDescription()));
        holder.openCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ExercicesList.class);

                intent.putExtra("catid",category.getId());

                context.startActivity(intent);

            }
        });

        holder.categoryCounter.setText(String.valueOf(category.getCounter()));

        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.categoryBack);

    }

//    private Integer GetTeamDrawable(String teamName){
//
//        String name = teamName;
//
//        if(name.contains(" ")){
//
//            name = name.replace(" ","_");
//
//        }
//
//        return context.getResources().getIdentifier( name.toLowerCase() , "drawable", context.getPackageName());
//
//    }
}




