package com.compet.myrangeseekbar;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RangeSeekBar<Integer> rangeSeekBar = new RangeSeekBar<Integer>(this);

        final TextView textView = (TextView)findViewById(R.id.textView);
        final TextView textView2 = (TextView)findViewById(R.id.textView2);

        rangeSeekBar.setRangeValues(0, 100);
        rangeSeekBar.setSelectedMinValue(20);
        rangeSeekBar.setSelectedMaxValue(88);

        Collections.sort(new ArrayList<String>());

        LinearLayout layout = (LinearLayout)findViewById(R.id.seekbar_placeholder);
        layout.addView(rangeSeekBar);

        NumberPicker np = (NumberPicker)findViewById(R.id.picker);

        np.setMinValue(10);
        np.setMaxValue(300);
        np.setValue(120);
        setDividerColor(np);
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {

                textView.setText(String.valueOf(newVal));
            }
        });

        String[] s = {"kg", "lbs"};

        NumberPicker np2 = (NumberPicker)findViewById(R.id.picker2);
        np2.setMinValue(0);
        np2.setMaxValue(s.length - 1);
        np2.setDisplayedValues(s);
        setDividerColor(np2);
        np2.setWrapSelectorWheel(false);
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {

                textView2.setText(numberPicker.getDisplayedValues()[newVal]);

            }
        });

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 2500; i++) {
            list.add(String.valueOf(i / 10.0));
        }

        String[] array = list.toArray(new String[list.size()]);
        NumberPicker np3 = (NumberPicker)findViewById(R.id.picker3);
        np3.setMinValue(0);
        np3.setMaxValue(array.length - 1);
        np3.setValue(array.length / 2);
        np3.setDisplayedValues(array);
        setDividerColor(np3);
        np3.setWrapSelectorWheel(false);
        np3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {

                Toast.makeText(MainActivity.this, "값 : " + numberPicker.getDisplayedValues()[newVal], Toast.LENGTH_LONG)
                     .show();

            }
        });

    }

    private void setDividerColor(NumberPicker picker) {
        Field[] numberPickerFields = NumberPicker.class.getDeclaredFields();
        for (Field field : numberPickerFields) {
            if (field.getName().equals("mSelectionDivider")) {
                field.setAccessible(true);
                try {
                    field.set(picker, getResources().getDrawable(R.drawable.customdivider));
                } catch (IllegalArgumentException e) {
                    Log.v(TAG, "Illegal Argument Exception");
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    Log.v(TAG, "Resources NotFound");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    Log.v(TAG, "Illegal Access Exception");
                    e.printStackTrace();
                }
                break;
            }
        }
    }

}
