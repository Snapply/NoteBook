package com.example.snapply.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luweiling on 2016/8/16 0016.
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    private int resourceId;

    public NoteAdapter(Context context, int itemId, List<Note> object) {
        super(context,itemId,object);
        resourceId = itemId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Note note = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        } else {
            view = convertView;
        }
        TextView title = (TextView)view.findViewById(R.id.title_list_item);
        title.setText(note.getTitle());
        return view;
    }
}
