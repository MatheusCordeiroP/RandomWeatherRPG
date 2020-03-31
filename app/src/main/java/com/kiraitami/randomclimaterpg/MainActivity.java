package com.kiraitami.randomclimaterpg;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatViewInflater;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity{

    //Weather Info
    class Weather{
        public String name;
        public float minTemperature;
        public float maxTemperature;
        public float tempModifier;
        public float springPrecipitation;
        public float summerPrecipitation;
        public float fallPrecipitation;
        public float winterPrecipitation;
        public int windMaxSpeed;


        public Weather(String name, float minT, float maxT, float tempM, float sprP, float sumP, float falP, float winP, int wMaxS) {
            this.name = name;
            this.minTemperature = minT;
            this.maxTemperature = maxT;
            this.tempModifier = tempM;
            this.springPrecipitation = sprP;
            this.summerPrecipitation = sumP;
            this.fallPrecipitation = falP;
            this.winterPrecipitation = winP;
            this.windMaxSpeed = wMaxS;
        }
    }

    List<Weather> weatherList = new ArrayList<Weather>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weatherList.add(0, new Weather("Planície Seca Quente",
                12,40,16,40,8,0, 14, 24));
        weatherList.add(0, new Weather("Planície Umida Quente",
                00,38,10,137,288,82, 36, 44));
        weatherList.add(0, new Weather("Planície Seca Fria",
                -12,23,26,41,70,72, 43,  14));
        weatherList.add(0, new Weather("Planície Umida Fria",
                -8,23,20,90,96,120, 112, 32));

        weatherList.add(0, new Weather("Montanha Quente",
                4,37,14,1,30,5, 10,  44));
        weatherList.add(0, new Weather("Montanha fria",
                -20,27,36,74,98,91, 69,  44));

        weatherList.add(0, new Weather("Floresta Seca Quente",
                18,35,3,40,112,20, 0,  70));
        weatherList.add(0, new Weather("Floresta Umida Quente",
                12,39,6,169,287,319, 65,  24));
        weatherList.add(0, new Weather("Floresta Seca Fria",
                -9,28,25,15,30,15, 30,  20));
        weatherList.add(0, new Weather("Floresta Umida Fria",
                -20,25,22,90,170,80, 80,  44));

        weatherList.add(0, new Weather("Mar Equinocial",
                20,29,2,490,290,390, 12,  34));
        weatherList.add(0, new Weather("Mar Tropical",
                19,30,4,140,320,270, 120,  50));
        weatherList.add(0, new Weather("Mar Ártico",
                0,14,6,60,110,90, 60, 60));

        weatherList.add(0, new Weather("Pântano Quente",
                32,5,16,130,180,80, 100,  44));
        weatherList.add(0, new Weather("Pântano Frio",
                -15,12,16,130,180,80, 100,  44));

        weatherList.add(0, new Weather("Deserto Quente",
                13,38,8,50,180,50, 0,  80));
        weatherList.add(0, new Weather("Deserto Gelado",
                -50,20,40,10,60,30, 20,  80));

        List<String> spinnerArray = new ArrayList<String>();
        for(int i = 0; i<weatherList.size(); i++) {
            spinnerArray.add(weatherList.get(i).name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, spinnerArray
        );

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spnLocation);
        sItems.setAdapter(adapter);

        List<String> spinnerSeason = new ArrayList<String>();
        spinnerSeason.add("Primavera");
        spinnerSeason.add("Verão");
        spinnerSeason.add("Outono");
        spinnerSeason.add("Inverno");
        ArrayAdapter<String> adapterseason = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, spinnerSeason
        );
        adapterseason.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner seasonItems = (Spinner) findViewById(R.id.spnSeason);
        seasonItems.setAdapter(adapterseason);
    }

    public void changeWeather(View view) {

        //Variables
        Spinner spnLocation = findViewById(R.id.spnLocation);
        Spinner spnSeason = findViewById(R.id.spnSeason);
        Switch swtNight = findViewById(R.id.swtNight);
        TextView text = findViewById(R.id.txtDescription);

        //0 - Spring / 1 - Summer / 2 - Fall / 3 - Winter
        int seasonId = spnSeason.getSelectedItemPosition();

        //Location Variables
        float minTemperature = weatherList.get(spnLocation.getSelectedItemPosition()).minTemperature;
        float maxTemperature = weatherList.get(spnLocation.getSelectedItemPosition()).maxTemperature;
        float tempModifier = weatherList.get(spnLocation.getSelectedItemPosition()).tempModifier;
        float springPrecipitation = weatherList.get(spnLocation.getSelectedItemPosition()).springPrecipitation;
        float summerPrecipitation = weatherList.get(spnLocation.getSelectedItemPosition()).summerPrecipitation;
        float fallPrecipitation = weatherList.get(spnLocation.getSelectedItemPosition()).fallPrecipitation;
        float winterPrecipitation = weatherList.get(spnLocation.getSelectedItemPosition()).winterPrecipitation;
        int windMaxSpeed = weatherList.get(spnLocation.getSelectedItemPosition()).windMaxSpeed;

        //To avoid errors
        if (minTemperature+tempModifier > maxTemperature) minTemperature = maxTemperature - (tempModifier+2);

        if(windMaxSpeed < 0) windMaxSpeed = 1;

        //Setting Variables
        float precipitation = (new Random().nextFloat())*400;

            //Verify if it's raining and sets max and min temperature for each season
        switch (seasonId) {
            case 0:
                //text.setText("é Primavera");
                maxTemperature-=tempModifier/4;
                minTemperature+=tempModifier/2;

                if(precipitation < springPrecipitation - 100) {
                    //Chuva Leve
                    precipitation = 2;
                }
                else if(precipitation < springPrecipitation){
                    //Chuva Forte
                    precipitation = 1;
                }
                else{
                    //Sem chuva
                    precipitation = 0;
                }
                break;
            case 1:
                //text.setText("é Verão");
                minTemperature+=tempModifier;

                if(precipitation < summerPrecipitation - 100) {
                    //Chuva Leve
                    precipitation = 2;
                }
                else if(precipitation < summerPrecipitation){
                    //Chuva Forte
                    precipitation = 1;
                }
                else{
                    //Sem chuva
                    precipitation = 0;
                }
                break;
            case 2:
                //text.setText("é Outono");
                maxTemperature-=tempModifier/2;
                minTemperature+=tempModifier/4;

                if(precipitation < fallPrecipitation - 100) {
                    //Chuva Leve
                    precipitation = 2;
                }
                else if(precipitation < fallPrecipitation){
                    //Chuva Forte
                    precipitation = 1;
                }
                else{
                    //Sem chuva
                    precipitation = 0;
                }
                break;
            case 3:
                //text.setText("é Inverno");
                maxTemperature-=tempModifier;

                if(precipitation < winterPrecipitation - 100) {
                    //Chuva Leve
                    precipitation = 2;
                }
                else if(precipitation < winterPrecipitation){
                    //Chuva Forte
                    precipitation = 1;
                }
                else{
                    //Sem chuva
                    precipitation = 0;
                }
                break;
        }

            //If it's night, it will be colder.
        if(swtNight.isChecked()) {
            maxTemperature -= (maxTemperature - minTemperature)/4;
        }
        else {
            minTemperature -= (maxTemperature - minTemperature)/4;
        }

        float temperature = 0;
        int windSpeed = 0;

        for(int i = 0; i<5 ; i++){
            temperature += ((new Random().nextFloat())*(maxTemperature-minTemperature))+minTemperature;
            windSpeed += (new Random().nextInt(windMaxSpeed));
        }
        temperature = temperature/5;
        windSpeed = windSpeed/5;

        int modNight;       // 0-Night      1-Day
        int modRain;        // 0-No Rain    1-Light Rain    2-Heavy Rain
        int modWinds;       // 0-No Winds   1-Light Wind    2-Medium Wind  3-Heavy Rain
        int modTemperature; // 0- -40-      1- -20~0        2-0~10         3-11~18     4- 19~28     5- 29~34    6- 35+

        if(swtNight.isChecked()) {
            modNight = 0;
        }
        else {
            modNight = 1;
        }

        if(precipitation == 0){
            modRain = 0;
        }else if(precipitation == 1){
            modRain = 1;
        } else {
            modRain = 2;
        }

        if(windSpeed < 14){
            modWinds = 0;
        } else if(windSpeed < 34){
            modWinds = 1;
        } else if(windSpeed < 44){
            modWinds = 2;
        } else{
            modWinds = 3;
        }

        if(temperature < -20){
            modTemperature = 0;
        } else if(temperature <0){
            modTemperature = 1;
        } else if(temperature <10){
            modTemperature = 2;
        } else if(temperature <20){
            modTemperature = 3;
        } else if(temperature <28){
            modTemperature = 4;
        } else if(temperature <34){
            modTemperature = 5;
        } else {
            modTemperature = 6;
        }

        int stringid = modNight*84+modRain*28+modWinds*7+modTemperature+1;
        String stringname = "weather_";
        String stringIDString = "";

        if(stringid < 100) {
            stringIDString+="0";
            if(stringid < 10){
                stringIDString+="0";
            }
        }
        stringIDString += Integer.toString(stringid);
        stringname = stringname+stringIDString;

        text.setText("error: unexpected condition.");

        String weather = getResources().getString(getStringIdentifier(stringname));
        DecimalFormat df = new DecimalFormat("0.0");

        text.setText(weather+"\n\nTemperatura: "+df.format(temperature)+"°C\nVentos: "+windSpeed+" Km/h");

    }


    private int getStringIdentifier(String aString) {
        String packageName = this.getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return resId;
    }

}


