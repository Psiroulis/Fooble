package com.redpepper.fooble.RecycleViewsAdapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.redpepper.fooble.Category;
import com.redpepper.fooble.ExerciseActivity;
import com.redpepper.fooble.R;
import com.redpepper.fooble.database.CategoriesEntity;
import java.util.List;

public class CategoryRecViewAdapter extends RecyclerView.Adapter<CategoryRecViewAdapter.MyViewHolder> {

    private List<Category> allCatEntities;
    private Context context;

   // public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView categoryId;
        public TextView categoryTitle;
        public ImageView categoryBack;
        public TextView categoryShortDescr;
        public ImageView openCategoryButton;



        MyViewHolder(View view) {
            super(view);

            categoryId = view.findViewById(R.id.catitemidtxt);
            categoryTitle = view.findViewById(R.id.catListItemTitle);
            categoryShortDescr = view.findViewById(R.id.catListItemShortDescr);
            categoryBack = view.findViewById(R.id.catlistitem_back);
            openCategoryButton = view.findViewById(R.id.catListItemButton);

//            view.setOnClickListener(this);

        }

//        @Override
//        public void onClick(View view) {
//            Toast.makeText(CategoryRecViewAdapter.this.context,"Id clicked"+categoryId.getText().toString(),Toast.LENGTH_SHORT).show();
//        }
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

                Intent intent = new Intent(context, ExerciseActivity.class);

                intent.putExtra("catid",category.getId());

            }
        });

        holder.categoryBack.setBackgroundResource(GetTeamDrawable("cat_"+category.getId()));
    }

    private Integer GetTeamDrawable(String teamName){

        String name = teamName;

        if(name.contains(" ")){

            name = name.replace(" ","_");

        }

        return context.getResources().getIdentifier( name.toLowerCase() , "drawable", context.getPackageName());

    }
}




