package com.example.githubtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    // Login Button
    private Button logIn;
    // Edit Text to input Github User name
    private EditText inputUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logIn = (Button) findViewById(R.id.bt_login);
        inputUserName = findViewById(R.id.input_username);
    }


    public void getUser(View view) {
        // Intent to redirect to another activity
        Intent i = new Intent(LoginActivity.this, UserActivity.class);
        i.putExtra("STRING I NEED", inputUserName.getText().toString());
        startActivity(i);
    }
}