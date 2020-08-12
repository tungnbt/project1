package project1.com.duan1.score;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import project1.com.duan1.MainActivity;
import project1.com.duan1.R;



public class ScoreFragment extends Fragment {

ListView lv_Listview;
ScoreAdapter scoreAdapter;
ScoreController scoreController;

    public ScoreFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_score,container,false);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Danh Sách Điểm");


        scoreController = new ScoreController(getActivity());
        lv_Listview = view.findViewById(R.id.lv_Listview);
        Cursor cursor = scoreController.getScore();
        scoreAdapter = new ScoreAdapter(getActivity(),cursor,true);
        lv_Listview.setAdapter(scoreAdapter);
        return view;
    }

}
