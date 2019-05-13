package com.example.msi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class Index extends AppCompatActivity implements View.OnClickListener {
    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        //找到按钮
        Button b = (Button)findViewById(R.id.save_data);
        b.setOnClickListener(this);
        //找到编辑框
        edit = (EditText)findViewById(R.id.edit);
        //键值对存储
        Button b1 = (Button)findViewById(R.id.save_data1);
        b1.setOnClickListener(this);
        //
        Button b2 = (Button)findViewById(R.id.get_data);
        b2.setOnClickListener(this);
        //goto_login
        Button goto_login = (Button)findViewById(R.id.goto_login);
        goto_login.setOnClickListener(this);
    }
    /**
     * 接口实现方法，点击以后写具体的逻辑
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goto_login:
                //跳转到登录界面
                Intent i = new Intent(Index.this,Login.class);
                startActivity(i);
                break;
            case R.id.get_data:
                SharedPreferences pref = getSharedPreferences("data1",MODE_PRIVATE);
                String name = pref.getString("name","");
                int age = pref.getInt("age",1);
                boolean married = pref.getBoolean("married",false);
                String job = pref.getString("job","");
                Log.d("Index",name + "的年龄是:" + age + "岁，工作是:"
                        + job + ",是否结婚:" + married);
                break;
            case R.id.save_data1:
                Log.d("||||","是否执行");
                //使用键值对进行存储
                SharedPreferences.Editor editor =
                        getSharedPreferences("data1",MODE_PRIVATE).edit();
                editor.putString("name","翠花");
                editor.putInt("age",18);
                editor.putString("job","上泡菜");
                editor.putBoolean("married",false);
                //存储
                editor.apply();
                break;
            case R.id.save_data:
                //文件流存储
                Log.d("***","念念不忘，必有回响！");
                //1.获取输入框中输入的内容
                String q = edit.getText().toString();
                Log.d("____",q);
                save(q);
                break;

        }
    }
    /**
     * 存储数据
     */
    public void save(String data){
        //String data = "hello android!";
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //data表示存储文件的名称，MODE_PRIVATE表示存储方式为私有
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }


}
