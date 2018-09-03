package com.example.luckyluke.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_databases";

    private static final String TABLE_MODELS = "models";
    public static final String ID = "id";
    private static final String NAME = "name";
    private static final String FACTORY = "factory";
    private static final String DISPLAY = "display";
    private static final String RAMROM = "ramrom";
    private static final String CAMERA = "camera";
    private static final String CREATE_TABLE_MODELS =
            "CREATE TABLE " + TABLE_MODELS + " (" +
                    ID +" integer primary key Autoincrement, "+
                    NAME + " TEXT, "+
                    FACTORY + " TEXT, "+
                    DISPLAY + " TEXT," +
                    RAMROM + " TEXT," +
                    CAMERA + " TEXT)";
    public static final String TABLE_CONTACTS = "contacts";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String CREATE_TABLE_CONTACTS =
            "CREATE TABLE " + TABLE_CONTACTS + " (" +
                    ID + " integer primary key Autoincrement, " +
                    NAME + " TEXT, " +
                    PHONE_NUMBER + " TEXT)";

    private Context mContext;
    private static DatabaseManager mInstance;

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    public static DatabaseManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseManager(context);
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // create model table
        sqLiteDatabase.execSQL(CREATE_TABLE_MODELS);

        // create contact table
        sqLiteDatabase.execSQL(CREATE_TABLE_CONTACTS);

        Toast.makeText(mContext, "Create successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MODELS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
        Toast.makeText(mContext, "Drop successfully", Toast.LENGTH_SHORT).show();
    }

    // if table model have nothing, add few model
    public void createDefaultModel() {
        if (this.getModelsCount() == 0) {
            this.addModel(new Model( "Galaxy S9", "Samsung",
                    "5.8inch", "4GB/64GB","8MP/12MP"));
            this.addModel(new Model("Iphone 8+", "Apple",
                    "5.5inch", "3GB/256GB","8MP/12MP"));
            this.addModel(new Model("Iphone 6", "Apple",
                    "4.7inch", "2GB/64GB","8MP/12MP"));
            this.addModel(new Model("Xperia Z3", "Sony",
                    "5.2inch", "3GB/32GB","2.2MP/20MP"));
            this.addModel(new Model("Nokia X6", "Global",
                    "5.8inch", "6GB/64GB","16MP/16MP"));
            this.addModel(new Model("Xperia XA2+", "Sony",
                    "6.0inch", "6GB/64GB","8MP/23MP"));
            this.addModel(new Model("Pixel 2", "Google",
                    "5.0inch", "4GB/64GB","8MP/12.2MP"));
            this.addModel(new Model("Galaxy S8", "Samsung",
                    "5.8inch", "4GB/64GB","8MP/12MP"));
            this.addModel(new Model("Iphone X", "Apple",
                    "5.8inch", "3GB/256GB","7MP/12MP"));
            this.addModel(new Model("P20 Pro", "Huawei",
                    "6.1inch", "6GB/128GB","24MP/40MP"));
        }
    }

    //return amount of models
    public int getModelsCount() {
        String mQuery = "SELECT * FROM " + TABLE_MODELS;
        SQLiteDatabase mSqLiteDatabase = this.getReadableDatabase();
        Cursor mCursor = mSqLiteDatabase.rawQuery(mQuery, null);
        int mCount = mCursor.getCount();
        mCursor.close();
        mSqLiteDatabase.close();

        return mCount;
    }

    // add new a model
    public void addModel(Model mModel){
        SQLiteDatabase mSqLiteDatabase = this.getWritableDatabase();
        ContentValues mValues = new ContentValues();
        mValues.put(NAME, mModel.getName());
        mValues.put(FACTORY, mModel.getFactory());
        mValues.put(DISPLAY, mModel.getDisplay());
        mValues.put(RAMROM, mModel.getRamRom());
        mValues.put(CAMERA, mModel.getCamera());

        mSqLiteDatabase.insert(TABLE_MODELS,null, mValues);

        mSqLiteDatabase.close();
    }

    // return a model by id
    public Model getModelById(int id) {
        SQLiteDatabase mSqLiteDatabase = this.getReadableDatabase();
        Cursor mCursor = mSqLiteDatabase.query(TABLE_MODELS, new String[] {ID, NAME,
                        FACTORY, DISPLAY, RAMROM, CAMERA }, ID + "=?",
                new String[] { String.valueOf(id) },null,null,null,null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }

        Model mModel = new Model(mCursor.getInt(0), mCursor.getString(1),
                mCursor.getString(2), mCursor.getString(3),
                mCursor.getString(4), mCursor.getString(5));
        mCursor.close();
        mSqLiteDatabase.close();
        return mModel;
    }

    // return all model
    public ArrayList<Model> getAllModel() {
        ArrayList<Model> mListModel = new ArrayList<>();

        // Select all query
        String mSelectQuery = "SELECT * FROM " + TABLE_MODELS;

        SQLiteDatabase mSqLiteDatabase = this.getWritableDatabase();
        Cursor mCursor = mSqLiteDatabase.rawQuery(mSelectQuery, null);

        if (mCursor.moveToFirst()) {
            do {
                Model mModel = new Model(mCursor.getInt(0), mCursor.getString(1),
                        mCursor.getString(2), mCursor.getString(3),
                        mCursor.getString(4), mCursor.getString(5));
                mListModel.add(mModel);
            }
            while (mCursor.moveToNext());
        }

        mCursor.close();
        mSqLiteDatabase.close();
        return mListModel;
    }

    // delete a model
    public void deleteModel(Model model) {
        SQLiteDatabase mSqLiteDatabase = this.getWritableDatabase();
        mSqLiteDatabase.delete(TABLE_MODELS, ID + " = ?",
                new String[] { String.valueOf(model.getId()) });
        mSqLiteDatabase.close();
    }

    // update a model
    public boolean updateModel(Model model) {
        SQLiteDatabase mSqLiteDatabase = this.getWritableDatabase();
        ContentValues mValues = new ContentValues();

        mValues.put(NAME, model.getName());
        mValues.put(FACTORY, model.getFactory());
        mValues.put(DISPLAY, model.getDisplay());
        mValues.put(RAMROM, model.getRamRom());
        mValues.put(CAMERA, model.getCamera());

        mSqLiteDatabase.update(TABLE_MODELS, mValues, ID + " = ?",
                new String[] {Integer.toString(model.getId())});

        mSqLiteDatabase.close();
        return true;
    }
}
