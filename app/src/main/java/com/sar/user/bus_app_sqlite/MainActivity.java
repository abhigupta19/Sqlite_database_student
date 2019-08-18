package com.sar.user.bus_app_sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Databasehelper databasehelper=new Databasehelper(this);
        final EditText editText1=findViewById(R.id.editText);
        final EditText editText2=findViewById(R.id.editText2);
        final EditText editText3=findViewById(R.id.editText3);
        Button button1=findViewById(R.id.button);
        final AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
        alertDialog.setCancelable(true);
        alertDialog.setTitle("title");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a=databasehelper.insertvalue(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());
             if(a==true){
                 Toast.makeText(MainActivity.this,"data insert",Toast.LENGTH_SHORT).show();
             }
                else {
                    Toast.makeText(MainActivity.this,"data not insert",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Cursor res=databasehelper.showdata();
                  if(res.getCount()==0)
                  {
                      alertDialog.setMessage("somenthing wrong");
                      alertDialog.show();
                      return;
                  }
                  StringBuffer stringBuffer=new StringBuffer();
                  while(res.moveToNext())
                  {
                      stringBuffer.append("ID :"+res.getString(0)+"\n");
                      stringBuffer.append("NAME :"+res.getString(1)+"\n");
                      stringBuffer.append("SUBJECT :"+res.getString(2)+"\n");
                      stringBuffer.append("MARKS :"+res.getString(3)+"\n\n");
                  }

                 alertDialog.setMessage(stringBuffer.toString());
                  alertDialog.show();
            }
        });
    }
}
