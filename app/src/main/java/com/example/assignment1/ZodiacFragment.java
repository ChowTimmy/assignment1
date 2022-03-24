package com.example.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import com.example.assignment1.databinding.FragmentZodiacBinding;

public class ZodiacFragment extends Fragment {

    private FragmentZodiacBinding binding;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Gender = "genderKey";
    public static final String Day = "dayKey";
    public static final String Sign = "signKey";
    public static final String Gender_p = "positionKey";
    String X;



    TextView tv_get_zodiac_date,tv_get_zodiac_gender,tv_get_zodiac_name,tv_get_zodiac_zodiac,tv_get_what,tv_get_personality,tv_get_match,tv_get_conflicts;
    ImageView iv_zodiac;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        zodiac_icon(X);
        binding = FragmentZodiacBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv_get_zodiac_gender = root.findViewById(R.id.tv_get_zodiac_gender);
        tv_get_zodiac_date = root.findViewById(R.id.tv_get_zodiac_date);
        tv_get_zodiac_name = root.findViewById(R.id.tv_get_zodiac_name);
        tv_get_zodiac_zodiac = root.findViewById(R.id.tv_get_zodiac_zodiac);
        iv_zodiac = root.findViewById(R.id.iv_zodiac);
        tv_get_what = root.findViewById(R.id.tv_get_what);
        tv_get_personality = root.findViewById(R.id.tv_get_personality);
        tv_get_match = root.findViewById(R.id.tv_get_match);
        tv_get_conflicts = root.findViewById(R.id.tv_get_conflicts);
        iv_zodiac.setVisibility(View.INVISIBLE);





