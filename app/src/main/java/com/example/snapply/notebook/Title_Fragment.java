package com.example.snapply.notebook;

import android.app.Activity;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luweiling on 2016/8/16 0016.
 */
public class Title_Fragment extends Fragment {

    private ListView titleListView;

    private List<Note> noteList;

    public NoteAdapter adapter;

    private MySQLiteOpenHelper dbHelper;

    public LayoutInflater layoutInflater;

    public ViewGroup viewGroup;

    public Bundle bundle;

    private View view;

    private Delete_Broadcast delete_message;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TAG", "Title_Fragment onAttach: ");
        dbHelper = new MySQLiteOpenHelper(activity,"Data",null,1);
        noteList = init();
        adapter = new NoteAdapter(activity,R.layout.title_list_item, noteList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "Title_Fragment onCreate: ");
        IntentFilter intentfilter1 = new IntentFilter();
        intentfilter1.addAction("delete_message");
        delete_message = new Delete_Broadcast();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(delete_message,intentfilter1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutInflater = inflater;
        viewGroup = container;
        bundle = savedInstanceState;
        view = inflater.inflate(R.layout.index_frag,container,false);
        titleListView = (ListView)view.findViewById(R.id.index_list_frag);
        titleListView.setAdapter(adapter);
        titleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = noteList.get(i);
                Content_Fragment content_fragment = (Content_Fragment)getFragmentManager().findFragmentById(R.id.content_fragment);
                content_fragment.refresh(note.getTitle(),note.getContent());
            }
        });
        Log.d("TAG", "Title_Fragment onCreateView: ");
        return view;
    }

    class Delete_Broadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TAG", "Title_Fragment Broadcast received");
            Toast.makeText(getActivity(), "Title_Fragment Received", Toast.LENGTH_SHORT).show();
            String retitle = intent.getStringExtra("title");
            String recontent = intent.getStringExtra("content");
            Note renote = new Note();
            renote.setTitle(retitle);
            renote.setContent(recontent);
            //noteList.remove(renote);
            noteList.clear();
            List<Note> newlist = new ArrayList<>();
            newlist = init();
            noteList.addAll(newlist);
            adapter.notifyDataSetChanged();
            //titleListView.refreshDrawableState();
            Log.d("TAG", "广播测试");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("TAG", "Title_Fragment onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", "Title_Fragment onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "Title_Fragment onResume: ");
        //adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG", "Title_Fragment onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TAG", "Title_Fragment onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("TAG", "Title_Fragment onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "Title_Fragment onDestroy: ");
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(delete_message);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("TAG", "Title_Fragment onDetach: ");
    }

    public void delete_refresh(){
    }

    public Fragment delete() {
        return this;
    }
    private List<Note> init(){
        List<Note> notes = new ArrayList<Note>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Data",null,null,null,null,null,null);
        Note note1 = new Note();
        Note note2 = new Note();
        note1.setTitle("Test 1");
        note1.setContent("First test message");
        note2.setTitle("Test 2");
        note2.setContent("Second test message");
        notes.add(note1);
        notes.add(note2);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                //Log.d("TAG", "Title_Fragment init: title-"+title);
                //Log.d("TAG", "Title_Fragment init: content-"+content);
                note.setTitle(title);
                note.setContent(content);
                notes.add(note);
            } while (cursor.moveToNext());
        }
        return notes;
    }
}
