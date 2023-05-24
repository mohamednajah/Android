package com.example.threadapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Activity activity;
    boolean runner;
    FirstThread f1;
    SecondThread f2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity=this;
    }


    public void start(View view){
        runner=true;
        f1=new FirstThread();
        f1.start();
        f2=new SecondThread();
        f2.start();

    }
    public void stop(View view){
        runner=false;
    }
    public void message(View view){
        Toast.makeText(this,"bonjour c un test",Toast.LENGTH_LONG).show();
    }

    public class FirstThread extends Thread {

        int comp = 0;

        public void run() {
            final TextView comp1 = findViewById(R.id.textView2);
            while (runner) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        comp1.setText(String.valueOf(comp));
                    }
                });
                comp++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


        }
    }
    public class SecondThread extends Thread{
        int compt=100;

        @Override
        public void run() {
            final TextView comp2=findViewById(R.id.textView4);
            while (runner){
                activity.runOnUiThread(()->{
                    comp2.setText(String.valueOf(compt));
                });
                compt++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }




}