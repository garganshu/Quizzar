package zersey.com.zersey;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.RadialGradient;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zersey.com.zersey.DataBase.Database_Adapter;

public class Quiz_One extends AppCompatActivity {

    //Database constraints-
    Database_Adapter dbhelper;
    SQLiteDatabase sql_db;
    Cursor mCursor2;

    //checking constraints-
    int s1,s2,s3,s4,s5;
    RadioButton r1,r2,r3,r4,r5;
    String o1 = "a",o2 = "a",o3 = "a",o4 = "a",o5 = "a";
    int r = 0,w = 0;

    AlertDialog.Builder builder;
    RadioGroup rq1,rq2,rq3,rq4,rq5;
    TextView tq1,tq2,tq3,tq4,tq5;
    List<String> arrayList;
    String sol1,sol2,sol3,sol4,sol5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__one);
        arrayList = new ArrayList<String>();
        builder = new AlertDialog.Builder(this);


        rq1 = (RadioGroup) findViewById(R.id.RGQ1_q1);
        rq2 = (RadioGroup) findViewById(R.id.RGQ1_q2);
        rq3 = (RadioGroup) findViewById(R.id.RGQ1_q3);
        rq4 = (RadioGroup) findViewById(R.id.RGQ1_q4);
        rq5 = (RadioGroup) findViewById(R.id.RGQ1_q5);

        tq1 = (TextView) findViewById(R.id.Quiz1_ques1);
        tq2 = (TextView) findViewById(R.id.Quiz1_ques2);
        tq3 = (TextView) findViewById(R.id.Quiz1_ques3);
        tq4 = (TextView) findViewById(R.id.Quiz1_ques4);
        tq5 = (TextView) findViewById(R.id.Quiz1_ques5);

        database_fetch();

        //questions set
        tq1.setText(arrayList.get(0));
        tq2.setText(arrayList.get(6));
        tq3.setText(arrayList.get(12));
        tq4.setText(arrayList.get(18));
        tq5.setText(arrayList.get(24));

        //options set into radiobuttons
        for (int i = 0; i < rq1.getChildCount(); i++) {
            ((RadioButton) rq1.getChildAt(i)).setText(arrayList.get(i+1));
        }

        for (int i = 0; i < rq2.getChildCount(); i++) {
            ((RadioButton) rq2.getChildAt(i)).setText(arrayList.get(i+7));
        }

        for (int i = 0; i < rq3.getChildCount(); i++) {
            ((RadioButton) rq3.getChildAt(i)).setText(arrayList.get(i+13));
        }

        for (int i = 0; i < rq4.getChildCount(); i++) {
            ((RadioButton) rq4.getChildAt(i)).setText(arrayList.get(i+19));
        }

        for (int i = 0; i < rq5.getChildCount(); i++) {
            ((RadioButton) rq5.getChildAt(i)).setText(arrayList.get(i+25));
        }

        //fetching solutions from db
        sol1 = arrayList.get(5);
        sol2 = arrayList.get(11);
        sol3 = arrayList.get(17);
        sol4 = arrayList.get(23);
        sol5 = arrayList.get(29);



    }

    public void submit(View view){

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
        s4 = rq4.getCheckedRadioButtonId();
        if(s4>-1){
            r4 = (RadioButton)findViewById(s4);
            o4 = r4.getText().toString();
        }
        s5 = rq5.getCheckedRadioButtonId();
        if(s5>-1){
            r5 = (RadioButton)findViewById(s5);
            o5 = r5.getText().toString();
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
        if(o4.contentEquals(sol4)){
            r++;
        }else{
            w++;
        }
        if(o5.contentEquals(sol5)){
            r++;
        }else{
            w++;
        }


    }

    public void database_fetch(){
        dbhelper=new Database_Adapter(this);
        sql_db= dbhelper.getWritableDatabase();

        mCursor2 = sql_db.rawQuery("SELECT * FROM "
                + dbhelper.TABLE_Quiz, null);

        //Log.d("check","cccc"+mCursor2);

        if (mCursor2.moveToFirst()) {
            do {

                //Log.d("checkcheck","cccc"+mCursor2);

                String  fques = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Ques));
                //Log.d("quessss",":"+fques);

                String  fopt1 = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Opt1));
                //Log.d("quesopt1",":"+fopt1);

                String  fopt2 = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Opt2));
                //Log.d("quesopt2",":"+fopt2);

                String  fopt3 = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Opt3));
                //Log.d("queopt3",":"+fopt3);

                String  fopt4 = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Opt4));
                //Log.d("quesopt4",":"+fopt4);

                String  fsol = mCursor2.getString(mCursor2
                        .getColumnIndex(dbhelper.KEY_Solution));
                //Log.d("quessol",":"+fsol);

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

