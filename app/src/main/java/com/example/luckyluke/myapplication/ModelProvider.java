package com.example.luckyluke.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ModelProvider extends ContentProvider {
    private DatabaseManager databaseManager;
    public static final String AUTHORITY = "com.example.luckyluke.myapplication.CONTENT_PROVIDER";


    public ModelProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // get model database instance
        databaseManager = databaseManager.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase mSqLiteDatabase = databaseManager.getReadableDatabase();

        Cursor mCursor = mSqLiteDatabase.query(DatabaseManager.TABLE_MODELS,
                projection, selection, selectionArgs, null, null, sortOrder);

        return mCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return 0;
    }
}
