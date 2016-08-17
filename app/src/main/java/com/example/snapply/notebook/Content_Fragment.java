package com.example.snapply.notebook;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by luweiling on 2016/8/16 0016.
 */
public class Content_Fragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_frag,container,false);
        return view;
    }

    public void refresh(String title,String content) {
        View view1 = view.findViewById(R.id.content);
        view1.setVisibility(View.VISIBLE);
        TextView newTitle = (TextView)view1.findViewById(R.id.content_title);
        TextView newContent = (TextView)view1.findViewById(R.id.content_content);
        newTitle.setText(title);
        newContent.setText(content);
    }
}
