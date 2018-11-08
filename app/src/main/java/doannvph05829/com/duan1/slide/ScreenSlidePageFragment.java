package doannvph05829.com.duan1.slide;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import doannvph05829.com.duan1.R;
import doannvph05829.com.duan1.question.Question;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class ScreenSlidePageFragment extends Fragment {
    ArrayList<Question> questions;

    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    TextView tvNum,tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA,radB,radC,radD;

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
    }
    public static ScreenSlidePageFragment create (int pageNumber){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle(  );
        args.putInt( ARG_PAGE,pageNumber);
        fragment.setArguments( args );
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );

        tvNum.setText( "Câu" + (mPageNumber+1) );
        tvQuestion.setText( questions.get( mPageNumber ).getQuestion() );
        radA.setText( questions.get( mPageNumber ).getAns_a() );
        radB.setText( questions.get( mPageNumber ).getAns_b() );
        radC.setText( questions.get( mPageNumber ).getAns_c() );
        radD.setText( questions.get( mPageNumber ).getAns_d() );

    }
}
