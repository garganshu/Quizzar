package zersey.com.zersey;

import android.app.Activity;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import zersey.com.zersey.Others.Constants;

public class SignUp extends Activity {

    EditText mEditText_name , mEditText_password , mEditText_confirmPass , mEditText_emailid  ;
    String mName , mPass, mConfirmPass , mEmailId  , mSex;
    RadioGroup mradioGroup_sex ;
    RadioButton mradioButton_male  ,  mradioButton_female ,  mradioButton_others ;
    Button mButton_Register ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mButton_Register = (Button)findViewById(R.id.Sinup_RegisterButton);
        mEditText_name = (EditText)findViewById(R.id.Sinup_Name);
        mEditText_emailid =(EditText)findViewById(R.id.Sinup_email);
        mEditText_password = (EditText)findViewById(R.id.Sinup_password);
        mEditText_confirmPass = (EditText)findViewById(R.id.Sinup_confirm_password);
        mradioGroup_sex = (RadioGroup)findViewById(R.id.Sinup_sex_radio_group);
        mradioButton_female = (RadioButton)findViewById(R.id.Sinup_radiobutton_female);
        mradioButton_male = (RadioButton)findViewById(R.id.Sinup_radiobutton_male);
        mradioButton_others = (RadioButton)findViewById(R.id.Sinup_radiobutton_other) ;

    }

    public void register(View view){
        set_Values_To_Paramters_And_Post_Over_Server();
        Toast.makeText(this, "Successfully Registered",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Home_Navigation.class);
        startActivity(i);

    }


    private void set_Values_To_Paramters_And_Post_Over_Server() {
        mName = mEditText_name.getText().toString();
        mEmailId = mEditText_emailid.getText().toString();
        mPass = mEditText_password.getText().toString();
        mConfirmPass = mEditText_confirmPass.getText().toString();

        int currentSexId  = mradioGroup_sex.getCheckedRadioButtonId();

        if(currentSexId == mradioButton_male.getId()) {
            mSex = Constants.Sinup_SEX_MALE;
        }
        else if(currentSexId == mradioButton_female.getId())
        {
            mSex = Constants.Sinup_SEX_FEMALE;
        }
        else if(currentSexId == mradioButton_others.getId()){
            mSex = Constants.Sinup_SEX_OTHERS;
        }
        else {
            mSex = "NotFound";
        }

        Log.i("check" , "CHECK_PARAMS->" + check_params());

        if(check_params()){
            //If the parameters are right - then posted to the server using appropriate api


        }

    }


    //checking
    private boolean check_params() {
        if(mName!=null && mEmailId!=null && mPass!=null && mConfirmPass!=null && mPass.equals(mConfirmPass) && mPass.length() >= Constants.Sinup_MIN_PASSWORD_LENGTH ){
            if(mSex.equals(Constants.Sinup_SEX_MALE) || mSex.equals(Constants.Sinup_SEX_FEMALE) || mSex.equals(Constants.Sinup_SEX_OTHERS)){

                return true ;
            }
        }

        return false;
    }

}
