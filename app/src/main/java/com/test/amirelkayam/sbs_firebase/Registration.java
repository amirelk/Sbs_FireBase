package com.test.amirelkayam.sbs_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    private EditText regEmail;
    private EditText regPassword;
    private Button btnBack;
    private ImageView mMainImage;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regEmail = (EditText) findViewById(R.id.registerEmail);
        regPassword = (EditText) findViewById(R.id.registerPassword);
        btnBack = (Button) findViewById(R.id.btn_back);
        mMainImage = (ImageView)findViewById(R.id.main_image);


        firebaseAuth = FirebaseAuth.getInstance();

        mMainImage.setOnClickListener(new View.OnClickListener() {   /// app logo - if you click its go back screen
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

    }

    public void btnRegisterNow_Click(View v){

        String email = regEmail.getText().toString().trim();
        String password = regPassword.getText().toString().trim();


        ///  Chacking Rules - if empty ---> Toast meggage \\\

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "הקלד כתובת מייל!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "הקלד סיסמא!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6){
            Toast.makeText(getApplicationContext(), "סיסמא חייבת להיות מינימום 6 תווים", Toast.LENGTH_SHORT).show();
            return;
        }

/*
        if(TextUtils.isEmpty(phone)) {
            Toast.makeText(getApplicationContext(), "הקלד מספר טלפון", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.length() < 10){
            Toast.makeText(getApplicationContext(), "טלפון חייב להיות מינימום 10 תווים", Toast.LENGTH_SHORT).show();
            return;
        }
*/

        final ProgressDialog progressDialog = ProgressDialog.show(Registration.this,  "אנא המתן...", "טוען...", true);
        (firebaseAuth.createUserWithEmailAndPassword(regEmail.getText().toString().trim(), regPassword.getText().toString().trim()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {        ///// Chaking if reg is success --> skip to login , else - stay in the same screen end say - Error \\\
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "הרשמה הצליחה! הנך מועבר למסך הכניסה", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Registration.this, Login.class);
                            startActivity(i);
                        }
                        else
                        {
                            Log.e("שגיאת רישום!", task.getException().toString());
                            Toast.makeText(Registration.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });



    }
}
