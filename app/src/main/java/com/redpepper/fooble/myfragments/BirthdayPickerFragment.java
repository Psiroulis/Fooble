package com.redpepper.fooble.myfragments;


import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.fragment.app.Fragment;

import com.redpepper.fooble.R;

import java.lang.reflect.Field;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class BirthdayPickerFragment extends Fragment {

    public BirthdayPickerFragment() {
        // Required empty public constructor
    }

    public static BirthdayPickerFragment newInstance() {
        return new BirthdayPickerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_birthday_picker, container, false);

        final NumberPicker dayPicker = view.findViewById(R.id.mainDayNumberPicker);
        final NumberPicker  monthPicker = view.findViewById(R.id.mainMonthNumberPicker);
        final NumberPicker  yearPicker = view.findViewById(R.id.mainYearNumberPicker);

        final Button proccedButton = view.findViewById(R.id.fragProccedButton);

        setDividerColor(dayPicker,Color.parseColor("#FFFFFF"));
        setDividerColor(monthPicker,Color.parseColor("#FFFFFF"));
        setDividerColor(yearPicker,Color.parseColor("#FFFFFF"));

        setNumberPickerTextColor(dayPicker,Color.parseColor("#FFFFFF"));
        setNumberPickerTextColor(monthPicker,Color.parseColor("#FFFFFF"));
        setNumberPickerTextColor(yearPicker,Color.parseColor("#FFFFFF"));

        Calendar today = Calendar.getInstance();

        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(31);
        dayPicker.setValue(today.get(Calendar.DAY_OF_MONTH));


        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setValue(today.get(Calendar.MONTH) + 1);
        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(newVal == 2){

                    if(yearPicker.getValue() % 2 == 0){

                        dayPicker.setMaxValue(29);

                    }else{

                        dayPicker.setMaxValue(28);

                    }

                }else if(newVal == 4 || newVal == 6 || newVal == 9 || newVal == 11){

                    dayPicker.setMaxValue(30);

                }else{
                    dayPicker.setMaxValue(31);
                }
            }
        });

        yearPicker.setMinValue(1980);
        yearPicker.setMaxValue(today.get(Calendar.YEAR));
        yearPicker.setValue(today.get(Calendar.YEAR));
        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                if(monthPicker.getValue() == 2){
                    if( newVal % 2 == 0 ){

                        dayPicker.setMaxValue(29);

                    }else{
                        dayPicker.setMaxValue(28);

                    }
                }

            }
        });

        proccedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    //Edit NumberPicker Style Classes
    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                }
                catch(NoSuchFieldException e){
                    e.printStackTrace();
                }
                catch(IllegalAccessException e){
                    e.printStackTrace();
                }
                catch(IllegalArgumentException e){
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
