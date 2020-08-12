package project1.com.duan1.slide;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import project1.com.duan1.R;
import project1.com.duan1.question.Question;
import project1.com.duan1.score.ScoreController;

public class TestDoneActivity extends AppCompatActivity  {

    ArrayList<Question> questionArrayList = new ArrayList<Question>(  );
    int numNoAss = 0;
    int numTrue = 0;
    int numFalse =0;
    int total=0;

    TextView tvTrue,tvFalse,tvNoAss,tvTotal;
    Button btnSave,btnExit,btnAgain;

    ScoreController scoreController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_test_done );

        scoreController = new ScoreController(TestDoneActivity.this);
        Intent intent = getIntent();
        questionArrayList = (ArrayList<Question>) intent.getExtras().getSerializable( "question" );
        begin();
        checkResult();
        total = numTrue*10;

        tvNoAss.setText( ""+numNoAss );
        tvFalse.setText( ""+numFalse );
        tvTrue.setText( ""+numTrue );
        tvTotal.setText( ""+total );


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestDoneActivity.this);
                LayoutInflater inflater = TestDoneActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_score,null);
                builder.setView(view);
                final EditText edName = view.findViewById(R.id.ed_name);
                final EditText edRoom = view.findViewById(R.id.ed_room);
                TextView tv_Score = view.findViewById(R.id.tvscore);

                final int numTotal = numTrue * 1;
                tv_Score.setText(numTotal+"điểm");

                builder.setTitle("Lưu Điểm Thi");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = edName.getText().toString().trim();
                        String room = edRoom.getText().toString().trim();

                        scoreController.insertScore(name,numTotal,room);
                        Toast.makeText(TestDoneActivity.this, "Lưu Điểm Thành Công  ", Toast.LENGTH_SHORT).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog b =builder.create();
                b.show();



            }
        });


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(TestDoneActivity.this);
                builder.setIcon(R.drawable.exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
    }



    public void begin(){
        tvFalse= (TextView)findViewById(R.id.tvFalse);
        tvTrue= (TextView)findViewById(R.id.tvTrue);
        tvNoAss= (TextView)findViewById(R.id.tvNotAns);
        tvTotal= (TextView)findViewById(R.id.tvTotalPoint);
        btnAgain=(Button)findViewById(R.id.btnAgain);
        btnSave=(Button)findViewById(R.id.btnSaveScore);
        btnExit=(Button)findViewById(R.id.btnExit);
    }
    // PT check kết quả
    public void checkResult(){
        for(int i=0; i< questionArrayList.size(); i++){
            if(questionArrayList.get(i).getTraloi().equals("")==true){
                numNoAss++;
            }else if(questionArrayList.get(i).getResult().equals(questionArrayList.get(i).getTraloi())==true){
                numTrue++;
            }else numFalse++;
        }
    }
}
