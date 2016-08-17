package com.example.snapply.notebook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by luweiling on 2016/8/17 0017.
 */
public class Add_Page extends Activity {

    private EditText addTitle;

    private EditText addContent;

    private MySQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_page);
        dbHelper = new MySQLiteOpenHelper(this,"Data",null,1);
        addTitle = (EditText) findViewById(R.id.add_page_title);
        addContent = (EditText) findViewById(R.id.add_page_content);
        Button addSure = (Button)findViewById(R.id.add_page_sure);
        Button addCancel = (Button)findViewById(R.id.add_page_cancel);
        addSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(Add_Page.this,"Data",null,1);
                dbHelper.getWritableDatabase();
                if (!(title.isEmpty()) && !(content.isEmpty())) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("title",title);
                    values.put("content",content);
                    db.insert("Data",null,values);
                    values.clear();
                }
                */
                String title = addTitle.getText().toString();
                String content = addContent.getText().toString();
                Intent intent = new Intent(Add_Page.this,Main_Page.class);
                //intent.putExtra("add_title",title);
                //intent.putExtra("add_content",content);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                try {
                    db.beginTransaction();
                    ContentValues values = new ContentValues();
                    values.put("title",title);
                    values.put("content",content);
                    db.insert("Data",null,values);
                    db.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    db.endTransaction();
                    db.close();
                }
                startActivity(intent);
                finish();
            }
        });
        addCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Page.this,Main_Page.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
