package com.example.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment1.databinding.FragmentBmiBinding;



public class BMIFragment extends Fragment{

    private FragmentBmiBinding binding;
    ImageView iv_bmi;
    Button btn_show;

    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Height = "heightKey";
    public static final String Weight = "weightKey";
    public static final String Gender = "genderKey";
    public static final String Date = "dateKey";
    public static final String BMI = "bmiKey";
    public static final String STATUS = "statusKey";
    public static final String Advice = "adviceKey";
    public static final String Gender_p = "positionKey";

    TextView tv_get_name,tv_get_weight,tv_get_height,tv_get_gender,tv_get_date,tv_get_bmi,tv_get_status,tv_get_advice;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentBmiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv_get_name = root.findViewById(R.id.tv_get_name);
        tv_get_height = root.findViewById(R.id.tv_get_height);
        tv_get_weight = root.findViewById(R.id.tv_get_weight);
        tv_get_gender = root.findViewById(R.id.tv_get_gender);
        tv_get_date = root.findViewById(R.id.tv_get_date);
        tv_get_bmi = root.findViewById(R.id.tv_get_bmi);
        tv_get_status = root.findViewById(R.id.tv_get_status);
        tv_get_advice = root.findViewById(R.id.tv_get_advice);
        iv_bmi = root.findViewById(R.id.iv_bmi);
        btn_show = root.findViewById(R.id.btn_show);



        sharedpreferences = getContext().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            tv_get_name.setText(sharedpreferences.getString(Name, ""));
        }
        if(sharedpreferences.getString(Weight, "") != "" || sharedpreferences.getString(Height, "") != "") {
            if (sharedpreferences.contains(Weight)) {
                tv_get_weight.setText(sharedpreferences.getString(Weight, "") + " " + "KG");
            }
            if (sharedpreferences.contains(Height)) {
                tv_get_height.setText(sharedpreferences.getString(Height, "") + " " + "CM");
            }
        }
        String gender;
        String[] gender_a = getResources().getStringArray(R.array.Gender);
        if (sharedpreferences.getInt(Gender_p, 0) != 0) {
            gender = gender_a[sharedpreferences.getInt(Gender_p, 0)];
            tv_get_gender.setText(gender);
        }
        if (sharedpreferences.contains(Date)) {
            tv_get_date.setText(sharedpreferences.getString(Date, ""));
        }
        if (sharedpreferences.contains(BMI)) {
            tv_get_bmi.setText(sharedpreferences.getString(BMI, ""));
        }
        if (sharedpreferences.contains(STATUS)) {
            tv_get_status.setText(sharedpreferences.getString(STATUS, ""));
        }
        if (sharedpreferences.contains(Advice)) {
            tv_get_advice.setText(sharedpreferences.getString(Advice, ""));
        }

        iv_bmi.setVisibility(View.INVISIBLE);
        btn_show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (iv_bmi.getVisibility() == View.VISIBLE){
                    iv_bmi.setVisibility(View.INVISIBLE);
                    btn_show.setText(getResources().getString(R.string.show));
                }
                else{
                    iv_bmi.setVisibility(View.VISIBLE);
                    btn_show.setText(getResources().getString(R.string.hide));
                }
            }

        });


        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}