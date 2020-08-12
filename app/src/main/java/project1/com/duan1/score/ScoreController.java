package project1.com.duan1.score;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import project1.com.duan1.question.DatabaseHelper;

public class ScoreController {
    private DatabaseHelper dbHelper;

    public ScoreController(Context context) {
        dbHelper= new DatabaseHelper(context);
    }

    //Lấy danh sách câu hỏi

    public void insertScore(String name,int score,String room){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("score",score);
        values.put("room",room);
        sqLiteDatabase.insert("tbscore",null,values);
        sqLiteDatabase.close();
    }



    public Cursor getScore(){
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.query("tbscore",
                null,
                null,
                null,
                null,
                null,
                "_id DESC",
                null);
        if (cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

}
