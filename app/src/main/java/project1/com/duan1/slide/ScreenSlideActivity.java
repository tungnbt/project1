package project1.com.duan1.slide;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import project1.com.duan1.R;
import project1.com.duan1.question.Question;
import project1.com.duan1.question.QuestionController;

public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 10;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    public int checkAss = 0;
    TextView tvKiemTra;
    TextView tvTimer;
    TextView tvXemDiem;
    String subject;
    int num_exam;

    CounterClass timer;

    //CSDL
    QuestionController questionController;
    ArrayList<Question> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());

        Intent intent = getIntent();
        subject = intent.getStringExtra( "subject" );
        num_exam = intent.getIntExtra( "num_exam", 0 );

        questionController = new QuestionController( this );
        questions = new ArrayList<Question>(  );
        questions = questionController.getQuestion( num_exam,subject );

        tvKiemTra = findViewById( R.id.tvKiemTra );
        tvTimer = findViewById( R.id.tvTimer );
        tvXemDiem = findViewById( R.id.tvScore );
        timer = new CounterClass( 10*60*1000,1000 );

        tvKiemTra.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog( ScreenSlideActivity.this );
                dialog.setContentView( R.layout.check_answer_dialog );
                dialog.setTitle( "Danh sách câu trả lời " );
                CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter( questions,ScreenSlideActivity.this );
                GridView gvLsQuestion = dialog.findViewById( R.id.gvLsQuestion );
                gvLsQuestion.setAdapter( answerAdapter );
                gvLsQuestion.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        mPager.setCurrentItem( i );
                        dialog.dismiss();
                    }
                } );

                Button btnCancle, btnfinlish;
                btnCancle = dialog.findViewById( R.id.btnCancle );
                btnfinlish = dialog.findViewById( R.id.btnFinish );

                btnCancle.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                } );

                btnfinlish.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        timer.cancel();
                        result();
                        dialog.dismiss();
                    }
                } );

               dialog.show();
            }
        } );
   tvXemDiem.setOnClickListener( new View.OnClickListener() {
       @Override
       public void onClick(View view) {
           finish();
           Intent intent = new Intent( ScreenSlideActivity.this, TestDoneActivity.class );
           intent.putExtra( "question",questions );
           startActivity( intent );
       }
   } );


      tvTimer.setOnClickListener( new View.OnClickListener() {
          @Override
          public void onClick(View view) {

          }
      } );
      timer.start();


        }
          public ArrayList<Question> getData(){
              return questions;
          }

          public void result(){
               checkAss = 1;
              if (mPager.getCurrentItem() >= 4) mPager.setCurrentItem(mPager.getCurrentItem() - 4);
              else if (mPager.getCurrentItem() <= 4) mPager.setCurrentItem(mPager.getCurrentItem() + 4);
               tvXemDiem.setVisibility( View.VISIBLE );
              tvKiemTra.setVisibility( View.GONE );
          }


    public class CounterClass extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String countTime = String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            tvTimer.setText(countTime); //SetText cho textview hiện thị thời gian.
        }

        @Override
        public void onFinish() {
            tvTimer.setText("00:00");  //SetText cho textview hiện thị thời gian.
        }
    }



    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create( position,checkAss );
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1f);
                view.setTranslationX(0f);
                view.setScaleX(1f);
                view.setScaleY(1f);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}
