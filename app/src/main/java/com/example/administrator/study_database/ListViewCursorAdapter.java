package com.example.administrator.study_database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.administrator.study_database.DB.DBHelper;

public class ListViewCursorAdapter extends CursorAdapter {

    private LayoutInflater inflater;

    public ListViewCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        ViewHolder viewHolder = new ViewHolder();

        View view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        viewHolder.name = view.findViewById(R.id.item_textView1);
        viewHolder.mobile = view.findViewById(R.id.item_textView2);

        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        String nameData = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME_01));
        String mobileData = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME_02));

        viewHolder.name.setText(nameData);
        viewHolder.mobile.setText(mobileData);
    }

    static class ViewHolder {
        // public ImageView icon;
        TextView name;
        TextView mobile;
    }

}
