package com.example.campybehappy.Controller.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.campybehappy.Global.Constants;
import com.example.campybehappy.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

import kotlin.reflect.KFunction;

public class RegisterActivity extends AppCompatActivity {

    Button BtnGoLogin,BtnValidRegister;
    TextInputEditText fullnameInput, usernameInput, emailInput, passwdInput;
    private static final Pattern PASSWORED_Pattern = Pattern.compile("^" + ".{4,}" +"$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fullnameInput = findViewById(R.id.name_input);
        usernameInput = findViewById(R.id.username_input);
        emailInput = findViewById(R.id.email_input);
        passwdInput = findViewById(R.id.password_input);
        BtnValidRegister = findViewById(R.id.btn_ValidRegister);

        BtnGoLogin = findViewById(R.id.btn_goLogIn);

        //Listeners
        BtnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        BtnValidRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInputs()) {

                   if((register(usernameInput.getText().toString(),passwdInput.getText().toString())) && (register(emailInput.getText().toString(),passwdInput.getText().toString())))
                   {
                       redirectTO();
                   }
                }
            }
        });

    }

    public boolean chekFullName(String fullname){
        if (fullname.length() <3 || fullname.isEmpty() || fullname==null ) {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean chekUserName(String UserName){
        if (UserName.length() <4 || UserName.isEmpty()) {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean chekEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public boolean chekPasswored(String passwored){
        if (passwored.isEmpty()) {
            return false;
        } else if (!PASSWORED_Pattern.matcher(passwored).matches()) {
            return false;
        } else {
            return true;
        }
    }
    public boolean validateInputs() {
        if (chekFullName(fullnameInput.getText().toString())== false) {
            fullnameInput.setError(getString(R.string.chekFullName));
            fullnameInput.requestFocus();
            return false;
        }
        if (chekUserName(usernameInput.getText().toString())== false) {
            usernameInput.setError(getString(R.string.chekUserName));
            usernameInput.requestFocus();
            return false;
        }
        if (chekEmail(emailInput.getText().toString())== false) {
            emailInput.setError(getString(R.string.chekEmail));
            emailInput.requestFocus();
            return false;
        }
        if (chekPasswored(passwdInput.getText().toString())== false) {
            passwdInput.setError(getString(R.string.chekPasswored));
            passwdInput.requestFocus();
            return false;
        }

        return true;
    }
    private boolean register(String email, String passwored) {
        SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(email,passwored);
        if( editor.commit())
        {
           return true ;
        }
        else
        {
            return false;
        }
    }
public void redirectTO()
{
    startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
    finish();

}

}
