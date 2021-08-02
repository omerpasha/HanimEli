package com.example.retropostdeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        button=findViewById(R.id.girişButton);
        button2=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });


    }

    public void openRegisterActivity(){
        Intent intent=new Intent(this,RegisterAcitivity.class);
        startActivity(intent);
    }

    public void openLoginActivity(){
        Intent intent=new Intent(this,LoginAcitivty.class);
        startActivity(intent);
    }


}