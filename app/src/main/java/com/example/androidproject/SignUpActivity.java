package com.example.androidproject;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends Fragment {

    EditText edt_username, edt_passwd, edt_email, edt_confirmpasswd;
    Button signup;
    ConstraintLayout layout;
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    DatabaseAccess sqliteHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sign_up, container, false);

//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);

        edt_email = view.findViewById(R.id.edt_email);
        edt_passwd = view.findViewById(R.id.edt_passwd);
        edt_username = view.findViewById(R.id.edt_username);
        textInputLayoutEmail = view.findViewById(R.id.textInputLayoutE);
        textInputLayoutPassword = view.findViewById(R.id.textInputLayoutP);
        textInputLayoutUserName = view.findViewById(R.id.textInputLayoutU);
        signup = view.findViewById(R.id.btn_signup);
        sqliteHelper = DatabaseAccess.getInstance(getActivity().getApplicationContext());


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = edt_username.getText().toString();
                    String Email = edt_email.getText().toString();
                    String Password = edt_passwd.getText().toString();

                    //Check in the database is there any user associated with  this email
                    if (!sqliteHelper.isEmailExists(Email)) {

                        //Email does not exist now add new user to database
                        sqliteHelper.addUser(new User(null, UserName, Email, Password));
                        Snackbar.make(signup, "User created successfully!", Snackbar.LENGTH_LONG).show();

                        Intent a = new Intent(getContext(),MainOptions.class);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    } else {

                        //Email exists with email input provided so show error user already exist
                        Snackbar.make(signup, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });
        return view;
    }

    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = edt_username.getText().toString();
        String Email = edt_email.getText().toString();
        String Password = edt_passwd.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                textInputLayoutUserName.setError(null);
            } else {
                valid = false;
                textInputLayoutUserName.setError("Username is to short!");
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }


        return valid;
    }

}