package project1.com.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.io.IOException;


import project1.com.duan1.monhoc.GDCDFragment;
import project1.com.duan1.monhoc.HoaHocFragment;
import project1.com.duan1.monhoc.VatLyFragment;
import project1.com.duan1.question.DatabaseHelper;
import project1.com.duan1.monhoc.SearchQuesFragment;
import project1.com.duan1.score.ScoreFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );



        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        ScoreFragment scoreFragment = new ScoreFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace( R.id.content_main, scoreFragment, scoreFragment.getTag()).commit();








        DatabaseHelper db = new DatabaseHelper(  this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.home) {

            ScoreFragment scoreFragment = new ScoreFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace( R.id.content_main, scoreFragment, scoreFragment.getTag()).commit();

        } else if (id == R.id.mongdcd) {

            GDCDFragment gdcdFragment = new GDCDFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace( R.id.content_main, gdcdFragment, gdcdFragment.getTag()).commit();

        } else if (id == R.id.monly) {
            VatLyFragment vatLyFragment = new VatLyFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace( R.id.content_main, vatLyFragment, vatLyFragment.getTag()).commit();
        }

        else if (id == R.id.monhoa) {
            HoaHocFragment hoaHocFragment = new HoaHocFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace( R.id.content_main, hoaHocFragment, hoaHocFragment.getTag()).commit();

        }else if (id == R.id.thoat){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            // Tao su kien ket thuc app
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
            finish();


        }
        else if (id == R.id.timkiem){
            SearchQuesFragment searchQuesFragment = new SearchQuesFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace( R.id.content_main, searchQuesFragment, searchQuesFragment.getTag()).commit();

        }



        DrawerLayout drawer = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}
