package com.example.cer_pc.pas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
/**
 * Created by cer-pc on 2016/9/23.
 */
public class SetConnection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setconnection);
        EditText ip=(EditText)findViewById(R.id.editText);//定义ip
        EditText port=(EditText)findViewById(R.id.editText2);//定义端口
        Button a=(Button)findViewById(R.id.button6);//确定并建立按钮
        Button b=(Button)findViewById(R.id.button7);//取消按钮
        b.setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetConnection.this,MainActivity.class);
                startActivity(intent);
            }
        });
    };
}
