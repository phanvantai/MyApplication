package com.example.luckyluke.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static final String ACTION_SEND = "com.example.luckyluke.contentprovider.SEND";

    DatabaseManager mDatabase;
    private StringReceiver mReceiver;
    private TextView tvWelcome;
    private Button bShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = mDatabase.getInstance(this);
        mDatabase.createDefaultModel();

        initView();
        getData();

        bShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(mIntent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        tvWelcome = findViewById(R.id.tv_activity_main_welcome);
        bShowList = findViewById(R.id.b_activity_main_show_list);
    }

    private void getData() {
        String mUserName = getIntent().getStringExtra(LoginActivity.KEY_USER_TO_MAIN);
        String mPassword = getIntent().getStringExtra(LoginActivity.KEY_PASSWORD_TO_MAIN);

        String mHelloText = getString(R.string.login_success);
        mHelloText = completeText(mHelloText, new String[]{mUserName, mPassword});
        tvWelcome.setText(mHelloText);

        // register a receiver
        mReceiver = new StringReceiver();
        IntentFilter mFilter = new IntentFilter(ACTION_SEND);
        registerReceiver(mReceiver, mFilter);
    }

    private String completeText(String source, String[] items) {
        for (int i = 0; i < items.length; i++) {
            source = source.replace("{" + i + "}", items[i]);
        }
        return source;
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}