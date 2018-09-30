package com.redpepper.fooble.RecycleViewsAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redpepper.fooble.AllExercisesActivity;
import com.redpepper.fooble.R;
import com.redpepper.fooble.myclasses.Category;

import java.util.List;

public class CategoryRecViewAdapter extends RecyclerView.Adapter<CategoryRecViewAdapter.MyViewHolder> {

    private List<Category> allCatEntities;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView categoryId;
        public TextView categoryTitle;
        public ImageView categoryBack;
        public TextView categoryShortDescr;
        public TextView categoryCounter;



        MyViewHolder(View view) {
            super(view);

            categoryId = view.findViewById(R.id.catitemidtxt);
            categoryTitle = view.findViewById(R.id.litCategoryTitleTxt);
            categoryShortDescr = view.findViewById(R.id.catListItemShortDescr);
            categoryBack = view.findViewById(R.id.catlistitem_back);
            categoryCounter = view.findViewById(R.id.catItemCounter);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, AllExercisesActivity.class);

                    intent.putExtra("catid",Integer.valueOf(categoryId.getText().toString()));

                    context.startActivity(intent);
                }
            });

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
        holder.categoryCounter.setText(String.valueOf(category.getCounter()));

        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(holder.categoryBack);

        holder.categoryBack.setBackgroundResource(R.drawable.cat_im);


    }
}




