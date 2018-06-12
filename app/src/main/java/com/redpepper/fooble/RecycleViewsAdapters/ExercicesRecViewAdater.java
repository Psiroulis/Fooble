package com.redpepper.fooble.RecycleViewsAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.redpepper.fooble.R;
import com.redpepper.fooble.database.ExercisesEntity;

import java.util.List;

public class ExercicesRecViewAdater extends RecyclerView.Adapter<ExercicesRecViewAdater.MyViewHolder> {

    private List<ExercisesEntity> allExercisesList;
    private Context context;

    public ExercicesRecViewAdater(Context context, List<ExercisesEntity> exercicesList) {
        this.allExercisesList = exercicesList;
        this.context = context;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView itemid;
        private TextView itemTitle;
        private ImageView itemBack;

        private MyViewHolder(View view) {
            super(view);

            itemid = view.findViewById(R.id.execitemid);
            itemTitle = view.findViewById(R.id.execitemtitle);
            itemBack = view.findViewById(R.id.execitemback);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public int getItemCount() {
        return allExercisesList.size();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_list_item, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ExercisesEntity execEntity = allExercisesList.get(position);

        holder.itemid.setText(execEntity.getId());
        holder.itemTitle.setText(execEntity.getName());
        holder.itemBack.setBackgroundResource(GetTeamDrawable("cat_"+execEntity.getId()));
    }

    private Integer GetTeamDrawable(String teamName){

        String name = teamName;

        if(name.contains(" ")){

            name = name.replace(" ","_");

        }

        return context.getResources().getIdentifier( name.toLowerCase() , "drawable", context.getPackageName());

    }
}
