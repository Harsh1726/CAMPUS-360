package com.example.mitujjainofficial;

import androidx.annotation.NonNull;
import  androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {

    private EditText inputEmail1,inputFullName,inputUsername,inputPassword1;
    private TextView vToggleVisibility;
    Button btnSignup;
    private static final String TAG ="RegistrationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView textView = findViewById(R.id.loginRedirect ) ;
        TextView login = (TextView) findViewById(R.id.loginRedirect);
        inputEmail1 = findViewById(R.id.inputEmail1);
        inputFullName = findViewById(R.id.inputFullName);
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword1 = findViewById(R.id.inputPassword1);
        vToggleVisibility = (TextView) findViewById(R.id.registerToggleVisibility);
        vToggleVisibility.setVisibility(View.GONE);
        inputPassword1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        inputPassword1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(inputPassword1.getText().length() > 0) {
                    vToggleVisibility.setVisibility(View.VISIBLE);
                }
                else {
                    vToggleVisibility.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        vToggleVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vToggleVisibility.getText() == "SHOW") {
                    vToggleVisibility.setText("HIDE");
                    inputPassword1.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    inputPassword1.setSelection(inputPassword1.length());
                }
                else {
                    vToggleVisibility.setText("SHOW");
                    inputPassword1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    inputPassword1.setSelection(inputPassword1.length());
                }
            }
        });


        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });


        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        SpannableString content = new SpannableString( "Log In" ) ;
        content.setSpan( new UnderlineSpan() , 0 , content.length() , 0 ) ;
        textView.setText(content) ;
    }
    private void checkCredentials() {
        String email = inputEmail1.getText().toString();
        String fullname = inputFullName.getText().toString();
        String username = inputUsername.getText().toString();
        String password = inputPassword1.getText().toString();

        if (email.isEmpty() || !email.contains("@gmail.com"))
        {
            showError(inputEmail1,"E-mail is not valid");
        }
        else if (fullname.isEmpty() || fullname.length() >20)
        {
            showError(inputFullName,"Full Name should not be greater than 20 characters");
        }
        else if (username.isEmpty() || username.length() > 7)
        {
            showError(inputUsername,"Your username should not be greater than 7 characters");
        }
        else if (password.isEmpty() || password.length() < 8)
        {
            showError(inputPassword1,"Password must be greater than 8 characters");
        }
        else
        {
            registerUser(email, fullname, username, password);
        }


    }

    private void registerUser(String email, String fullname, String username, String password) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        // Create User Profile

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistrationActivity.this,
                       new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            // Update Display Name Of User
                            UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(fullname).build();
                            firebaseUser.updateProfile(profileChangeRequest);

                            // Enter User Data into the Firebase RealTime Database.
                            ReadWriteUserDetails writeUserDetails = new ReadWriteUserDetails(username);

                            // Extracting User reference from Database for "Registration Of Users"
                            DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registration Of Users");

                            referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        // Send  Verification E-mail
                                        firebaseUser.sendEmailVerification();

                                        Toast.makeText(RegistrationActivity.this, "Registered Successfully !", Toast.LENGTH_LONG).show();

                                        //Open User Profile after successful registration
                                        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);

                                        // To Prevent User From Returning back to Register Activity on pressing back button after registration
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish(); // to close Registration Activity
                                    } else {
                                        Toast.makeText(RegistrationActivity.this, "Registration Failed. Please Try Again", Toast.LENGTH_LONG).show();

                                    }




                                }
                            });


                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                inputPassword1.setError("Your password is too weak. Kindly use a combination of alaphabets, numbers and special symbols.");
                                inputPassword1.requestFocus();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                inputEmail1.setError("Your Email is invalid or already in use. Please re-enter.");
                                inputEmail1.requestFocus();
                            } catch (FirebaseAuthUserCollisionException e){
                                inputEmail1.setError("User is already registered with this Email. Use another Email.");
                                inputEmail1.requestFocus();
                            } catch (Exception e) {
                                Log.e(TAG,e.getMessage());
                                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
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