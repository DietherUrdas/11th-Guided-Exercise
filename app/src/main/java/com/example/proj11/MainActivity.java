package com.example.proj11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, age;
    RadioButton male, female;
    CheckBox appdet, intcomp, comprog1, comprog2;
    Spinner job;
    ListView thesis;
    Button submit, clear;
    Intent intent;
    Adapter adapter;
    String[] jobList = {"Software Developer", "Software QA", "System Analyst", "Data Scientist"};
    String[] thesisTopics = {"Web Based/On-Line Application", "Network-Based Application ",
            "System/Software Development ", "Computer Aided Instruction "};
    String  subjects, topic = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        displayResult();
    }

    public void init(){
        name =  findViewById(R.id.name);
        age =  findViewById(R.id.age);
        male =  findViewById(R.id.male);
        female =  findViewById(R.id.female);
        appdet = findViewById(R.id.appdet);
        intcomp = findViewById(R.id.intcomp);
        comprog1 = findViewById(R.id.comprog1);
        comprog2 = findViewById(R.id.comprog2);
        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);

        intent = new Intent(this, MainActivity2.class);

        job = findViewById(R.id.job);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,jobList);
        job.setAdapter((SpinnerAdapter) adapter);
        thesis = findViewById(R.id.thesis);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,thesisTopics);
        thesis.setAdapter((ListAdapter) adapter);
        clear.setOnClickListener(v -> clearFields());
    }
    public String getThesis() {
        thesis.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getApplicationContext(), "You select " + thesisTopics[position],
                    Toast.LENGTH_SHORT).show();
            topic = thesisTopics[position];
        });
        return topic;
    }
    public void displayResult(){
        submit.setOnClickListener(v -> {
            if(name.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter your name",Toast.LENGTH_SHORT).show();
                return;
            }else if(age.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter your age",Toast.LENGTH_SHORT).show();
                return;
            }else if(getSubjects().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please select at least 1 for your"+
                        " favorite subject",Toast.LENGTH_SHORT).show();
                return;
            } else if(getThesis().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please select your Thesis Topic", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("id_name", name.getText().toString());
            intent.putExtra("id_age", age.getText().toString());
            intent.putExtra("id_gender", male.isChecked() ? "Male" : "Female");
            intent.putExtra("id_subjects", getSubjects());
            intent.putExtra("id_job", jobList[job.getSelectedItemPosition()]);
            intent.putExtra("id_thesis",getThesis());
            startActivity(intent);
        });
    }

    public String getSubjects(){
        subjects = "";
        if ((appdet.isChecked() || intcomp.isChecked()) ||
                (comprog1.isChecked() || comprog2.isChecked())) {
                    if(appdet.isChecked()){
                        subjects = subjects + appdet.getText().toString()+ "\n";
                    }
                    if(intcomp.isChecked()){
                        subjects = subjects + intcomp.getText().toString()+ "\n";
                    }
                    if(comprog1.isChecked()){
                        subjects = subjects + comprog1.getText().toString()+ "\n";
                    }
                    if(comprog2.isChecked()){
                        subjects = subjects + comprog2.getText().toString()+ "\n";
                    }
                }
        return subjects;
    }private void clearFields() {
        name.setText("");
        age.setText("");
        male.setChecked(false);
        female.setChecked(false);
        appdet.setChecked(false);
        intcomp.setChecked(false);
        comprog1.setChecked(false);
        comprog2.setChecked(false);
        job.setSelection(0);
        thesis.clearChoices();
        topic = "";
        subjects = "";
    }
}