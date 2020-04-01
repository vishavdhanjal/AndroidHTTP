package com.example.appfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText txtVal1=findViewById(R.id.txtVal1);
        final EditText txtVal2=findViewById(R.id.txtVal2);
        final TextView txtResult=findViewById(R.id.txtResult);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int x=Integer.parseInt(String.valueOf(txtVal1.getText()));
                        int y=Integer.parseInt(String.valueOf(txtVal2.getText()));

                        Intent intent=getIntent();
                        intent.putExtra("result",x+y);
                    }
                }
        );
    }
}