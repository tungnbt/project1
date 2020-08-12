package project1.com.duan1.monhoc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import project1.com.duan1.MainActivity;
import project1.com.duan1.R;
import project1.com.duan1.slide.ScreenSlideActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class GDCDFragment extends Fragment {
    ExamAdapter examAdapter;
    GridView gridView;
    ArrayList<Exam> exams = new ArrayList<Exam>( );

    public GDCDFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Môn GDCD");
        return inflater.inflate( R.layout.fragment_gdcd, container, false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        gridView = getActivity().findViewById( R.id.gvExam );

        exams.add( new Exam( "Đề Số 1" ) );
        exams.add( new Exam( "Đề Số 2" ) );
        exams.add( new Exam( "Đề Số 3" ) );
        exams.add( new Exam( "Đề Số 4" ) );

        examAdapter = new ExamAdapter( getActivity(),exams );
        gridView.setAdapter( examAdapter );

        gridView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent( getActivity(), ScreenSlideActivity.class );
                intent.putExtra( "num_exam",i+1 );
                intent.putExtra( "subject","gdcd" );
                startActivity( intent );
            }
        } );
    }
}