        sharedPreferences = getContext().getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedPreferences.contains(Day)) {
            tv_get_zodiac_date.setText(sharedPreferences.getString(Day, ""));
        }
        if (sharedPreferences.contains(Name)) {
            tv_get_zodiac_name.setText(sharedPreferences.getString(Name, ""));
        }
        String gender;
        String[] gender_a = getResources().getStringArray(R.array.Gender);
        if (sharedPreferences.getInt(Gender_p, 0) != 0) {
            gender = gender_a[sharedPreferences.getInt(Gender_p, 0)];
            tv_get_zodiac_gender.setText(gender);
        }
        if (sharedPreferences.contains(Sign)) {
            tv_get_zodiac_zodiac.setText(sharedPreferences.getString(Sign, ""));
        }
        X = tv_get_zodiac_zodiac.getText().toString();

        zodiac_icon(X);










        return root;
    }

    public void zodiac_icon(String icon){
        if(icon == getResources().getString(R.string.pisces)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.pisces);
            tv_get_what.setText(getResources().getString(R.string.Pisces));
            tv_get_personality.setText(getResources().getString(R.string.p_Pisces));
            tv_get_match.setText(getResources().getString(R.string.scorpius) +" "+ getResources().getString(R.string.cancer)+" "+getResources().getString(R.string.capricornus));
            tv_get_conflicts.setText(getResources().getString(R.string.gemini) +" "+ getResources().getString(R.string.sagittarius)+" "+getResources().getString(R.string.leo));


        }

        else if(icon == getResources().getString(R.string.capricornus)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.capricron);
            tv_get_what.setText(getResources().getString(R.string.Capricornus));
            tv_get_personality.setText(getResources().getString(R.string.p_Capricornus));
            tv_get_match.setText(getResources().getString(R.string.virgo) +" "+ getResources().getString(R.string.scorpius));
            tv_get_conflicts.setText(getResources().getString(R.string.leo) +" "+ getResources().getString(R.string.gemini)+" "+getResources().getString(R.string.sagittarius));

        }
        else if(icon == getResources().getString(R.string.aquarius)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.aquarius);
            tv_get_what.setText(getResources().getString(R.string.Aquarius));
            tv_get_personality.setText(getResources().getString(R.string.p_Aquarius));
            tv_get_match.setText(getResources().getString(R.string.gemini) +" "+ getResources().getString(R.string.libra));
            tv_get_conflicts.setText(getResources().getString(R.string.cancer) +" "+ getResources().getString(R.string.taurus));


        }
        else if(icon == getResources().getString(R.string.aries)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.aries);
            tv_get_what.setText(getResources().getString(R.string.Aries));
            tv_get_personality.setText(getResources().getString(R.string.p_Aries));
            tv_get_match.setText(getResources().getString(R.string.sagittarius) +" "+ getResources().getString(R.string.gemini)+" "+getResources().getString(R.string.libra));
            tv_get_conflicts.setText(getResources().getString(R.string.scorpius) +" "+ getResources().getString(R.string.cancer)+" "+getResources().getString(R.string.virgo));


        }
        else if(icon == getResources().getString(R.string.taurus)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.taurus);
            tv_get_what.setText(getResources().getString(R.string.Taurus));
            tv_get_personality.setText(getResources().getString(R.string.p_Taurus));
            tv_get_match.setText(getResources().getString(R.string.capricornus) +" "+ getResources().getString(R.string.virgo)+" "+getResources().getString(R.string.cancer));
            tv_get_conflicts.setText(getResources().getString(R.string.leo) +" "+ getResources().getString(R.string.aquarius)+" "+getResources().getString(R.string.sagittarius));


        }
        else if(icon == getResources().getString(R.string.gemini)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.gemini);
            tv_get_what.setText(getResources().getString(R.string.Gemini));
            tv_get_personality.setText(getResources().getString(R.string.p_Gemini));
            tv_get_match.setText(getResources().getString(R.string.libra) +" "+ getResources().getString(R.string.sagittarius)+" "+getResources().getString(R.string.aquarius));
            tv_get_conflicts.setText(getResources().getString(R.string.scorpius) +" "+ getResources().getString(R.string.capricornus));


        }
        else if(icon == getResources().getString(R.string.cancer)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.cancer);
            tv_get_what.setText(getResources().getString(R.string.Cancer));
            tv_get_personality.setText(getResources().getString(R.string.p_Cancer));
            tv_get_match.setText(getResources().getString(R.string.scorpius) +" "+ getResources().getString(R.string.pisces)+" "+getResources().getString(R.string.virgo));
            tv_get_conflicts.setText(getResources().getString(R.string.gemini) +" "+ getResources().getString(R.string.sagittarius)+" "+getResources().getString(R.string.leo));

        }
        else if(icon == getResources().getString(R.string.leo)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.leo);
            tv_get_what.setText(getResources().getString(R.string.Leo));
            tv_get_personality.setText(getResources().getString(R.string.p_Leo));
            tv_get_match.setText(getResources().getString(R.string.sagittarius) +" "+ getResources().getString(R.string.aquarius)+" "+getResources().getString(R.string.gemini));
            tv_get_conflicts.setText(getResources().getString(R.string.capricornus) +" "+ getResources().getString(R.string.taurus)+" "+getResources().getString(R.string.virgo));

        }
        else if(icon == getResources().getString(R.string.virgo)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.virgo);
            tv_get_what.setText(getResources().getString(R.string.Virgo));
            tv_get_personality.setText(getResources().getString(R.string.p_Virgo));
            tv_get_match.setText(getResources().getString(R.string.capricornus) +" "+ getResources().getString(R.string.taurus)+" "+getResources().getString(R.string.scorpius));
            tv_get_conflicts.setText(getResources().getString(R.string.sagittarius) +" "+ getResources().getString(R.string.gemini)+" "+getResources().getString(R.string.aquarius));

        }
        else if(icon == getResources().getString(R.string.libra)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.libra);
            tv_get_what.setText(getResources().getString(R.string.Libra));
            tv_get_personality.setText(getResources().getString(R.string.p_Libra));
            tv_get_match.setText(getResources().getString(R.string.aries) +" "+ getResources().getString(R.string.aquarius));
            tv_get_conflicts.setText(getResources().getString(R.string.virgo) +" "+ getResources().getString(R.string.capricornus));


        }
        else if(icon == getResources().getString(R.string.scorpius)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.scorpio);
            tv_get_what.setText(getResources().getString(R.string.Scorpius));
            tv_get_personality.setText(getResources().getString(R.string.p_Scorpius));
            tv_get_match.setText(getResources().getString(R.string.pisces) +" "+ getResources().getString(R.string.cancer)+" "+getResources().getString(R.string.virgo));
            tv_get_conflicts.setText(getResources().getString(R.string.aquarius) +" "+ getResources().getString(R.string.leo)+" "+getResources().getString(R.string.aries));


        }
        else if(icon == getResources().getString(R.string.sagittarius)){
            iv_zodiac.setVisibility(View.VISIBLE);
            iv_zodiac.setImageResource(R.drawable.sagittarius);
            tv_get_what.setText(getResources().getString(R.string.Sagittarius));
            tv_get_personality.setText(getResources().getString(R.string.p_Sagittarius));
            tv_get_match.setText(getResources().getString(R.string.gemini) +" "+ getResources().getString(R.string.aries));
            tv_get_conflicts.setText(getResources().getString(R.string.capricornus) +" "+ getResources().getString(R.string.taurus));

        }




    }


}