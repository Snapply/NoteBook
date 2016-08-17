package com.example.snapply.notebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;


/**
 * Created by luweiling on 2016/8/17 0017.
 */
public class Main_Page extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_page);
        Button add = (Button)findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main_Page.this,Add_Page.class);
                startActivity(intent);
            }
        });

    }
}
