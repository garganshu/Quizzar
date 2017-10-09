package zersey.com.zersey;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import zersey.com.zersey.DataBase.Database_Adapter;

public class Create_Quiz extends AppCompatActivity {

    EditText eq1,eq1o1,eq1o2,eq1o3,eq1o4,eq1sol;
    EditText eq2,eq2o1,eq2o2,eq2o3,eq2o4,eq2sol;
    EditText eq3,eq3o1,eq3o2,eq3o3,eq3o4,eq3sol;

    String sq1,sq1o1,sq1o2,sq1o3,sq1o4,sq1sol;
    String sq2,sq2o1,sq2o2,sq2o3,sq2o4,sq2sol;
    String sq3,sq3o1,sq3o2,sq3o3,sq3o4,sq3sol;

    AlertDialog.Builder builder;
    Database_Adapter dbhelper;
    SQLiteDatabase sql_db;
    static int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__quiz);
        setTitle("CREATE QUIZ");
        builder = new AlertDialog.Builder(this);

        eq1 = (EditText) findViewById(R.id.Quiz2_ques1);
        eq2 = (EditText) findViewById(R.id.Quiz2_ques2);
        eq3 = (EditText) findViewById(R.id.Quiz2_ques3);

        eq1o1 = (EditText) findViewById(R.id.Quiz2_q1opt1);
        eq1o2 = (EditText) findViewById(R.id.Quiz2_q1opt2);
        eq1o3 = (EditText) findViewById(R.id.Quiz2_q1opt3);
        eq1o4 = (EditText) findViewById(R.id.Quiz2_q1opt4);

        eq2o1 = (EditText) findViewById(R.id.Quiz2_q2opt1);
        eq2o2 = (EditText) findViewById(R.id.Quiz2_q2opt2);
        eq2o3 = (EditText) findViewById(R.id.Quiz2_q2opt3);
        eq2o4 = (EditText) findViewById(R.id.Quiz2_q2opt4);

        eq3o1 = (EditText) findViewById(R.id.Quiz2_q3opt1);
        eq3o2 = (EditText) findViewById(R.id.Quiz2_q3opt2);
        eq3o3 = (EditText) findViewById(R.id.Quiz2_q3opt3);
        eq3o4 = (EditText) findViewById(R.id.Quiz2_q3opt4);

        eq1sol = (EditText) findViewById(R.id.Quiz2_q1sol);
        eq2sol = (EditText) findViewById(R.id.Quiz2_q2sol);
        eq3sol = (EditText) findViewById(R.id.Quiz2_q3sol);

        if(count>1){
            get_count();
        }else{
            count = 1;
        }




    }
    public void quiz_submit(View view){

        sq1 = eq1.getText().toString();
        sq2 = eq2.getText().toString();
        sq3 = eq3.getText().toString();

        sq1o1 = eq1o1.getText().toString();
        sq1o2 = eq1o2.getText().toString();
        sq1o3 = eq1o3.getText().toString();
        sq1o4 = eq1o4.getText().toString();

        sq2o1 = eq2o1.getText().toString();
        sq2o2 = eq2o2.getText().toString();
        sq2o3 = eq2o3.getText().toString();
        sq2o4 = eq2o4.getText().toString();

        sq3o1 = eq3o1.getText().toString();
        sq3o2 = eq3o2.getText().toString();
        sq3o3 = eq3o3.getText().toString();
        sq3o4 = eq3o4.getText().toString();

        sq1sol = eq1sol.getText().toString();
        sq2sol = eq2sol.getText().toString();
        sq3sol = eq3sol.getText().toString();

        if (isEmptyField(eq1)) return;

        if (isEmptyField(eq1o1)) return;
        if (isEmptyField(eq1o2)) return;
        if (isEmptyField(eq1o3)) return;
        if (isEmptyField(eq1o4)) return;

        if (isEmptyField(eq1sol)) return;


        if (isEmptyField(eq2)) return;

        if (isEmptyField(eq2o1)) return;
        if (isEmptyField(eq2o2)) return;
        if (isEmptyField(eq2o3)) return;
        if (isEmptyField(eq2o4)) return;

        if (isEmptyField(eq2sol)) return;


        if (isEmptyField(eq3)) return;

        if (isEmptyField(eq3o1)) return;
        if (isEmptyField(eq3o2)) return;
        if (isEmptyField(eq3o3)) return;
        if (isEmptyField(eq3o4)) return;

        if (isEmptyField(eq3sol)) return;

        //db connectivity-
        count++;
        DBValues(sq1,sq1o1,sq1o2,sq1o3,sq1o4,sq1sol,"Quiz"+count);
        DBValues(sq2,sq2o1,sq2o2,sq2o3,sq2o4,sq2sol,"Quiz"+count);
        DBValues(sq3,sq3o1,sq3o2,sq3o3,sq3o4,sq3sol,"Quiz"+count);



        builder.setMessage("Sucessfully Submitted")
                .setCancelable(false)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(), Home_Navigation.class);
                        startActivity(i);

                    }
                });

        put_count();
        Log.d("check count"," :"+count);
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Quiz"+count);
        alert.show();


    }

    //to check empty fields
    private boolean isEmptyField (EditText editText){
        boolean result = editText.getText().toString().length() <= 0;
        if (result)
            editText.setError("Required Field");
        return result;
    }

    public void DBValues(String ques,String opt1,String opt2,String opt3,String opt4, String sol,String type) {

        dbhelper = new Database_Adapter(this);
        sql_db = dbhelper.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(dbhelper.KEY_Ques, ques);
        value.put(dbhelper.KEY_Opt1, opt1);
        value.put(dbhelper.KEY_Opt2, opt2);
        value.put(dbhelper.KEY_Opt3, opt3);
        value.put(dbhelper.KEY_Opt4, opt4);
        value.put(dbhelper.KEY_Solution, sol);
        value.put(dbhelper.KEY_Type, type);



        sql_db.insert(dbhelper.TABLE_Quiz, "1", value);

        Toast.makeText(getApplicationContext(), "Store Successfull ",
                Toast.LENGTH_LONG).show();

    }

    public void put_count(){
        SharedPreferences sp = getApplicationContext().getSharedPreferences("CountState", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count", count);
        editor.commit();
    }

    public void get_count(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("CountState", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        count=pref.getInt("count",1);

        Log.d("check count at create :",""+count);


    }


}
