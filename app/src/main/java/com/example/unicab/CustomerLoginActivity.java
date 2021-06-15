package com.example.unicab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerLoginActivity extends AppCompatActivity {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    FloatingActionButton mFb, mGoogle, mTwitter;
    float v=0;

    EditText mEmail, mPassword;
    Button mLogin, mSignup;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        mAuth = FirebaseAuth.getInstance();

        firebaseAuthListener = firebaseAuth -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null){
                Intent intent = new Intent(CustomerLoginActivity.this, CustomerMapActivity.class);
                startActivity(intent);
                finish();
                return;
            }
        };



        /*

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mFb = findViewById(R.id.fab_fb);
        mGoogle = findViewById(R.id.fab_google);
        mTwitter = findViewById(R.id.fab_twitter);

        mTabLayout.addTab(mTabLayout.newTab().setText("Login"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Signup"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this,mTabLayout.getTabCount());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mFb.setTranslationY(300);
        mGoogle.setTranslationY(300);
        mTwitter.setTranslationY(300);
        mTabLayout.setTranslationY(300);

        mFb.setAlpha(v);
        mGoogle.setAlpha(v);
        mTwitter.setAlpha(v);
        mTabLayout.setAlpha(v);


        mFb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        mGoogle.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        mTwitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        mTabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();


         */




        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        mLogin = findViewById(R.id.login);
        mSignup = findViewById(R.id.signup);



        mSignup.setOnClickListener((v -> {
            final String email = mEmail.getText().toString();
            final String password = mPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CustomerLoginActivity.this, task -> {
                if(!task.isSuccessful()){
                    Toast.makeText(CustomerLoginActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                } else {
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Customers").child(user_id);
                    current_user_db.setValue(true);
                }
            });
        }));

        mLogin.setOnClickListener(v -> {
            final String email = mEmail.getText().toString();
            final String password = mPassword.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(CustomerLoginActivity.this, task -> {
                if(!task.isSuccessful()){
                    Toast.makeText(CustomerLoginActivity.this, "sign up error", Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

}