package org.techtown.application;

import android.app.Activity;

import androidx.lifecycle.Observer;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.application.R;
//import org.techtown.application.page2;
//import org.techtown.application.page3;
//import org.techtown.application.page4;
//import org.techtown.application.page5;


public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory()).get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        //로그인버튼
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(id와 비번 불일치) ->  toast:ID와 PW가 일치하지 않습니다 + text clear

                //if (선호태그 등록x)
                //Intent tag = new Intent(LoginActivity.this, TagActivity.class);
                //startActivity(tag);

                //if 선호태그 등록완료
                Intent main = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(main);

            }
        });

        //sign up 버튼
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent signup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signup);
            }
        });

        //ID PW 찾기 버튼
        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent find = new Intent(LoginActivity.this, FindinfoActivity.class);
               startActivity(find);
            }
        });
    }}