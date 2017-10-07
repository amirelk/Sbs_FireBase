package com.test.amirelkayam.sbs_firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class Customers extends AppCompatActivity {

    private FirebaseAuth auth;                              // reference to the users
    private DatabaseReference mDatabaseCustomers;           // reference to the data in the DB


    //get current user
    FirebaseUser user = auth.getInstance().getCurrentUser();


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

        mDatabaseCustomers = FirebaseDatabase.getInstance().getReference();

        //// ReycyclerView Customers
        RecyclerView recyclerView_Customer = (RecyclerView) findViewById(R.id.rv_Customer);

        FirebaseRecyclerAdapter <String, Customers.CustomerViewHolder> adapter_customer;

        recyclerView_Customer.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_Customer.setHasFixedSize(true);

        adapter_customer = new FirebaseRecyclerAdapter<String, Customers.CustomerViewHolder>(
                String.class,
                R.layout.customers_rv_item,
                Customers.CustomerViewHolder.class,
                mDatabaseCustomers.child("Users").child(user.getUid()).child("Customers").getRef().child("Name")
        ) {
            @Override
            protected void populateViewHolder(final CustomerViewHolder viewHolder, String name,final int position) {      /// title = name
                viewHolder.customerName_filed.setText(name);
                viewHolder.delCustomer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference itemRef = getRef(position);
                        itemRef.removeValue();

                    }
                });
            }
        };
        recyclerView_Customer.setAdapter(adapter_customer);



    }    // End OnCreate


    private static class CustomerViewHolder extends RecyclerView.ViewHolder {

        TextView customerName_filed;
        Button delCustomer;

        public CustomerViewHolder(View itemView) {// S CustomerViewHolder
            super(itemView);
            customerName_filed = (TextView) itemView.findViewById(R.id.tv_customer_name);
            delCustomer = (Button) itemView.findViewById(R.id.btn_del_customer);
        }

    }


    // OnClick NewCustomer from customers XML
    public void btnNewCustomer_Click(View v){
        Intent intent_newCustomer = new Intent(getApplicationContext(), NewCustomer.class);
        startActivity(intent_newCustomer);
    }

    public void btnFindCustomers_Click(View v){


    }

}   //End Customers
