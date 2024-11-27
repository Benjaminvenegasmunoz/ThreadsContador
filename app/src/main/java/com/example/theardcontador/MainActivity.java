package com.example.theardcontador;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txtcounter;
    private int counter = 0;
    private boolean iniciar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtcounter=findViewById(R.id.txt_contador);
    }

    public void iniciar(View view){
        if(!iniciar) {
            iniciar = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (iniciar){
                        counter++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtcounter.setText(String.valueOf(counter));
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public void stop(View view){
        iniciar=false;
    }
}