package com.test.amirelkayam.sbs_firebase;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {// Start MainActivity

    private ImageView mMainImage;

    private Button mSuppliers;
    private Button mCustomers;
    private Button mSignOut;

  //  private TextView mLogedOnAs;
    private TextView mUserLogedOn;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {// start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainImage = (ImageView) findViewById(R.id.main_image);

        mSuppliers = (Button) findViewById(R.id.btn_suppliers);
        mCustomers = (Button) findViewById(R.id.btn_customers);
        mSignOut = (Button) findViewById(R.id.btn_signOut);

     //   mLogedOnAs = (TextView) findViewById(R.id.tv_logedonas);
        mUserLogedOn = (TextView) findViewById(R.id.tv_userlogedon);


        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }
            }
        };

        mUserLogedOn.setText(user.getEmail());


        // go to CLASS >>  Suppliers.class
        mSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_suppliers = new Intent(getApplicationContext(), Suppliers.class);
                startActivity(intent_suppliers);
            }
        });


        // go to CLASS >>  Customers.class
        mCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_customers = new Intent(getApplicationContext(), Customers.class);
                startActivity(intent_customers);
            }
        });


        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                finish();
            }
        });

    }// end onCreate


}// END MainActivity


