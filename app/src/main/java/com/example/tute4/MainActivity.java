package com.example.tute4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DATABASE.DBHandler;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button add, signin, update, delete, selectall;
    private EditText Username, password;

    DBHandler database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.btn_add);
        signin = findViewById(R.id.btn_sign_in);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        selectall = findViewById(R.id.btn_select_all);
        Username = findViewById(R.id.editText_user_name);
        password = findViewById(R.id.editText_passwords);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().length() <= 0 || password.getText().length() <= 0){
                    Toast.makeText(getApplicationContext(), "Please enter username and password.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                database.addInfo(Username.getText().toString(), password.getText().toString());

                Toast.makeText(getApplicationContext(), "Record added successfully.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().length() <= 0 || password.getText().length() <= 0){
                    Toast.makeText(getApplicationContext(), "Please enter username and password.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                List<String> matchList = database.readInfo(Username.getText().toString(), password.getText().toString());

                if(matchList.size() > 0){
                    Toast.makeText(getApplicationContext(), "Username and password is correct",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Username and password is wrong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().length() <= 0 || password.getText().length() <= 0){
                    Toast.makeText(getApplicationContext(), "Please enter username and password.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                database.updateInfo(Username.getText().toString(), password.getText().toString());
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().length() <= 0){
                    Toast.makeText(getApplicationContext(), "Please enter username to delete",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                database.deleteInfo(Username.getText().toString());
            }
        });

        selectall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> usernames = database.readAllInfo();

                for (String usr: usernames) {
                    Log.i("db",usr);
                }
            }
        });

    }
}
