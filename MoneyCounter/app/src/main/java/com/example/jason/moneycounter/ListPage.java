package com.example.jason.moneycounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListPage extends AppCompatActivity {

    TextView textshow;
    Button rtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);



        rtn=(Button)findViewById(R.id.rtn);
        rtn.setOnClickListener(mylistener);
        textshow = (TextView)findViewById(R.id.textshow);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String message = bundle.getString("Message");

        textshow.setText(message);
    }
    private Button.OnClickListener mylistener = new Button.OnClickListener(){
        public void onClick(View v){

            finish();


        }
    };
}
