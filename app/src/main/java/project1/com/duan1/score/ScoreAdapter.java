package project1.com.duan1.score;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import project1.com.duan1.R;

public class ScoreAdapter extends CursorAdapter {
    public ScoreAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_listscore,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv_NameScore = view.findViewById(R.id.tv_NameScore);
        TextView tv_Score = view.findViewById(R.id.tv_Score);
        TextView tv_Room = view.findViewById(R.id.tv_Room);

        tv_Room.setText(cursor.getString(4));
        tv_NameScore.setText(cursor.getString(1));
        tv_Score.setText(cursor.getInt(2)+"");

    }
}
