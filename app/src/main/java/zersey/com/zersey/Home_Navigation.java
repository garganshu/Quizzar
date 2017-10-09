package zersey.com.zersey;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home_Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean fabExpanded = false;
    LinearLayout layoutFabCreate;

    static int quiz_count = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Zersey Quizzer");
        setContentView(R.layout.activity_home__navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //layoutFabCreate = (LinearLayout) fab.findViewById(R.id.layoutFabCreate);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (fabExpanded) {
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }*/
                Toast.makeText(getApplicationContext(), "Quiz Creation Window",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Create_Quiz.class);
                startActivity(i);
            }

        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CardView cardView2 = (CardView) findViewById(R.id.CardView2);
        CardView cardView3 = (CardView) findViewById(R.id.CardView3);
        CardView cardView4 = (CardView) findViewById(R.id.CardView4);
        CardView cardView5 = (CardView) findViewById(R.id.CardView5);

        get_count();
        if(quiz_count == 2){
            cardView2.setVisibility(View.VISIBLE);
            put_count();
        }else if(quiz_count == 3){
            cardView2.setVisibility(View.VISIBLE);
            cardView3.setVisibility(View.VISIBLE);
            put_count();
        }else if(quiz_count == 4){
            cardView2.setVisibility(View.VISIBLE);
            cardView3.setVisibility(View.VISIBLE);
            cardView4.setVisibility(View.VISIBLE);
            put_count();
        }else if(quiz_count == 5){
            cardView2.setVisibility(View.VISIBLE);
            cardView3.setVisibility(View.VISIBLE);
            cardView4.setVisibility(View.VISIBLE);
            cardView5.setVisibility(View.VISIBLE);
            put_count();
        }

    }

    //quiz one redirection
    public void Quiz_ONE(View view){
        Toast.makeText(getApplicationContext(), "Quiz One Loading!!",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Quiz_One.class);
        startActivity(i);
    }

    public void Quiz_THREE(View view){
        Toast.makeText(getApplicationContext(), "Quiz Three Loading!!",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Quiz_Three.class);
        startActivity(i);
    }

    public void Quiz_TWO(View view){
        Toast.makeText(getApplicationContext(), "Quiz Two Loading!!",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Quiz_Two.class);
        startActivity(i);
    }

    public void Quiz_FOUR(View view){
        Toast.makeText(getApplicationContext(), "Quiz Four Loading!!",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Quiz_Four.class);
        startActivity(i);
    }

    public void Quiz_FIVE(View view){
        Toast.makeText(getApplicationContext(), "Quiz Five Loading!!",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), Quiz_Five.class);
        startActivity(i);
    }


    /*//closes fab submenu of quiz create
    private void closeSubMenusFab(){
        layoutFabCreate.setVisibility(View.INVISIBLE);
        fabExpanded = false;
    }

    //Opens FAB submenu of quiz create
    private void openSubMenusFab(){
        layoutFabCreate.setVisibility(View.VISIBLE);
        fabExpanded = true;
    }

    //create quiz
    public void create(View view){
        Toast.makeText(this, "Quiz Creation Window",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Create_Quiz.class);
        startActivity(i);


    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home__navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void get_count(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("CountState", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        quiz_count=pref.getInt("count",1);

        Log.d("checkvaluegetcount :",""+quiz_count);


    }
    public void put_count(){
        SharedPreferences sp = getApplicationContext().getSharedPreferences("CountState", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count", quiz_count);
        editor.commit();

        Log.d("checkvalueputcount :",""+quiz_count);
    }

}
