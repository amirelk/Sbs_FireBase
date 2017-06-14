package com.test.amirelkayam.sbs_firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText loginEmail;
    private EditText loginPassword;
    //  private Button mButtonResetPassword;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        //   mButtonResetPassword = (Button) findViewById(R.id.btn_reset_password);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void btnLogin_Click(View v){

        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "הקלד כתובת מייל!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "הקלד סיסמא!", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog progressDialog = ProgressDialog.show(Login.this, "אנא המתן...", "טוען...", true);

        (firebaseAuth.signInWithEmailAndPassword(loginEmail.getText().toString(), loginPassword.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "כניסה הצליחה", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            Log.e("שגיאה! לא ניתן להיכנס", task.getException().toString());
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    public void btnResetPassword_Click(View v){
        Intent intent_resetPassword = new Intent(getApplicationContext(), ResetPassword.class);
        startActivity(intent_resetPassword);
    }

    public void btnRegister_Click(View v){
        Intent intent_register = new Intent(getApplicationContext(),Registration.class);
        startActivity(intent_register);
    }
}
