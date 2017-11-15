package com.example.cer_pc.pas;

/**
 * Created by cer-pc on 2016/9/22.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

/*public class SecondActivity extends AppCompatActivity{
    private void initView(){
        EditText distance=(EditText)findViewById(R.id.editText);//定义距离变量
        EditText speed=(EditText)findViewById(R.id.editText2);//定义速度变量
        Button a=(Button)findViewById(R.id.button2);//确定按钮
        Button b=(Button)findViewById(R.id.button5);//取消按钮
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_main);
        initView();
    }
    @Override
            public
        b.setOnClickListener(new View.OnClickListener() {//取消
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

};}*/
public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button01 = null;
    private Button button02 = null;
    private EditText editText01 = null;
    private EditText editText02 = null;
    private TextView textView = null;

    private static Socket ClientSocket = null;
    private byte[] msgBuffer1 = null;
    private byte[] msgBuffer2 = null;
    private String string="SETL";
    Handler handler = new Handler();


    private void initView() {
        button01 = (Button) findViewById(R.id.button01);
        button02 = (Button) findViewById(R.id.button02);
        editText01 = (EditText) findViewById(R.id.editText);//定义距离变量
        editText02=(EditText)findViewById(R.id.editText2);//定义速度变量
        textView = (TextView) findViewById(R.id.textView10);

        button01.setOnClickListener(this);
        button02.setOnClickListener(this);

        button01.setEnabled(true);
        button02.setEnabled(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondactivity_main);
        initView();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button01:
                // TODO: 15-9-4 socket连接线程
                connectThread();
                break;
            case R.id.button02:
                // TODO: 15-9-4 发送数据线程
                sendMsgThread();
                break;
        }
    }
    private void sendMsgThread() {

        final String text1 = editText01.getText().toString();
        final String text2=editText02.getText().toString();

        //string.concat(text1+'V');
        if(text2.length()==3)string+=text2+'V';
        if(text2.length()==2)string+='0'+text2+'V';
        if(text2.length()==1)string+="00"+text2+'V';
        if(text1.length()==3)string+=text1+"\r\n";
        if(text1.length()==2)string+='0'+text1+"\r\n";
        if(text1.length()==1)string+="00"+text1+"\r\n";
        try {
            msgBuffer1 = string.getBytes("UTF-8");
            msgBuffer2 = text1.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream outputStream;
                    //Socket输出流
                    outputStream = ClientSocket.getOutputStream();

                    outputStream.write(msgBuffer1);
                    outputStream.write(msgBuffer2);
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.append("发送成功："+"\n"+"距离："+text2+"m"+"\n"+"速度："+text1+"m/s"+"\n"+string);
                    }
                });
            }
        }).start();
    }

    private void connectThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ClientSocket = new Socket("192.168.4.1",8080);
                    if (ClientSocket.isConnected()){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.append("连接成功！"+"\n");
                                button01.setEnabled(false);
                                button02.setEnabled(true);
                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                textView.append("连接失败！"+"\n");
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }







    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
