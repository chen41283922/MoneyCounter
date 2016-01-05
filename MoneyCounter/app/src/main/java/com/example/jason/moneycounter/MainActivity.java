package com.example.jason.moneycounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnnext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnnext = (Button)findViewById(R.id.button);
        btnnext.setOnClickListener(mylistener);

    }

    private Button.OnClickListener mylistener = new Button.OnClickListener(){
        public void onClick(View v){

                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                startActivity(intent);

        }
    };

}
