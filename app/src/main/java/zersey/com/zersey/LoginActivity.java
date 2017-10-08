package zersey.com.zersey;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {


    EditText Login_email,Login_password;
    String LoginEmail,LoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login_email = (EditText) findViewById(R.id.Login_email);
        Login_password = (EditText) findViewById(R.id.Login_password);





    }

    public void login(View view){
        //login check for test user.
        LoginEmail = Login_email.getText().toString();
        LoginPassword = Login_password.getText().toString();

        if((LoginEmail.contentEquals("test")) && LoginPassword.contentEquals("test")){
            Toast.makeText(this, "!!Welcome Back "+LoginEmail,
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this , Home_Navigation.class);
            startActivity(i);

        }else{
            Toast.makeText(this, "Wrong Email or Password",
                    Toast.LENGTH_SHORT).show();
        }


    }

    public void forget(View view){
        Toast.makeText(this, "Coming Soon",
                Toast.LENGTH_SHORT).show();
    }

    public void signed(View view){
        Toast.makeText(this, "Redirecting to SignUp Window",
                Toast.LENGTH_SHORT).show();
        Intent i = new Intent(LoginActivity.this , SignUp.class);
        startActivity(i);

    }


}
