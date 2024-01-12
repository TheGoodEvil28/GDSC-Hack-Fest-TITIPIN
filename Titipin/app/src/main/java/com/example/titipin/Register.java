package com.example.titipin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private TextView etUsername, etEmail, etPassword;

    private Button regist, Login;

    private DatabaseReference dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        regist = findViewById(R.id.Regist);
        Login = findViewById(R.id.btnLogin);

        dataBase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://titipin-d3591-default-rtdb.firebaseio.com/");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getApplicationContext(), Login.class);
                startActivity(register);

            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Ada Data Yang Masih Kosong!!", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(),"Password Minimal 6 Baris ",Toast.LENGTH_SHORT).show();

                } else {
                    dataBase=FirebaseDatabase.getInstance().getReference("users");
                    dataBase.child(username).child("user").setValue(username);
                    dataBase.child(username).child("email").setValue(email);
                    dataBase.child(username).child("password").setValue(password);


                    Toast.makeText(getApplicationContext(),"RegisterBerhasil",Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(), Login.class);
                    register.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(register);
                    finish();
                }
            }
        });
    }
}