package com.example.luckyluke.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.luckyluke.myapplication.DatabaseManager.ID;

public class DetailsActivity extends AppCompatActivity {

    DatabaseManager mDatabase;

    int mId;
    Button bEdit;
    boolean mEdit = false;
    TextView tvId;
    EditText etName, etFactory, etDisplay, etRr, etCamera;
    ImageView ivModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mDatabase = mDatabase.getInstance(this);
        connectView();
        getData();

        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mEdit) {
                    mEdit = true;
                    bEdit.setText("Save");
                    setEnable();
                }
                else {
                    mEdit = false;
                    bEdit.setText("Edit");

                    Model model = new Model(mId, etName.getText().toString(),
                            etFactory.getText().toString(), etDisplay.getText().toString(),
                            etRr.getText().toString(), etCamera.getText().toString());
                    mDatabase.updateModel(model);
                    Toast.makeText(getApplicationContext(),
                                "Data updated!", Toast.LENGTH_LONG).show();
                    setDisable();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDisable() {
        etCamera.setEnabled(false);
        etDisplay.setEnabled(false);
        etFactory.setEnabled(false);
        etName.setEnabled(false);
        etRr.setEnabled(false);
    }

    private void setEnable() {
        etCamera.setEnabled(true);
        etDisplay.setEnabled(true);
        etFactory.setEnabled(true);
        etName.setEnabled(true);
        etRr.setEnabled(true);
    }


    private void getData() {
        Intent mIntent = getIntent();
        Bundle mBundle = mIntent.getBundleExtra("bundle");
        mId = mBundle.getInt(ID);

        tvId.setText(Integer.toString(mId));
        etName.setText(mDatabase.getModelById(mId).getName());
        etFactory.setText(mDatabase.getModelById(mId).getFactory());
        etDisplay.setText(mDatabase.getModelById(mId).getDisplay());
        etRr.setText(mDatabase.getModelById(mId).getRamRom());
        etCamera.setText(mDatabase.getModelById(mId).getCamera());
        ivModel.setImageResource(R.drawable.apple);
    }

    private void connectView() {
        bEdit = findViewById(R.id.b_activity_details_edit);
        tvId = findViewById(R.id.tv_activity_details_id);
        etName = findViewById(R.id.et_activity_details_name);
        etFactory = findViewById(R.id.et_activity_details_factory);
        etDisplay = findViewById(R.id.et_activity_details_display);
        etRr = findViewById(R.id.et_activity_details_ramrom);
        etCamera = findViewById(R.id.et_activity_details_camera);
        ivModel = findViewById(R.id.iv_activity_details_image);
        setDisable();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
