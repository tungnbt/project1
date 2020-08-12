package project1.com.duan1.question;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class QuestionController {
    private DatabaseHelper dbHelper;

    public QuestionController(Context context) {
        dbHelper= new DatabaseHelper(context);
    }

    //Lấy danh sách câu hỏi
    public ArrayList<Question> getQuestion(int num_exam, String subject){
        ArrayList<Question> lsData= new ArrayList<Question>();
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM tracnghiem WHERE num_exam = '"+
                num_exam+"' AND subject='"+subject+"' ORDER BY random()",null);
        cursor.moveToFirst();
        do {
            Question item;
            item= new Question(cursor.getInt(0), cursor.getString(1),cursor.getString(2),
                    cursor.getString(3), cursor.getString(4),cursor.getString(5),
                    cursor.getString(6),cursor.getInt(7),cursor.getString(8),
                    cursor.getString(9),"");
            lsData.add(item);
        }while (cursor.moveToNext());
        return lsData;
    }
    public Cursor getSearchQuestion(String key) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM tracnghiem WHERE question LIKE '%"+
                key+"%'",null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
