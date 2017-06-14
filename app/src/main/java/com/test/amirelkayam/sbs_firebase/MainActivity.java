package com.test.amirelkayam.sbs_firebase;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {// Start MainActivity

    private ImageView mMainImage;

    private Button mSuppliers;
    private Button mCustomers;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {// start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainImage = (ImageView) findViewById(R.id.main_image);

        mSuppliers = (Button) findViewById(R.id.btn_suppliers);
        mCustomers = (Button) findViewById(R.id.btn_customers);


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


    }// end onCreate


}// END MainActivity


