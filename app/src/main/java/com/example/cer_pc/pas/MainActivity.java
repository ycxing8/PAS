package com.example.cer_pc.pas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button a=(Button)findViewById(R.id.button);//设置参数按钮
        //Button z =(Button)findViewById(R.id.button3);//建立连接按钮
        Button c =(Button)findViewById(R.id.button4);//使用说明按钮
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//设置参数
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {//使用说明
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Instruction.class);
                startActivity(intent);
            }
        });
    }
}
