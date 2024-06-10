package com.rifat.sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edName,edMobile,edGmail,edPassword,edId;
    Button buttonSubmit,buttonShow,buttonUpdate;
    DataBaseHelper dbHelper;


    String setSpinner="item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edId=findViewById(R.id.edId);
        edName=findViewById(R.id.edName);
        edMobile=findViewById(R.id.edMobile);
        edGmail=findViewById(R.id.edGmail);
        edPassword=findViewById(R.id.edPassword);
        buttonSubmit=findViewById(R.id.buttonSubmit);
        buttonShow=findViewById(R.id.buttonShow);
        buttonUpdate=findViewById(R.id.buttonUpdate);

        dbHelper=new DataBaseHelper(MainActivity.this);

   /* The hole process how to set Spinner */

        Spinner spinner = findViewById(R.id.spinner);

        ArrayList<String>arrayList=new ArrayList<>();
        arrayList.add("No Semester Selected");
        arrayList.add("1st Semester");
        arrayList.add("2nd Semester");
        arrayList.add("3rd Semester");
        arrayList.add("4th Semester");
        arrayList.add("5th Semester");
        arrayList.add("6th Semester");
        arrayList.add("7th Semester");
        arrayList.add("8th Semester");
        arrayList.add("9th Semester");
        arrayList.add("10th Semester");
        arrayList.add("11th Semester");
        arrayList.add("12th Semester");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, R.layout.spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //    String item  =parent.getItemAtPosition(position).toString();
                setSpinner=parent.getItemAtPosition(position).toString();

                Toast.makeText(MainActivity.this,"Selected item "+setSpinner  ,Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /* Ends of the Introduction of a spinner  */


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For submitting all data
                String item;
                dbHelper.SubmitData(edName.getText().toString(),edGmail.getText().toString(),edPassword.getText().toString(), edMobile.getText().toString(),setSpinner);
                Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
            }
        });



        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For showing all data
                startActivity(new Intent(MainActivity.this,ShowFinalData.class));
            }
        });


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updatedData(edId.getText().toString(), edName.getText().toString(),edGmail.getText().toString()  ,edPassword.getText().toString() ,edMobile.getText().toString(), setSpinner);
                Toast.makeText(MainActivity.this, "Studdent Profile Updated..", Toast.LENGTH_SHORT).show();


            }
        });


    }////on create
}