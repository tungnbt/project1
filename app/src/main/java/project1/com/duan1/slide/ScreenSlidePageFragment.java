package project1.com.duan1.slide;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import project1.com.duan1.R;
import project1.com.duan1.question.Question;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ScreenSlidePageFragment extends Fragment {
    ArrayList<Question> questions;

    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSEWR = "checkAnsewr";
    private int mPageNumber; // vị trí hiện tại
    private int checkAss; // biến kiểm tra

    TextView tvNum,tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA,radB,radC,radD;
    ImageView imgIcon;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate( R.layout.fragment_screen_slide_page,container,false );

        tvNum = viewGroup.findViewById( R.id.tvNum );
        tvQuestion = viewGroup.findViewById( R.id.tvQuestion );
        radA = viewGroup.findViewById( R.id.radA );
        radB = viewGroup.findViewById( R.id.radB );
        radC = viewGroup.findViewById( R.id.radC );
        radD = viewGroup.findViewById( R.id.radD );
        imgIcon = viewGroup.findViewById( R.id.ivIcon );
        radioGroup = viewGroup.findViewById( R.id.radGroup );

        return viewGroup;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
          questions = new ArrayList<Question>(  );

          ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
          questions = slideActivity.getData();
          mPageNumber = getArguments().getInt( ARG_PAGE );
          checkAss = getArguments().getInt( ARG_CHECKANSEWR );
    }
    public static ScreenSlidePageFragment create (int pageNumber, int checkAnsewr){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle(  );
        args.putInt( ARG_PAGE,pageNumber);
        args.putInt( ARG_CHECKANSEWR,checkAnsewr );
        fragment.setArguments( args );
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        tvNum.setText( "Câu " + (mPageNumber+1) );
        tvQuestion.setText(questions.get(mPageNumber).getQuestion());
        radA.setText(getItem(mPageNumber).getAns_a());
        radB.setText(getItem(mPageNumber).getAns_b());
        radC.setText(getItem(mPageNumber).getAns_c());
        radD.setText(getItem(mPageNumber).getAns_d());

        imgIcon.setImageResource( getResources().getIdentifier( getItem( mPageNumber ).getImage()+"","drawable","doannvph05829.com.duan1" ) );

        if(checkAss!=0){
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }

        radioGroup.setOnCheckedChangeListener( new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                questions.get( mPageNumber ).choiceID = i;
                questions.get( mPageNumber ).setTraloi( getChoicefromID( i ) );
            }
        } );
    }
    public Question getItem(int positon){
        return questions.get( positon );
    }

    // lấy giá trị từ radiogrop chuyển thành A,B,C,D
     private String getChoicefromID(int ID){
        if ( ID == R.id.radA){
            return "A";
        }else if (ID == R.id.radB){
            return "B";
        }else if (ID == R.id.radC){
            return "C";
        }else if (ID == R.id.radD){
            return "D";
        }else return "";
     }
    private void getCheckAns(String ans){
        if(ans.equals("A")==true){
            radA.setBackgroundColor( Color.RED);
        }
        else if(ans.equals("B")==true){
            radB.setBackgroundColor(Color.RED);
        }else if(ans.equals("C")==true){
            radC.setBackgroundColor(Color.RED);
        }else if(ans.equals("D")==true){
            radD.setBackgroundColor(Color.RED);
        }else ;
    }


}
