package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    String userInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);    // [5]
        /* [6] Code taken from https://stackoverflow.com/questions/5853719/highlighting-only-the-selected-item-in-the-listview-in-android
        * Authored by: rekaszeru
        * Edited by: Donald Duck
        * Taken by: Ercel Angeles
        * Taken on: January 17, 2025 */
        cityList.setSelector(android.R.color.darker_gray);

        String []cities = {"Edmonton", "Vancouver"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        editText = findViewById(R.id.textBox);
        editText.setVisibility(View.INVISIBLE);

        addButton = findViewById(R.id.Add);
        deleteButton = findViewById(R.id.Delete);
        /* [8] Code taken from https://stackoverflow.com/questions/2838757/how-to-set-opacity-alpha-for-view-in-android
        * Authored by: Jake Wilson
        * Taken by: Ercel Angeles
        * Taken on: January 17, 2025 */
        deleteButton.getBackground().setAlpha(150);

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

                /* [7] Code taken from https://stackoverflow.com/questions/48253761/how-do-i-clear-listview-selection
                * Authored by: Aleksandr Medvedev
                * Taken by: Ercel Angeles
                * Taken on: January 17, 2025*/
                cityList.clearChoices();    // [7]
                cityList.setAdapter(cityAdapter);   // [7]
                userInput = "";
                editText.setText("");
                deleteButton.getBackground().setAlpha(150);
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
                userInput = editText.getText().toString();   // [4]
                dataList.add(userInput);    // [4]
                cityList.setAdapter(cityAdapter);   // [4]
                userInput = "";
                editText.setText("");
                editText.setVisibility(View.INVISIBLE);
                confirmButton.setVisibility(View.INVISIBLE);
            }
        });

        /* [5] Function code is taken from https://www.youtube.com/watch?v=5F5EJ1LUoZY
        * Authored by: Coding with Dev
        * Taken by: Ercel Angeles
        * Taken on: January 17, 2025*/
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                deleteButton.getBackground().setAlpha(255);
                editText.setVisibility(View.INVISIBLE);
                confirmButton.setVisibility(View.INVISIBLE);
                userInput = (String) adapterView.getItemAtPosition(i);
            }
        });

        // Deletes a selected item.
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.remove(userInput);
                cityList.setAdapter(cityAdapter);
                userInput = "";
                deleteButton.getBackground().setAlpha(150);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }





}