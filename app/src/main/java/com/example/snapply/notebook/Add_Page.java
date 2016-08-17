package com.example.snapply.notebook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by luweiling on 2016/8/17 0017.
 */
public class Add_Page extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_page);
        TextView addTitle = (TextView)findViewById(R.id.add_page_title);
        TextView addContent = (TextView)findViewById(R.id.add_page_content);
        Button addSure = (Button)findViewById(R.id.add_page_sure);
        Button addCancel = (Button)findViewById(R.id.add_page_cancel);
        final String title = addTitle.getText().toString();
        final String content = addContent.getText().toString();
        addSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent intent = new Intent(Add_Page.this,Main_Page.class);
                //intent.putExtra("add_title",title);
                //intent.putExtra("add_content",content);
                startActivity(intent);
                finish();
            }
        });
        addCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
