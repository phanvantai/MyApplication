package com.example.luckyluke.myapplication;


import java.io.Serializable;

public class Model implements Serializable {
    private int mId;
    private String mName, mFactory, mDisplay, mRamRom, mCamera;
    private boolean selected;

    public Model() {
        this.selected = false;
    }

    public Model(String name, String factory, String display, String ramrom, String camera) {
        this.mName = name;
        this.mFactory = factory;
        this.mCamera = camera;
        this.mDisplay = display;
        this.mRamRom = ramrom;
        this.selected = false;
    }

    public Model(int id, String name, String factory, String display, String ramrom, String camera) {
        this.mName = name;
        this.mId = id;
        this.mFactory = factory;
        this.mCamera = camera;
        this.mDisplay = display;
        this.mRamRom = ramrom;
        this.selected = false;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getFactory() {
        return mFactory;
    }

    public String getDisplay() {
        return mDisplay;
    }

    public String getRamRom() {
        return mRamRom;
    }

    public String getCamera() {
        return mCamera;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setFactory(String mManufactory) {
        this.mFactory = mManufactory;
    }

    public void setDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    public void setRamRom(String mRamRom) {
        this.mRamRom = mRamRom;
    }

    public void setCamera(String mCamera) {
        this.mCamera = mCamera;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean mSelected) {
        this.selected = mSelected;
    }

    @Override
    public String toString() {
        return "Model: "+mName+ " Manufactory: "+ mFactory +"\r\n" +"Display: " +mDisplay+ " RAM/ROM: " + mRamRom+" Camera: "+mCamera ;
    }
}
