package com.example.lab2_jenish_c0850919_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        //For hiding actionbar...
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td=new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(5000);
                }

                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                finally
                {
                    Intent intent = new Intent(MainActivity.this,homePage.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        td.start();
    }
}