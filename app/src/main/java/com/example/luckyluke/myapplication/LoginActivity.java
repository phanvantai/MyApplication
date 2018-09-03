package com.example.luckyluke.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_USER_TO_MAIN = "KEY_USER_TO_MAIN";
    public static final String KEY_PASSWORD_TO_MAIN = "KEY_PASSWORD_TO_MAIN";

    public static final String KEY_USER_FROM_REGISTER = "KEY_USER_FROM_REGISTER";
    public static final String KEY_PASSWORD_FROM_REGISTER = "KEY_PASSWORD_FROM_REGISTER";

    public static final String USER_FORM = "USER_FORM";

    public static final int REQUEST_CODE_REGISTER = 1;

    ArrayList<LoginInfo> mUserForm;
    CustomPreferences mCustomPreferences;

    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCustomPreferences = new CustomPreferences(this);
        mUserForm = mCustomPreferences.getListLogin(USER_FORM, LoginInfo.class);

        connectView();
    }

    //
    private void connectView() {
        etUserName = findViewById(R.id.et_activity_login_user_name);
        etPassword = findViewById(R.id.et_activity_login_password);

        findViewById(R.id.b_activity_login_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        findViewById(R.id.b_activity_login_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    // start login process
    private void login() {
        boolean error = false;

        String mUserName = etUserName.getText().toString().trim();
        String mPassword = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(mPassword)) {
            etPassword.requestFocus();
            etPassword.setError(getString(R.string.error_field_required));
            error = true;
        }

        if (TextUtils.isEmpty(mUserName)) {
            etUserName.requestFocus();
            etUserName.setError(getString(R.string.error_field_required));
            error = true;
        }



        if (mUserForm.size() == 0) {
            Toast.makeText(LoginActivity.this, "No user", Toast.LENGTH_LONG).show();
        }
        else {
            for (LoginInfo member : mUserForm) {
                error = true;
                LoginInfo user = new LoginInfo(mUserName,mPassword);
                if (member.equals(user)) {
                    error = false;
                    break;
                }
            }
            if (error) {
                Toast.makeText(LoginActivity.this, "Invalid login", Toast.LENGTH_LONG).show();
            }

            if (!error) {
                Intent intent = new Intent(this, MainActivity.class);

                intent.putExtra(KEY_USER_TO_MAIN, mUserName);
                intent.putExtra(KEY_PASSWORD_TO_MAIN, mPassword);

                startActivity(intent);
                Toast.makeText(LoginActivity.this, "Login successfully!!!", Toast.LENGTH_LONG).show();
            }
        }
    }

    // create register activity and receive data
    private void register() {
        Intent mIntent = new Intent(this, RegisterActivity.class);
        startActivityForResult(mIntent, REQUEST_CODE_REGISTER);
    }

    // receive data from register activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REGISTER && resultCode == Activity.RESULT_OK) {
            String mUserName = data.getStringExtra(KEY_USER_FROM_REGISTER);
            String mPassword = data.getStringExtra(KEY_PASSWORD_FROM_REGISTER);
            mUserForm.add(new LoginInfo(mUserName, mPassword));
            etUserName.setText(mUserName);
//            etPassword.setText(mPassword);
        }
    }


    @Override
    protected void onPause() {
        mCustomPreferences.putListLogin(USER_FORM, mUserForm);
        super.onPause();
    }

    @Override
    protected void onResume() {
        etPassword.setText("");
        super.onResume();
    }
}