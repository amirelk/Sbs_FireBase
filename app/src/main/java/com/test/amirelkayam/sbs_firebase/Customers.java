package com.test.amirelkayam.sbs_firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Customers extends AppCompatActivity {

    private ImageView mMainImage;
    private EditText mFindCustomers;
    private Button mButtonFindCustomers;
    private Button mButtonNewCustomer;
    private Button btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {   // Start OnCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers);

        mFindCustomers = (EditText)findViewById(R.id.editText_findCustomers);
        mMainImage = (ImageView)findViewById(R.id.main_image);
        mButtonFindCustomers = (Button)findViewById(R.id.btn_findCustomers);
        mButtonNewCustomer = (Button)findViewById(R.id.btn_newCustomer);
        btnBack = (Button) findViewById(R.id.btn_back);



        mMainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }    // End OnCreate


    // OnClick NewCustomer from customers XML
    public void btnNewCustomer_Click(View v){
        Intent intent_newCustomer = new Intent(getApplicationContext(), NewCustomer.class);
        startActivity(intent_newCustomer);
    }

    public void btnFindCustomers_Click(View v){


    }

}   //End Customers
