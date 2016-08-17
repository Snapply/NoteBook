package com.example.snapply.notebook;

import android.app.Activity;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luweiling on 2016/8/16 0016.
 */
public class Title_Fragment extends Fragment {
    private ListView titleListView;

    private List<Note> titleList;

    private NoteAdapter adapter;

    private MySQLiteOpenHelper dbHelper;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dbHelper = new MySQLiteOpenHelper(activity,"Data",null,1);
        titleList = init();
        adapter = new NoteAdapter(activity,R.layout.title_list_item,titleList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_frag,container,false);
        titleListView = (ListView)view.findViewById(R.id.index_frag);
        titleListView.setAdapter(adapter);
        titleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = titleList.get(i);
                Content_Fragment content_fragment = (Content_Fragment)getFragmentManager().findFragmentById(R.id.content_fragment);
                content_fragment.refresh(note.getTitle(),note.getContent());
            }
        });
        return view;
    }

    private List<Note> init(){
        List<Note> notes = new ArrayList<Note>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Data",null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                note.setTitle(title);
                note.setContent(content);
                notes.add(note);
            } while (cursor.moveToNext());
        }
        return notes;
    }
}
