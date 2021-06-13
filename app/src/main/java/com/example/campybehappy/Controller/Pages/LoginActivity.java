package com.example.campybehappy.Controller.Pages;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.campybehappy.Global.Constants;
import com.example.campybehappy.Model.User;
import com.example.campybehappy.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    Button callSignUp, btnValidLogin;
    ImageView logo_img;
    TextView logo_title, logo_subtitle;
    TextInputEditText usernameInput2, passwdInput2;
    private static final Pattern PASSWORED_Pattern = Pattern.compile("^" + ".{4,}" +"$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        callSignUp = findViewById(R.id.btn_goSignUp);
        logo_img = findViewById(R.id.app_logo);
        logo_title = findViewById(R.id.logo_name);
        logo_subtitle = findViewById(R.id.slogan_name);

        usernameInput2 = findViewById(R.id.username_input2);
        passwdInput2 = findViewById(R.id.password_input2);
        btnValidLogin = findViewById(R.id.btn_ValidLogin);

        /* Onclick listener */
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goToRegister();
            }
        });
        btnValidLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEmail()== true && validatePassword()== true) {

                    loginUser();
                }
            }
        });
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

    //return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }
    public boolean chekpasswoored(String passwored){
        if(PASSWORED_Pattern.matcher(passwored).matches())
        {
            return true;
        }
        else return false;
    }
    public boolean validateEmail() {
        String emailInput = usernameInput2.getText().toString().trim();
        if (emailInput.isEmpty()) {
            usernameInput2.setError(getString(R.string.errorpassw));
            usernameInput2.requestFocus();
            return false;
        } else if (chekEmail(emailInput)== false) {
            usernameInput2.setError(getString(R.string.erreremail));
            usernameInput2.requestFocus();
            return false;
        } else {
            usernameInput2.setError(null);
            return true;
        }
    }
    public boolean validatePassword(){
        String passwordInput = passwdInput2.getText().toString().trim();
        if (passwordInput.isEmpty()) {
            passwdInput2.setError(getString(R.string.errorpassw));

            return false;//Le champ ne peut pas être vide
        } else if (chekpasswoored(passwordInput)==false) {
            passwdInput2.setError(getString(R.string.errorpass2));
            return false;
        } else {
            passwdInput2.setError(null);
            return true;
        }
    }
    private void goToRegister() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    public boolean existeEmail(String email) {
        SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        if(preferences.contains(email) || email != null){
            return  true;
        }
        else
        {
            return  false ;
        }
    }
    public boolean existePasswored(String passwored,String email) {
        String passw="";
        SharedPreferences preferences = getSharedPreferences(Constants.MY_PREFS, MODE_PRIVATE);
        passw =preferences.getString(email, "notfound");
        return passw.equals(passwored);
    }
    public void loginUser() {
        String Adres = usernameInput2.getText().toString();
        String pass = passwdInput2.getText().toString();
        User user1= new User("jbeli","jbeli","Jbeli@gmail.com","14265401");
        create(user1);
        if(existeEmail(Adres)== true)
        {
            if(existePasswored(pass,Adres) == true){
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.MY_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Constants.ISCONNECTED, true);
                editor.apply();
                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
            }
            else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                alertDialog.setTitle(":/ Erreur !!!!");
                alertDialog.setMessage("Mot Passe Or/Et Email incorrect");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
        else
        {
            AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
            alertDialog.setTitle(":/ Erreur !!!!");
            alertDialog.setMessage("Mot Passe Or/Et Email incorrect");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    public void create(User user)
    {
        ArrayList<User> userarray = new ArrayList<User>();
        userarray.add(user);
    }
}