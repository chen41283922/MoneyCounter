package com.example.jason.moneycounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    Button addbtn,delbtn,clrbtn,listbtn;
    TextView textshow;
    ListView listv;
    EditText edititem, editprice;

    ArrayAdapter<String> adapter;
    ArrayList<String> list,list2;

    String[] items= new String[]{"香蕉","西瓜","梨子","水蜜桃"};
    String[] prices=new String[]{"330","120","250","280"};
    String listing;

    double total=0;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textshow = (TextView)findViewById(R.id.textshow);
        addbtn = (Button)findViewById(R.id.addbtn);
        delbtn = (Button)findViewById(R.id.delbtn);
        clrbtn = (Button)findViewById(R.id.clrbtn);
        listbtn = (Button)findViewById(R.id.listbtn);
        edititem = (EditText)findViewById(R.id.edititem);
        editprice = (EditText)findViewById(R.id.editprice);
        listv = (ListView)findViewById(R.id.listv);

        list = new ArrayList<String>();
        list2 = new ArrayList<String>();
        list.addAll( Arrays.asList(items));
        list2.addAll( Arrays.asList(prices));

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,list);
        listv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listv.setAdapter(adapter);

        addbtn.setOnClickListener(mtlistener);
        delbtn.setOnClickListener(mtlistener);
        clrbtn.setOnClickListener(mtlistener);
        listbtn.setOnClickListener(mtlistener);
        listv.setOnItemClickListener(mylistener);


        count=adapter.getCount();


    }

    private Button.OnClickListener mtlistener = new Button.OnClickListener(){
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.addbtn:

                        if(edititem.getText().toString().trim().length()!=0 && editprice.getText().toString().trim().length()!=0) {
                            list.add(edititem.getText().toString());
                            edititem.setText("");
                            list2.add(editprice.getText().toString());
                            editprice.setText("");
                            Toast toastt = Toast.makeText(Main2Activity.this, "Item Added", Toast.LENGTH_LONG);
                            adapter.notifyDataSetChanged();
                            toastt.show();
                        } else if (edititem.getText().toString().trim().length()==0 | editprice.getText().toString().trim().length()==0 ){
                            edititem.setText("");
                            editprice.setText("");
                            Toast toasttt = Toast.makeText(Main2Activity.this, "Input error", Toast.LENGTH_LONG);
                            toasttt.show();
                        }
                        break;

                    case R.id.delbtn:
                        for(int p=count; p >=0; p--)
                        {
                            if(listv.isItemChecked(p))
                            {

                                    list.remove(p);
                                    list2.remove(p);
                                    textshow.setText("0.0");
                                    total = 0;
                                    adapter.notifyDataSetChanged();
                                    Toast toast = Toast.makeText(Main2Activity.this, "Item Deleted", Toast.LENGTH_LONG);
                                    toast.show();

                            }
                        }
                        listv.clearChoices();
                        break;
                    case R.id.clrbtn:
                        new AlertDialog.Builder(Main2Activity.this)
                        .setTitle("Clear ListView").setMessage("Are You Sure You want to clear Listview?").setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.clear();
                                total= 0;
                                textshow.setText(" "+total+" ");
                            }
                        }).setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              //cancel
                            }
                        }).show();


                        break;
                    case R.id.listbtn:

                        String listitem =list.toString();
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        intent.setClass(Main2Activity.this,ListPage.class);
                        bundle.putString("Message",listitem);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;


                }
            }
    };

    private ListView.OnItemClickListener mylistener=
            new ListView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    // 顯示 ListView 的選項內容
                  if(listv.isItemChecked(position)){

                      String[] stockArr = new String[list2.size()];
                      stockArr = list2.toArray(stockArr);
                      double pricet = Double.parseDouble(stockArr[position]);
                      total += pricet;
                      textshow.setText(" "+total+" ");

                  }else{
                      String[] stockArr = new String[list2.size()];
                      stockArr = list2.toArray(stockArr);
                      double pricet = Double.parseDouble(stockArr[position]);
                      total -= pricet;
                      textshow.setText(" "+total+" ");
                  }

                }
            };


}
