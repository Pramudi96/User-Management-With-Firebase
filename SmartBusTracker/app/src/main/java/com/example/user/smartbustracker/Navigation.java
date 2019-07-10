package com.example.user.smartbustracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    

    Fragment tempFragment;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tempFragment = new HomeFragment();
        transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.main_Container, tempFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (tempFragment instanceof HomeFragment){
                return;
            }
            else {
                tempFragment = new HomeFragment();
                transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.main_Container, tempFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_Home){
            Intent intent = new Intent(Navigation.this,Navigation.class);
            startActivity(intent);
            return false;
        }
        else if (id == R.id.action_Profile){
            Intent intent = new Intent(Navigation.this,Profile.class);
            startActivity(intent);
            return false;

        }*/


        if (id == R.id.action_Help){
            tempFragment = new HelpFragment();
            transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.main_Container, tempFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return false;
        }
        else if (id == R.id.nav_Logout){
            AlertDialog.Builder a_builder = new AlertDialog.Builder(Navigation.this);
            a_builder.setMessage("Do You Want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            AlertDialog alert = a_builder.create();
            alert.setTitle("SMART BUS TRACKER");
            alert.show();


           /* Intent intent = new Intent(Navigation.this,MainActivity.class);
            startActivity(intent);
            return false;*/

        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {

            tempFragment = new HomeFragment();
            transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.main_Container, tempFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_Profile) {

           /* tempFragment = new ProfileFragment();
            transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.main_Container, tempFragment);
            transaction.addToBackStack(null);
            transaction.commit();*/


            Intent intent = new Intent(Navigation.this,Profile.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_Root) {
            tempFragment = new RootFragment();
            transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.main_Container, tempFragment);
            transaction.addToBackStack(null);
            transaction.commit();




            } else if (id == R.id.nav_Help) {

            tempFragment = new HelpFragment();
            transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.main_Container, tempFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_Logout) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(Navigation.this);
            a_builder.setMessage("Do You Want to exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    auth.signOut();
                    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                        @Override
                        public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            if (user == null) {
                                startActivity(new Intent(Navigation.this, MainActivity.class));
                                finish();
                            }
                        }
                    };

                }
            })

                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

            AlertDialog alert = a_builder.create();
            alert.setTitle("SMART BUS TRACKER");
            alert.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
