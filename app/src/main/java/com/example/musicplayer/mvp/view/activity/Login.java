package com.example.musicplayer.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.musicplayer.R;

public class Login extends AppCompatActivity {
    private int uid = 1348032807;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
    }

    void init() {
        btnLogin = findViewById(R.id.login_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit();
                editor.putInt("userUid", uid);
                //editor.putString("id","3100484976");
                editor.apply();
                Intent intent = new Intent(Login.this, Home.class);
                startActivity(intent);
            }
        });
    }
}
