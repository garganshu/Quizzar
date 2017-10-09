package zersey.com.zersey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zersey.com.zersey.DataBase.Database_Adapter;

public class Quiz_Five extends AppCompatActivity {

    //Database constraints-
    Database_Adapter dbhelper;
    SQLiteDatabase sql_db;
    Cursor mCursor2;

    //checking constraints-
    int s1,s2,s3;
    RadioButton r1,r2,r3;
    String o1 = "aee",o2 = "aee",o3 = "aee";
    int r = 0,w = 0;

    AlertDialog.Builder builder;
    RadioGroup rq1,rq2,rq3;
    TextView tq1,tq2,tq3;
    List<String> arrayList;
    String sol1,sol2,sol3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__five);
        setTitle("QUIZ FIVE");
        arrayList = new ArrayList<String>();
        builder = new AlertDialog.Builder(this);


        rq1 = (RadioGroup) findViewById(R.id.RGQ5_q1);
        rq2 = (RadioGroup) findViewById(R.id.RGQ5_q2);
        rq3 = (RadioGroup) findViewById(R.id.RGQ5_q3);

        tq1 = (TextView) findViewById(R.id.Quiz5_ques1);
        tq2 = (TextView) findViewById(R.id.Quiz5_ques2);
        tq3 = (TextView) findViewById(R.id.Quiz5_ques3);


        database_fetch();

        //questions set
        if(arrayList.size()>0) {

            tq1.setText(arrayList.get(0));
            tq2.setText(arrayList.get(6));
            tq3.setText(arrayList.get(12));

            //options set into radiobuttons
            for (int i = 0; i < rq1.getChildCount(); i++) {
                ((RadioButton) rq1.getChildAt(i)).setText(arrayList.get(i + 1));
            }

            for (int i = 0; i < rq2.getChildCount(); i++) {
                ((RadioButton) rq2.getChildAt(i)).setText(arrayList.get(i + 7));
            }

            for (int i = 0; i < rq3.getChildCount(); i++) {
                ((RadioButton) rq3.getChildAt(i)).setText(arrayList.get(i + 13));
            }


            //fetching solutions from db
            sol1 = arrayList.get(5);
            sol2 = arrayList.get(11);
            sol3 = arrayList.get(17);
        }else{
            Toast.makeText(getApplicationContext(), "Create Quiz First",
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Home_Navigation.class);
            startActivity(i);
        }


    }

    public void submit_quiz_five(View view){

        get_check();
        builder.setMessage("Attempted : "+(r+w)+"\nCorrect Answers : "+r+"\nWrong Answers : "+w )
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Your Scorecard");
        alert.show();

    }

    public void get_check(){

        s1 = rq1.getCheckedRadioButtonId();
        if(s1>-1){
            r1 = (RadioButton)findViewById(s1);
            o1 = r1.getText().toString();
        }
        s2 = rq2.getCheckedRadioButtonId();
        if(s2>-1){
            r2 = (RadioButton)findViewById(s2);
            o2 = r2.getText().toString();
        }
        s3 = rq3.getCheckedRadioButtonId();
        if(s3>-1){
            r3 = (RadioButton)findViewById(s3);
            o3 = r3.getText().toString();
        }


        //Log.d("id1",String.valueOf(s1));

        if(o1.contentEquals(sol1)){
            r++;
        }else{
            w++;
        }
        if(o2.contentEquals(sol2)){
            r++;
        }else{
            w++;
        }
        if(o3.contentEquals(sol3)){
            r++;
        }else{
            w++;
        }


    }

    public void database_fetch(){
        dbhelper=new Database_Adapter(this);
        sql_db= dbhelper.getWritableDatabase();

        mCursor2 = sql_db.rawQuery("SELECT * FROM "
                + dbhelper.TABLE_Quiz + " WHERE " + dbhelper.KEY_Type + " = 'Quiz5'", null);

        Log.d("check","cccc"+mCursor2);

        if (mCursor2.moveToFirst()) {
            do {

                Log.d("checkcheck","cccc"+mCursor2);

                String  fques = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Ques));
                Log.d("quessss",":"+fques);

                String  fopt1 = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Opt1));
                Log.d("quesopt1",":"+fopt1);

                String  fopt2 = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Opt2));
                Log.d("quesopt2",":"+fopt2);

                String  fopt3 = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Opt3));
                Log.d("queopt3",":"+fopt3);

                String  fopt4 = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Opt4));
                Log.d("quesopt4",":"+fopt4);

                String  fsol = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Solution));
                Log.d("quessol",":"+fsol);

                arrayList.add(fques);
                arrayList.add(fopt1);
                arrayList.add(fopt2);
                arrayList.add(fopt3);
                arrayList.add(fopt4);
                arrayList.add(fsol);


            } while (mCursor2.moveToNext());



        }
        sql_db.close();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Submit the Quiz before you Leave :)")
                .setCancelable(false)
                .setPositiveButton("Okay, Got it", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Do you want to Quit ?");
        alert.show();
    }

}
