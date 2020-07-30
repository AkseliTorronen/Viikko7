package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    TextView text;
    EditText input;
    EditText file;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        input = (EditText) findViewById(R.id.input);
        String inputToFile = input.getEditableText().toString();
        file = (EditText) findViewById(R.id.fileName);
        String nameOfFile = file.getEditableText().toString();
        context = MainActivity.this;
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello World!");
                text.setText("Hello World!");
            }
        });


        input.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text.setText(input.getText());
            }

            public void afterTextChanged(Editable s) {

            }
        });
    }
        public void loadFile (View v){
            String nameOfFile = file.getEditableText().toString();
            try {
                InputStream is = context.openFileInput(nameOfFile);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String s = "";
                while ((s = br.readLine()) != null) {
                    text.setText(s + '\n');
                }
                is.close();
            } catch (IOException e) {
                Log.e("IOException", "Virhe syöte");
            }
        }
        public void saveFile (View v){
            String inputToFile = input.getEditableText().toString();
            String nameOfFile = file.getEditableText().toString();
            try {
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(nameOfFile, Context.MODE_PRIVATE));
                osw.write(inputToFile + '\n');
                osw.close();
            } catch (IOException e) {
                Log.e("IOException", "Virhe syöte");
            }
        }
    }

