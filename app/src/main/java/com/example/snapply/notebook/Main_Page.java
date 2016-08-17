package com.example.snapply.notebook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


/**
 * Created by luweiling on 2016/8/17 0017.
 */
public class Main_Page extends Activity {

    //private String title;

    //private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_page);
        /*
        Intent intent = getIntent();
        title = intent.getStringExtra("add_title");
        content = intent.getStringExtra("add_content");
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(this,"Data",null,1);
        //dbHelper.getWritableDatabase();
        if (!title.isEmpty() && !content.isEmpty()) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            //使用事物
            db.beginTransaction();
            try {
                ContentValues values = new ContentValues();
                values.put("title",title);
                values.put("content",content);
                db.insert("Data",null,values);
                db.setTransactionSuccessful();
                values.clear();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                db.endTransaction();
            }
            db.close();
        }
        */
        Button add = (Button)findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Page.this,Add_Page.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
