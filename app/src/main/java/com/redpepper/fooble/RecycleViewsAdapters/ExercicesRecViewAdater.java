package com.redpepper.fooble.RecycleViewsAdapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.redpepper.fooble.Exercise;
import com.redpepper.fooble.ExerciseActivity;
import com.redpepper.fooble.R;

import java.util.List;

public class ExercicesRecViewAdater extends RecyclerView.Adapter<ExercicesRecViewAdater.MyViewHolder> {

    private List<Exercise> allCategorysExercices;
    private List<Integer> allDoneExrcises;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView exerciseThumb;
        public TextView exerciseName;
        public TextView exerciseLevel;
        public Button exerciseProcced;
        public RelativeLayout exerciseLevelLayout;
        public ImageView exerciseDone;

        public MyViewHolder(View itemView) {

            super(itemView);

            exerciseThumb = itemView.findViewById(R.id.exerItemThumbImg);
            exerciseName = itemView.findViewById(R.id.exerItemNameText);
            exerciseLevel = itemView.findViewById(R.id.exerItemLevelText);
            exerciseProcced = itemView.findViewById(R.id.exerItemProccedBtn);
            exerciseLevelLayout = itemView.findViewById(R.id.exerItemLevelLay);
            exerciseDone = itemView.findViewById(R.id.exerItemDoneImg);

        }
    }

    public ExercicesRecViewAdater(List<Exercise> allCategorysExercices, List<Integer> allDoneExercises, Context context) {
        this.allCategorysExercices = allCategorysExercices;
        this.allDoneExrcises = allDoneExercises;
        this.context = context;
    }

    @NonNull
    @Override
    public ExercicesRecViewAdater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercicesRecViewAdater.MyViewHolder holder, int position) {

        final Exercise exercise = allCategorysExercices.get(position);

        holder.exerciseThumb.setImageResource(R.drawable.cat_im);
        holder.exerciseName.setText(exercise.getName());
        holder.exerciseLevel.setText(exercise.getLevelText());
        String level = exercise.getLevelText();

        if(level.equalsIgnoreCase("Easy")){
            holder.exerciseLevelLayout.setBackgroundColor(Color.parseColor("#01ff1f"));
        }else if(level.equalsIgnoreCase("Medium")){
            holder.exerciseLevelLayout.setBackgroundColor(Color.parseColor("#ff5601"));
        }else if(level.equalsIgnoreCase("Hard")){
            holder.exerciseLevelLayout.setBackgroundColor(Color.parseColor("#ff1601"));
        }

        if(allDoneExrcises.contains(exercise.getId())){
            holder.exerciseDone.setImageResource(R.drawable.exerc_done);
        }else{
            holder.exerciseDone.setImageResource(R.drawable.exerc_undone);
        }

        holder.exerciseProcced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ExerciseActivity.class);

                intent.putExtra("exerciseId",exercise.getId());

                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return allCategorysExercices.size();
    }
}
