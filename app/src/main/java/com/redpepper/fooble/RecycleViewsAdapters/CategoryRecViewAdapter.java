package com.redpepper.fooble.RecycleViewsAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.redpepper.fooble.R;
import com.redpepper.fooble.database.CategoriesEntity;
import java.util.List;

public class CategoryRecViewAdapter extends RecyclerView.Adapter<CategoryRecViewAdapter.MyViewHolder> {

    private List<CategoriesEntity> allCatEntities;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView categoryName;
        public ImageView categoryBack;


        MyViewHolder(View view) {
            super(view);

            categoryName = view.findViewById(R.id.catlistitem_text);
            categoryBack = view.findViewById(R.id.catlistitem_back);

        }
    }

    public CategoryRecViewAdapter(Context context,List<CategoriesEntity> allCatEntities) {
        this.allCatEntities = allCatEntities;
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

        CategoriesEntity entity = allCatEntities.get(position);

        holder.categoryName.setText(entity.getName());
        holder.categoryBack.setBackgroundResource(GetTeamDrawable("cat_"+entity.getId()));
    }

    private Integer GetTeamDrawable(String teamName){

        String name = teamName;

        if(name.contains(" ")){

            name = name.replace(" ","_");

        }

        return context.getResources().getIdentifier( name.toLowerCase() , "drawable", context.getPackageName());

    }
}




