package com.example.mitujjainofficial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mitujjainofficial.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    FirebaseAuth authProfile = FirebaseAuth.getInstance();
    FirebaseUser user = authProfile.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightskyblue)));


        FirebaseMessaging.getInstance().subscribeToTopic("Notification");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

       navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.navigation_collegeofficialworks:

                Intent intent = new Intent(MainActivity.this, CollegeNavigationDrawerWorks.class);
                startActivity(intent);
                break;

            case R.id.navigation_changepassword:

                startActivity(new Intent(this, ChangePasswordActivity.class));
                break;

            case R.id.navigation_updateemail:

                startActivity(new Intent(this, UpdateEmailActivity.class));
                break;

            case R.id.navigation_ebook:

                startActivity(new Intent(this, EbookActivity.class));
                break;

            case R.id.navigation_feedback:

                Intent in = new Intent(MainActivity.this, FeedbackActivity.class);
                startActivity(in);
                break;

            case R.id.navigation_telegramchannel:

                Intent intent1 = new Intent(MainActivity.this, TelegramChannelActivity.class);
                startActivity(intent1);
                break;

            case R.id.navigation_shareapp:

                Intent intent2 = new Intent(MainActivity.this, ShareAppActivity.class);
                startActivity(intent2);
                break;

            case R.id.navigation_upcomingfeatures:

                Intent intent3 = new Intent(MainActivity.this, UpcomingFeaturesActivity.class);
                startActivity(intent3);
                break;

            case R.id.navigation_logout:

                authProfile.signOut();
                Toast.makeText(MainActivity.this, "Logged Out !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;

        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

            showAlertDialog();


        } else

            showAlertDialog();

    }

    private void showAlertDialog() {

        // Setup the Alert Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.exit);
        builder.setTitle("Close App");
        builder.setMessage("Do you really want to exit ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        // Return to Main Activity if User presses No Button
        builder.setNegativeButton("No", null);


        // Create the AlertDialog
        AlertDialog alertDialog = builder.create();

        // Change the button color of Continue
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.red));
            }
        });

        //Show the AlertDialog
        alertDialog.show();
    }
}