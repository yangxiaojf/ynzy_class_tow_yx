package com.example.msi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    //键值对存储的对象
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    //编辑框
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        usernameEdit = (EditText)findViewById(R.id.user_name);
        passwordEdit = (EditText)findViewById(R.id.pass_word);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        login = (Button)findViewById(R.id.login);
        //是否存储用户的密码
        boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember){
            //存储
            String username = pref.getString("username","");
            String password = pref.getString("password","");
            //若点击了存储密码功能，则将已经存储的密码返回到编辑框中--记住密码
            usernameEdit.setText(username);
            passwordEdit.setText(password);
            //默认被勾选
            rememberPass.setChecked(true);
        }
        //当点击登录按钮时触发的事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = usernameEdit.getText().toString();
                String p = passwordEdit.getText().toString();
                //默认账号：root,密码：123456
                if (u.equals("root") && p.equals("123456")){
                    editor = pref.edit();
                    //如果记住密码选项被选中
                    if (rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("username",u);
                        editor.putString("password",p);
                    }else{
                        //清除已经记住的密码
                        editor.clear();
                    }
                    editor.apply();
                    //记住密码后返回到其他页面--相当于刷新一次
                    Intent i = new Intent(Login.this,Index.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(Login.this,"账号或密码不对",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
