package com.example.snapply.notebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by luweiling on 2016/8/16 0016.
 */
public class Content_Fragment extends Fragment {

    private View view;

    private Context context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_frag,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button delete = (Button)view.findViewById(R.id.content_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "delete_button onClick: ");
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Warning");
                alert.setMessage("确认删除此条信息吗？");
                alert.setCancelable(false);
                alert.setPositiveButton("确 定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        View view1 = Content_Fragment.this.view.findViewById(R.id.content);
                        view1.setVisibility(View.INVISIBLE);
                        TextView titleView = (TextView)view1.findViewById(R.id.content_title);
                        TextView contentView = (TextView)view1.findViewById(R.id.content_content);
                        String title = titleView.getText().toString();
                        String content = contentView.getText().toString();
                        MySQLiteOpenHelper dbhelper = new MySQLiteOpenHelper(context,"Data",null,1);
                        SQLiteDatabase db = dbhelper.getWritableDatabase();
                        db.beginTransaction();
                        try {
                            if (!title.isEmpty())
                                db.delete("Data","title = ?",new String[]{title});
                            if (!content.isEmpty())
                                db.delete("Data","content = ?",new String[]{content});
                            db.setTransactionSuccessful();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            db.endTransaction();
                            db.close();
                        }
                        //Title_Fragment title_fragment = (Title_Fragment)getFragmentManager().findFragmentById(R.id.title_fragment);
                        //title_fragment.delete_refresh(title,content);
                        //Intent intent = new Intent(getActivity(),Main_Page.class);
                        //startActivity(intent);
                        //getActivity().finish();
                        Intent intent = new Intent("delete_message");
                        intent.putExtra("title",title);
                        intent.putExtra("content",content);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }
                });
                alert.setNegativeButton("取 消",null);
                alert.show();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void refresh(String title, String content) {
        View view1 = view.findViewById(R.id.content);
        view1.setVisibility(View.VISIBLE);
        TextView newTitle = (TextView)view1.findViewById(R.id.content_title);
        TextView newContent = (TextView)view1.findViewById(R.id.content_content);
        newTitle.setText(title);
        newContent.setText(content);
    }
}
