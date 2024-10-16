package com.example.lab5_2;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.lab5_2.R;

public class MainActivity extends FragmentActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {
    TextView resultView=null;
    CursorLoader cursorLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultView= (TextView) findViewById(R.id.res);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    public void onClickDisplayNames(View view) {
        getSupportLoaderManager().initLoader(1, null, this);
    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        cursorLoader= new CursorLoader(this, Uri.parse("content://com.example.lab5_1.MyProvider/names"), null, null, null, null);
        return cursorLoader;
    }
    @SuppressLint("Range")
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> arg0, Cursor cursor) {
        cursor.moveToFirst();
        StringBuilder res=new StringBuilder();
        while (!cursor.isAfterLast()) {
            res.append("\n").append(cursor.getString(cursor.getColumnIndex("id"))).append("-").append(cursor.getString(cursor.getColumnIndex("name")));
            cursor.moveToNext();
        }
        resultView.setText(res);
    }
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> arg0) {
// TODO Auto-generated method stub
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
