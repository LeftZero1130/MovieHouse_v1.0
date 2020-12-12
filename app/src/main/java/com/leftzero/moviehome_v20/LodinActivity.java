package com.leftzero.moviehome_v20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class LodinActivity extends AppCompatActivity {
    EditText et_userName;
    EditText et_password;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodin);

        et_userName = findViewById(R.id.et_UserName);
        et_password = findViewById(R.id.et_Password);
        checkBox = findViewById(R.id.checkBox);

        LoadInfo();
    }

    private void LoadInfo()
    {
        File file=new File(LodinActivity.this.getFilesDir(),"UserID.txt");
        if (!file.exists()) return;

        try {
            FileReader reader = new FileReader(file);
            BufferedReader br=new BufferedReader(reader);
            String text=br.readLine();
            String[] arr=text.split("#");
            et_userName.setText(arr[0]);
            et_password.setText(arr[1]);
            checkBox.setChecked(true);
            br.close();
            Intent intent = new Intent(this, DefaultHomeActivity.class);
            this.startActivity(intent);
            this.finish();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Login(View view) {

        String userName=et_userName.getText().toString().trim();
        String password= et_password.getText().toString().trim();

        if (TextUtils.isEmpty(userName)|| TextUtils.isEmpty(password))
        {
            Toast.makeText(LodinActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (checkBox.isChecked())
        {
            File file=new File(LodinActivity.this.getFilesDir(),"UserID.txt");
            try {

                OutputStream out=new FileOutputStream(file);
                OutputStreamWriter osw=new OutputStreamWriter(out,"UTF-8");
                BufferedWriter writer=new BufferedWriter(osw);
                writer.write(userName+"#"+password);
                writer.flush();
                writer.close();
                Intent intent = new Intent(this, DefaultHomeActivity.class);
                this.startActivity(intent);
                this.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
