package com.example.luckyluke.myapplication;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.luckyluke.myapplication.DatabaseManager.ID;

public class ListActivity extends AppCompatActivity {

    DatabaseManager mDatabase;
    ArrayList<Model> mListModels = new ArrayList<>();
    public ModelsAdapter mAdapter;

    ListView lvModels;
    Button bAddModel, bDelModel;
    CheckBox cbCheckAll;
    Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mDatabase = mDatabase.getInstance(this);
        mListModels = mDatabase.getAllModel();

        connectView();

        mAdapter = new ModelsAdapter(this, mListModels, cbCheckAll);
        lvModels.setAdapter(mAdapter);
        lvModels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sendData(i);
            }
        });

        bAddModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
        bDelModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCheckedItems();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void connectView() {
        cbCheckAll = findViewById(R.id.cb_activity_list_check_all);
        bAddModel = findViewById(R.id.b_activity_list_add);
        bDelModel = findViewById(R.id.b_activity_list_delete);
        lvModels = findViewById(R.id.lv_activity_list_list);
    }

    private void deleteCheckedItems() {
        for (int i = mAdapter.getCount() - 1; i >= 0; i--) {
            Model model = mAdapter.getItem(i);
            if (model.isSelected()) {
                mDatabase.deleteModel(model);
                mListModels.remove(model);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    // add a back item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendData(int i) {
        Intent mIntent = new Intent(getApplicationContext(), DetailsActivity.class);
        Bundle mBundle = new Bundle();

        mBundle.putInt(ID, mListModels.get(i).getId());
        mIntent.putExtra("bundle", mBundle);
        startActivity(mIntent);
    }

    private void createDialog() {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.add_model_layout);
        mDialog.setTitle("Add model");

        final EditText factory = mDialog.findViewById(R.id.et_add_model_layout_factory);
        final EditText name = mDialog.findViewById(R.id.et_add_model_layout_name);
        final EditText display = mDialog.findViewById(R.id.et_add_model_layout_display);
        final EditText ramrom = mDialog.findViewById(R.id.et_add_model_layout_ramrom);
        final EditText camera = mDialog.findViewById(R.id.et_add_model_layout_camera);

        Button bDialog = mDialog.findViewById(R.id.btn_add_model_layout_add);

        bDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mManu = factory.getText().toString();
                String mName = name.getText().toString();
                String mDis = display.getText().toString();
                String mRr = ramrom.getText().toString();
                String mCa = camera.getText().toString();

                Model model = new Model(mName, mManu, mDis, mRr, mCa);

                mDatabase.addModel(model);
                //mListModels.add(model);
                mAdapter.add(model);
                //mAdapter.notifyDataSetChanged();

                mDialog.dismiss();
            }
        });

        mDialog.show();
    }

    @Override
    protected void onResume() {
        mAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
