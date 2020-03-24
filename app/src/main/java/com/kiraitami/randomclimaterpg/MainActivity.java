package com.kiraitami.randomclimaterpg;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatViewInflater;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    //Weather Info
    class Weather{
        public String name;
        public float minTemperature;
        public float maxTemperature;
        public int minMoisture;
        public int maxMoisture;
        public int windMinSpeed;
        public int windMaxSpeed;

        public Weather(String name, float minT, float maxT, int minM, int maxM, int wMinS, int wMaxS) {
            this.name = name;
            this.minTemperature = minT;
            this.maxTemperature = maxT;
            this.minMoisture = minM;
            this.maxMoisture = maxM;
            this.windMinSpeed = wMinS;
            this.windMaxSpeed = wMaxS;
        }
    }

    List<Weather> weatherList = new ArrayList<Weather>);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherList.add(0, new Weather("Planície Tropical", 0,35,5,80,0,80));
        weatherList.add(0, new Weather("Planície Ártica", -60,20,15,90,0,80));

        List<String> spinnerArray = new ArrayList<String>();
        for(int i = 0; i<weatherList.size(); i++) {
            spinnerArray.add(weatherList.get(i).name);
        }
        //spinnerArray.add("Planície Tropical");
        //spinnerArray.add("Planície Ártica");
        //spinnerArray.add("Montanha Tropical");
        //spinnerArray.add("Montanha Ártica");
        //spinnerArray.add("Deserto Quente");
        //spinnerArray.add("Deserto Gelado");
        //spinnerArray.add("Pântano");
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
                this, R.layout.support_simple_spinner_dropdown_item, spinnerArray
        );
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner seasonItems = (Spinner) findViewById(R.id.spnSeason);
        sItems.setAdapter(adapterseason);
    }

    public void changeWeather(View view){
        Spinner spnLocation = findViewById(R.id.spnLocation);
        Spinner spnSeason = findViewById(R.id.spnSeason);
        TextView text = findViewById(R.id.txtDescription);

        float minTemperature = weatherList.get(spnLocation.getSelectedItemPosition()).minTemperature;
        float maxTemperature = weatherList.get(spnLocation.getSelectedItemPosition()).maxTemperature;
        int minMoisture = weatherList.get(spnLocation.getSelectedItemPosition()).minMoisture;
        int maxMoisture = weatherList.get(spnLocation.getSelectedItemPosition()).maxMoisture;
        int windMinSpeed = weatherList.get(spnLocation.getSelectedItemPosition()).windMinSpeed;
        int windMaxSpeed = weatherList.get(spnLocation.getSelectedItemPosition()).windMaxSpeed;

        text.setText(minTemperature + " " + maxTemperature + " " + minMoisture + " " );
    }


}
