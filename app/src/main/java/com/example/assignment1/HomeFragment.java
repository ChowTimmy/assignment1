package com.example.assignment1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment1.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private FragmentHomeBinding binding;

    Button btn_save, btn_clear,btn_delete;
    EditText et_name,et_height,et_weight;
    String SEX,format, str_bmi,date,sign;
    int DAY,MONTH;
    Spinner spin ;
    Calendar c;
    boolean refresh = true;

    FloatingActionButton fabtn;
    TextView tv_date;

    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Height = "heightKey";
    public static final String Weight = "weightKey";
    public static final String Gender = "genderKey";
    public static final String Date = "dateKey";
    public static final String BMI = "bmiKey" ;
    public static final String STATUS = "statusKey";
    public static final String Advice = "adviceKey";
    public static final String Day = "dayKey";
    public static final String Sign = "signKey";
    public static final String Hibi = "hibiKey";
    public static final String Ketsu = "ketsuKey";
    public static final String Gender_p = "positionKey";

    String [] gender;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btn_save = root.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_clear = root.findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
        btn_delete = root.findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);


        et_name = root.findViewById(R.id.et_name);
        et_height = root.findViewById(R.id.et_height);
        et_weight = root.findViewById(R.id.et_weight);

        Resources res = getResources();
        gender = res.getStringArray(R.array.Gender);

        spin = root.findViewById(R.id.gender_spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);



        fabtn = root.findViewById(R.id.fabtn);

        tv_date = root.findViewById(R.id.tv_date);
        tv_date.setText(format);

        //DATE PICKER --------------------------------------------------------------------------------------------------------
        fabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dateDialog = new DatePickerDialog(view.getContext(), datePickerListener, mYear, mMonth, mDay);
                dateDialog.getDatePicker().setMaxDate(new Date().getTime());
                dateDialog.show();
            }

        });



        sharedPreferences = getContext().getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        if (refresh == true) {
            if (et_name.getText().toString().isEmpty()
                    || et_weight.getText().toString().isEmpty()
                    || et_height.getText().toString().isEmpty()
                    || spin.getSelectedItemId() == 0
                    || tv_date.getText().toString().isEmpty()) {

                if (sharedPreferences.contains(Name)) {
                    et_name.setText(sharedPreferences.getString(Name, ""));
                }
                if (sharedPreferences.contains(Weight)) {
                    et_weight.setText(sharedPreferences.getString(Weight, ""));
                }
                if (sharedPreferences.contains(Height)) {
                    et_height.setText(sharedPreferences.getString(Height, ""));
                }
                if (sharedPreferences.contains(Date)) {
                    tv_date.setText(sharedPreferences.getString(Date, ""));
                }
                if (sharedPreferences.contains(Day)) {
                    date = sharedPreferences.getString(Day, "");
                }
                spin.setSelection(sharedPreferences.getInt(Gender_p, 0));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(sharedPreferences.getInt(Ketsu, 0) != 0 || sharedPreferences.getInt(Hibi, 0) != 0 )
                zodiac_cal(sharedPreferences.getInt(Ketsu, -1), sharedPreferences.getInt(Hibi, -1));

                if (et_weight.getText().toString().isEmpty() || et_height.getText().toString().isEmpty()) {
                } else {
                    calBmi(Double.parseDouble(et_height.getText().toString()), Double.parseDouble(et_weight.getText().toString()));

                    editor.putString(Name, et_name.getText().toString());
                    editor.putString(Height, et_height.getText().toString());
                    editor.putString(Weight, et_weight.getText().toString());
                    editor.putString(Gender, SEX);
                    if (spin.getSelectedItemId() == 0) {
                        editor.putString(Gender, "");
                    }
                    editor.putString(Date, tv_date.getText().toString());
                    editor.putString(BMI, str_bmi);
                    if (Double.parseDouble(str_bmi) < 18.5) {
                        editor.putString(STATUS, getResources().getString(R.string.UnderWeight));
                    } else if (Double.parseDouble(str_bmi) < 25) {
                        editor.putString(STATUS, getResources().getString(R.string.HealthyWeight));
                    } else if (Double.parseDouble(str_bmi) < 30) {
                        editor.putString(STATUS, getResources().getString(R.string.OverWeight));
                    } else if (Double.parseDouble(str_bmi) < 35) {
                        editor.putString(STATUS, getResources().getString(R.string.Obese));
                    } else if (Double.parseDouble(str_bmi) < 40) {
                        editor.putString(STATUS, getResources().getString(R.string.S_Obese));
                    } else if (Double.parseDouble(str_bmi) >= 40) {
                        editor.putString(STATUS, getResources().getString(R.string.M_Obese));
                    }

                    if (Double.parseDouble(str_bmi) < 18.5) {
                        editor.putString(Advice, getResources().getString(R.string.underweight));
                    } else if (Double.parseDouble(str_bmi) < 25) {
                        editor.putString(Advice, getResources().getString(R.string.healthy));
                    } else if (Double.parseDouble(str_bmi) < 30) {
                        editor.putString(Advice, getResources().getString(R.string.overweight));
                    } else {
                        editor.putString(Advice, getResources().getString(R.string.obese));
                    }
                }
                editor.putString(Day, date);
                editor.putString(Sign, sign);


                editor.commit();

            }
        }

        return root;
    }


    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            format = new SimpleDateFormat("dd MMM YYYY").format(c.getTime());
            tv_date.setText(format);

            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            date = new SimpleDateFormat("dd MMM").format(c.getTime());

            SharedPreferences.Editor editor = sharedPreferences.edit();
            c.set(Calendar.DAY_OF_MONTH, day);
            DAY = day;
            editor.putInt(Hibi, DAY);
            c.set(Calendar.MONTH, month);
            MONTH = month;
            editor.putInt(Ketsu, MONTH);
            editor.commit();

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0,View arg1,int position, long id){
        SEX = arg0.getSelectedItem().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Gender_p, position );
        editor.commit();
    }

    @Override
    public void onClick(View v){

        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(v.getWindowToken(),0);

        switch (v.getId()){
            case R.id.btn_clear:
                et_weight.setText("");
                et_height.setText("");
                et_name.setText("");
                tv_date.setText("");
                spin.setSelection(0);
                format  = "";
                break;

            case R.id.btn_save:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if          (et_name.getText().toString().isEmpty()
                        || et_weight.getText().toString().isEmpty()
                        || et_height.getText().toString().isEmpty()
                        || spin.getSelectedItemId() == 0
                        || tv_date.getText().toString().isEmpty()){

                    Toast.makeText(getContext().getApplicationContext(),getResources().getString(R.string.error) , Toast.LENGTH_LONG).show();
                }
                else {

                    zodiac_cal( MONTH, DAY);
                    calBmi(Double.parseDouble(et_height.getText().toString()),Double.parseDouble(et_weight.getText().toString()));
                    editor.putString(Name, et_name.getText().toString());
                    editor.putString(Height, et_height.getText().toString());
                    editor.putString(Weight, et_weight.getText().toString());
                    editor.putString(Gender, SEX);
                    if (spin.getSelectedItemId() == 0) {
                        editor.putString(Gender, "");
                    }
                    editor.putString(Date, tv_date.getText().toString());
                    editor.putString(BMI, str_bmi);
                    if ( Double.parseDouble(str_bmi) < 18.5 ) {
                        editor.putString(STATUS, getResources().getString(R.string.UnderWeight) );
                    }
                    else if (Double.parseDouble(str_bmi) < 25) {
                        editor.putString(STATUS, getResources().getString(R.string.HealthyWeight) );
                    }
                    else if (Double.parseDouble(str_bmi) < 30) {
                        editor.putString(STATUS, getResources().getString(R.string.OverWeight) );
                    }
                    else if (Double.parseDouble(str_bmi) < 35) {
                        editor.putString(STATUS, getResources().getString(R.string.Obese) );
                    }
                    else if (Double.parseDouble(str_bmi) < 40) {
                        editor.putString(STATUS, getResources().getString(R.string.S_Obese) );
                    }
                    else if (Double.parseDouble(str_bmi) >= 40) {
                        editor.putString(STATUS, getResources().getString(R.string.M_Obese) );
                    }

                    if ( Double.parseDouble(str_bmi) < 18.5){
                        editor.putString(Advice,getResources().getString(R.string.underweight));
                    }
                    else if (Double.parseDouble(str_bmi) < 25){
                        editor.putString(Advice,getResources().getString(R.string.healthy));
                    }
                    else if (Double.parseDouble(str_bmi) < 30){
                        editor.putString(Advice,getResources().getString(R.string.overweight));
                    }
                    else
                    {
                        editor.putString(Advice,getResources().getString(R.string.obese));
                    }

                    editor.putString(Day, date);
                    editor.putString(Sign,sign);


                    editor.commit();
                    Toast.makeText(getContext().getApplicationContext(), getResources().getString(R.string.saved), Toast.LENGTH_LONG).show();
                    refresh = false;
                }
                break;


            case R.id.btn_delete:
                et_weight.setText("");
                et_name.setText("");
                et_height.setText("");
                spin.setSelection(0);
                tv_date.setText("");
                format  = "";




                editor = sharedPreferences.edit();

                editor.putString(Name, "");
                editor.putString(Height, "");
                editor.putString(Weight, "");
                editor.putString(Date, "");
                editor.putString(Gender, "");
                editor.putString(BMI,"");
                editor.putString(STATUS, "" );
                editor.putString(Advice, "" );
                editor.putString(Day, "");
                editor.putString(Sign, "");
                editor.putInt(Hibi,0);
                editor.putInt(Ketsu,0);

                editor.commit();
                Toast.makeText(getContext().getApplicationContext(),getResources().getString(R.string.clean_data) ,Toast.LENGTH_LONG).show();
                break;
        }



    }

    public void calBmi(double h ,double w) {
        DecimalFormat df = new DecimalFormat("#.0");

        Double bmi = w / ((h / 100) * (h / 100));
        str_bmi = df.format(bmi).toString();
    }

    public void zodiac_cal( int month, int day){
        sign ="";
        if (month == 0 ) {
            if (day < 20)
                sign = getResources().getString(R.string.capricornus);
            else
                sign = getResources().getString(R.string.aquarius);
        }
        else if (month == 1) {
            if (day < 19)
                sign = getResources().getString(R.string.aquarius);
            else
                sign = getResources().getString(R.string.pisces);
        }
        else if(month == 2) {
            if (day < 21) {
                sign = getResources().getString(R.string.pisces);

            }
            else
                sign = getResources().getString(R.string.aries);
        }
        else if (month == 3) {
            if (day < 20)
                sign = getResources().getString(R.string.aries);
            else
                sign = getResources().getString(R.string.taurus);
        }
        else if (month == 4) {
            if (day < 21)
                sign = getResources().getString(R.string.taurus);
            else
                sign = getResources().getString(R.string.gemini);
        }
        else if( month == 5) {
            if (day < 21)
                sign = getResources().getString(R.string.gemini);
            else
                sign = getResources().getString(R.string.cancer);
        }
        else if (month == 6) {
            if (day < 23)
                sign = getResources().getString(R.string.cancer);
            else
                sign = getResources().getString(R.string.leo);
        }
        else if( month == 7 ) {
            if (day < 23)
                sign = getResources().getString(R.string.leo);
            else
                sign = getResources().getString(R.string.virgo);
        }
        else if (month == 8) {
            if (day < 23)
                sign = getResources().getString(R.string.virgo);
            else
                sign = getResources().getString(R.string.libra);
        }
        else if (month == 9) {
            if (day < 23)
                sign = getResources().getString(R.string.libra);
            else
                sign = getResources().getString(R.string.scorpius);
        }
        else if (month == 10) {
            if (day < 22)
                sign = getResources().getString(R.string.scorpius);
            else
                sign = getResources().getString(R.string.sagittarius);
        }
        else if (month == 11) {
            if (day < 22)
                sign = getResources().getString(R.string.sagittarius);
            else
                sign = getResources().getString(R.string.capricornus);
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){

    }
}


