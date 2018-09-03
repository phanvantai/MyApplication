package com.example.luckyluke.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ModelsAdapter extends ArrayAdapter<Model> {

    ArrayList<Model> mListModels;

    CheckBox mCheckBox;

    public ModelsAdapter(Context context, ArrayList<Model> models, CheckBox mCheckBox) {
        super(context, 0, models);
        this.mListModels = models;
        this.mCheckBox = mCheckBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Model mModel = mListModels.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_model,
                    parent, false);
        }

        TextView tvId = convertView.findViewById(R.id.tv_item_model_id);
        TextView tvName = convertView.findViewById(R.id.tv_item_model_name);
        TextView tvFactory = convertView.findViewById(R.id.tv_item_model_factory);
        TextView tvDisplay = convertView.findViewById(R.id.tv_item_model_display);
        TextView tvRr = convertView.findViewById(R.id.tv_item_model_ramrom);
        TextView tvCam = convertView.findViewById(R.id.tv_item_model_camera);
        ImageView ivModel = convertView.findViewById(R.id.iv_item_model_image);
        CheckBox cbCheck = convertView.findViewById(R.id.cb_item_model_check);

        tvId.setText(Integer.toString(mModel.getId()));
        tvName.setText(mModel.getName());
        tvFactory.setText(mModel.getFactory());
        tvDisplay.setText(mModel.getDisplay());
        tvRr.setText(mModel.getRamRom());
        tvCam.setText(mModel.getCamera());
        ivModel.setImageResource(R.drawable.apple);
        cbCheck.setTag(position);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean mBoolean) {
                mCheckBox.setChecked(mBoolean);
                for (int i = 0; i < mListModels.size(); i++) {
                    mListModels.get(i).setSelected(mBoolean);
                }
                notifyDataSetChanged();
            }
        });

        cbCheck.setChecked(mListModels.get(position).isSelected());
        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean mBoolean) {
                int position = (Integer) compoundButton.getTag();
                if (mBoolean) {
                    mListModels.get(position).setSelected(true);
                }
                else {
                    mListModels.get(position).setSelected(false);
                    if (mCheckBox.isChecked()) {
                        mCheckBox.setChecked(false);
                        for (int i = 0; i < mListModels.size(); i++) {
                            mListModels.get(i).setSelected(true);
                            mListModels.get(position).setSelected(false);
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return mListModels.size();
    }

    @Nullable
    @Override
    public Model getItem(int position) {
        return mListModels.get(position);
    }
}
