package com.example.snapply.notebook;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;


/**
 * Created by luweiling on 2016/8/17 0017.
 */
public class Main_Page extends Activity {

    private Delete_Broadcast delete_broadcast;

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
                finish();
            }
        });
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("delete_message");
        delete_broadcast = new Delete_Broadcast();
        registerReceiver(delete_broadcast,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(delete_broadcast);
    }

    class Delete_Broadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(Main_Page.this, "Broadcast Recevied", Toast.LENGTH_SHORT).show();
            Title_Fragment title_fragment = (Title_Fragment)getFragmentManager().findFragmentById(R.id.title_fragment);
            title_fragment.delete_refresh();
        }
    }
}
