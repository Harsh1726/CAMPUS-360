package com.example.mitujjainofficial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.text.method.LinkMovementMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail,inputPassword,inputUsernameOnLogin;
    private TextView tToggleVisibility,forForgotPassword;
    Button btnLogin;
    private FirebaseAuth authProfile;
    private static final String TAG = "LoginActivity";

    private ImageView btnGoogle, btnGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView register = (TextView) findViewById(R.id.registerRedirect);
        TextView textView = findViewById(R.id.registerRedirect ) ;
        inputEmail = findViewById(R.id.inputEmail);
        inputUsernameOnLogin = findViewById(R.id.inputUsernameOnLogin);
        inputPassword = findViewById(R.id.inputPassword);
        tToggleVisibility = (TextView) findViewById(R.id.loginToggleVisibility);
        tToggleVisibility.setVisibility(View.GONE);
        inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        forForgotPassword = (TextView) findViewById(R.id.forgotPassword);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnGithub =findViewById(R.id.btnGithub);


        authProfile = FirebaseAuth.getInstance();


        // Login User
        btnLogin = findViewById(R.id.btnLogin);



        inputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(inputPassword.getText().length() > 0) {
                    tToggleVisibility.setVisibility(View.VISIBLE);
                }
                else {
                    tToggleVisibility.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tToggleVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tToggleVisibility.getText() == "SHOW") {
                    tToggleVisibility.setText("HIDE");
                    inputPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    inputPassword.setSelection(inputPassword.length());
                }
                else {
                    tToggleVisibility.setText("SHOW");
                    inputPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    inputPassword.setSelection(inputPassword.length());
                }
            }
        });


        // Reset Password

        forForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "You can reset your password now!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });




        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });


        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, GoogleSignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });


        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, GithubAuthActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);


            }
        });


        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        SpannableString content = new SpannableString( "Sign Up" ) ;
        content.setSpan( new UnderlineSpan() , 0 , content.length() , 0 ) ;
        textView.setText(content) ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authProfile.getCurrentUser() != null){
            Toast.makeText(LoginActivity.this, "Already Logged In !", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CurrentUserActivity.class));
            finish();
        }
    }

    private void checkCredentials() {
        String email = inputEmail.getText().toString();
        String username = inputUsernameOnLogin.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@gmail.com"))
        {
            showError(inputEmail,"E-mail is not valid");
        }

        else if (username.isEmpty() || username.length() > 7)
        {
            showError(inputUsernameOnLogin,"Your username is not valid");
        }

        else if (password.isEmpty() || password.length() < 8)
        {
            showError(inputPassword,"Password must be greater than 8 characters");
        }
        else
        {

            loginUser(email, username, password);

        }


    }

    private void loginUser(String email, String username, String password) {

        authProfile.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Logged In Successfully...", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    try {
                        throw task.getException();
                    } catch(FirebaseAuthInvalidUserException e) {
                        inputEmail.setError("User does not exists or no longer valid. PLease register again");
                        inputEmail.requestFocus();
                    } catch (FirebaseAuthInvalidCredentialsException e) {

                        inputUsernameOnLogin.setError("Invalid credentials. Kindly, check and re-enter");
                        inputUsernameOnLogin.requestFocus();

                    } catch (Exception e) {
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }




    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}