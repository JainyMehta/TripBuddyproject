package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Button signup,btn_login;
    FrameLayout f1;
    RelativeLayout login;
    AnimationDrawable animationDrawable;
    ConstraintLayout main;
    EditText username,passwd;
    DatabaseAccess sqliteHelper;
    TextInputLayout textInputLayoutUser,textInputLayoutPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.signup);
        f1 = findViewById(R.id.F1);
        login = findViewById(R.id.login);
        main = findViewById(R.id.main);
        btn_login = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        passwd = findViewById(R.id.passwd);
        textInputLayoutUser = findViewById(R.id.textInputLayoutUser);
        textInputLayoutPass = findViewById(R.id.textInputLayoutPass);

        sqliteHelper = DatabaseAccess.getInstance(getApplicationContext());

        animationDrawable =(AnimationDrawable)main.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setVisibility(View.GONE);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.F1,new SignUpActivity());
                ft.commit();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//

                if (validate()) {

                    //Get values from EditText fields
                    String UserName = username.getText().toString();
                    String Password = passwd.getText().toString();


//
//                   // System.out.println("Hello");
//                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, UserName,null, Password));
                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(btn_login, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        Intent i = new Intent(getApplicationContext(),MainOptions.class);
                        startActivity(i);
                        finish();

                    } else {

                        //User Logged in Failed
                        Snackbar.make(btn_login, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = username.getText().toString();
        String Password = passwd.getText().toString();

        //Handling validation for Email field
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
//            valid = false;
//            textInputLayoutEmail.setError("Please enter valid email!");
//        } else {
//            valid = true;
//            textInputLayoutEmail.setError(null);
//        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPass.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPass.setError(null);
            } else {
                valid = false;
                textInputLayoutPass.setError("Password is to short!");
            }
        }

        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUser.setError("Please enter valid Username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                textInputLayoutUser.setError(null);
            } else {
                valid = false;
                textInputLayoutUser.setError("Username is to short!");
            }
        }

        return valid;
    }
}