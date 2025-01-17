package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    /* Taken from https://www.youtube.com/watch?v=-Yelywy0idc
    * Authored by: MISSIONTOP Tech
    * Taken by: Ercel Angeles
    * Taken on: January 17, 2025*/
    EditText editText;
    Button addButton;
    Button deleteButton;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        editText = findViewById(R.id.textBox);
        editText.setVisibility(View.INVISIBLE);

        addButton = findViewById(R.id.Add);

        confirmButton = findViewById(R.id.CONFIRM);
        confirmButton.setVisibility(View.INVISIBLE);

        // When "ADD CITY" button is clicked, a textbox and a "CONFIRM" button appears at the bottom of the screen.
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            /* [3] Function code is taken from https://www.youtube.com/watch?v=fqfv6gOZZhY
            * Authored by: Teach Programming
            * Taken by: Ercel Angeles
            * Taken on: January 17, 2025 */
            public void onClick(View v) {
                if (editText.getVisibility() == View.INVISIBLE)
                    editText.setVisibility(View.VISIBLE);
                if (confirmButton.getVisibility() == View.INVISIBLE)
                    confirmButton.setVisibility(View.VISIBLE);
            }
        });

        // When "CONFIRM" button is clicked, textbox and confirm button disappear.
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* [4] Taken from https://www.youtube.com/watch?v=7yZngVxaZDM
                * Authored by: IJ Apps
                * Taken by: Ercel Angeles
                * Taken on: January 17, 2025 */
                String userInput = editText.getText().toString();   // [4]
                dataList.add(userInput);    // [4]
                cityList.setAdapter(cityAdapter);   // [4]
                editText.setVisibility(View.INVISIBLE);
                confirmButton.setVisibility(View.INVISIBLE);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }





}