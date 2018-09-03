package com.example.luckyluke.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName;
    private EditText etPassword;
    private EditText etRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        connectView();
    }

    private void connectView() {
        etUserName = findViewById(R.id.et_activity_register_user_name);
        etPassword = findViewById(R.id.et_activity_register_password);
        etRePassword = findViewById(R.id.et_activity_register_rePassword);

        findViewById(R.id.b_activity_register_register).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.b_activity_register_register:
                register();
                break;
        }
    }

    private void register() {
        boolean error = false;

        // get data
        String mUser = etUserName.getText().toString().trim();
        String mPassword = etPassword.getText().toString().trim();
        String mRePassword = etRePassword.getText().toString().trim();

        // password empty
        if (TextUtils.isEmpty(mRePassword)) {
            etRePassword.requestFocus();
            etRePassword.setError(getString(R.string.error_field_required));
            error = true;
        }

        // password empty
        if (TextUtils.isEmpty(mPassword)) {
            etPassword.requestFocus();
            etPassword.setError(getString(R.string.error_field_required));
            error = true;
        }

        // username empty
        if (TextUtils.isEmpty(mUser)) {
            etUserName.requestFocus();
            etUserName.setError(getString(R.string.error_field_required));
            error = true;
        }

        // repassword not match
        if (!mPassword.equals(mRePassword)) {
            etRePassword.requestFocus();
            etRePassword.setError(getString(R.string.error_password_not_match));
            error = true;
        }

        // all data is ok
        //LoginInfo user = new LoginInfo(mUser,password);
        //loginInfos.add(user);

        if (!error) {
            // create intent to send data back Login Activity
            Intent mIntent = new Intent();

            // send data
            mIntent.putExtra(LoginActivity.KEY_USER_FROM_REGISTER, mUser);
            mIntent.putExtra(LoginActivity.KEY_PASSWORD_FROM_REGISTER, mPassword);

            setResult(RESULT_OK, mIntent);
            finish();
        }
    }
}
