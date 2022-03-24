package com.example.assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    BMIFragment bmiFragment = new BMIFragment();
    HomeFragment homeFragment = new HomeFragment();
    ZodiacFragment zodiacFragment = new ZodiacFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){

        /*Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        bmiFragment.setArguments(bundle);
        zodiacFragment.setArguments(bundle);*/

        switch (item.getItemId()){
            case R.id.menu_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                return true;
            case R.id.menu_bmi:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, bmiFragment).commit();
                return true;
            case R.id.menu_zodiac:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, zodiacFragment).commit();
                return true;
        }


        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.english:
                Toast.makeText(this,"English",Toast.LENGTH_LONG).show();
                setLocale("");
                Intent intent = getIntent();
                finish();
                startActivity(intent);
               // setLocale(MainActivity.this,"");
               // finish();
                //startActivity(getIntent());
                break;
            case R.id.chinese:
                Toast.makeText(this,"中文",Toast.LENGTH_LONG).show();
                setLocale("chi");
                intent = getIntent();
                finish();
                startActivity(intent);
                //setLocale(MainActivity.this,"chi");
                //finish();
                //startActivity(getIntent());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setLocale( String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

    }

/*
    private void setLocale(Activity activity, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());

    }
*/


}