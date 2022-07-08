package com.example.bestplace_jav2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

public class RegActivity extends AppCompatActivity implements android.view.View.OnClickListener {
    private TextView mTextView;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

    }
    public void onMyButtonClick(View view)
    {
        // выводим сообщение
        Toast.makeText(this, "Я делал это всё два дня, учитывая что перед этим я настраивал рабочую среду две недели, в которой я абсолютно не шарю. Да, я оправдываюсь. Вопросы?", Toast.LENGTH_SHORT).show();
    }
    public void loginUser(View view) {
        final EditText loginText = (EditText)findViewById(R.id.loginText);
        final EditText passwordText = (EditText)findViewById(R.id.PasswordText);
        final EditText emailText = (EditText)findViewById(R.id.emailText);
        final EditText dateText = (EditText)findViewById(R.id.editTextDate);
        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();
        String email = emailText.getText().toString();
        String date = dateText.getText().toString();

        DatabaseReference newUser = database.child(login);
        newUser.child("email").setValue(email);
        newUser.child("password").setValue(password);
        newUser.child("date").setValue(date);
    }

    @Override
    public void onClick(android.view.View view) {
        final EditText loginText = (EditText)findViewById(R.id.loginText);
        final EditText passwordText = (EditText)findViewById(R.id.PasswordText);
        final EditText emailText = (EditText)findViewById(R.id.emailText);
        final EditText dateText = (EditText)findViewById(R.id.editTextDate);
        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();
        String email = emailText.getText().toString();
        String date = dateText.getText().toString();

        DatabaseReference newUser = database.child(login);
        newUser.child("email").setValue(email);
        newUser.child("password").setValue(password);
        newUser.child("date").setValue(date);
        Toast.makeText(this,"регистрация прошла успешно!",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"вернитесь на прошлую страницу",Toast.LENGTH_LONG).show();
    }
}
