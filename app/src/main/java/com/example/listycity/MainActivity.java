package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> cityData;
    ArrayAdapter<String> cityAdapter;
    ListView cityList;


    Button addCityButton;
    Button deleteCityButton;
    Button confirmButton;
    EditText cityName;

    int selectedCityPosition = -1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCityButton = findViewById(R.id.add_city);
        deleteCityButton = findViewById(R.id.delete_city);
        confirmButton = findViewById(R.id.confirm_button);
        cityName = findViewById(R.id.city_name);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna"};
        cityData = new ArrayList<>();
        cityData.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, cityData);
        cityList.setAdapter(cityAdapter);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCityPosition = position;
                cityList.setItemChecked(position, true);
            }
        });

        addCityButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                cityName.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                cityName.setText("");

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String newCity = cityName.getText().toString().trim();
                if (!newCity.isEmpty()) {
                    cityData.add(newCity);
                    cityAdapter.notifyDataSetChanged();

                    cityName.setVisibility(View.INVISIBLE);
                    confirmButton.setVisibility(View.INVISIBLE);
                }
            }
        });
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCityPosition >= 0 && selectedCityPosition < cityData.size()) {
                    // Remove the city
                    String removedCity = cityData.remove(selectedCityPosition);
                    cityAdapter.notifyDataSetChanged();

                    // Clear any checked states in the list
                    cityList.clearChoices();

                    // Reset the selected position
                    selectedCityPosition = -1;


                } else {

                }
            }
        });
    }
}