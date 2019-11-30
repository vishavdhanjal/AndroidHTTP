package com.example.apphttpgetandpost;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnGet;
    Button btnPost;

    TextView txtMessage;
    String Message="";
    Boolean flag=false;
    Boolean flagPost=false;
    JSONArray jsonArray;
    String response = "";
    List<ModelUser> lstUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet=findViewById(R.id.btnGet);
        btnPost=findViewById(R.id.btnPost);
        txtMessage=findViewById(R.id.txtMessage);
        //initGet();
        Thread thread=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        testPost();
                    }
                }
        );
        thread.start();
        btnPost.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(flagPost)
                            txtMessage.setText("Post Success.");
                        else
                            txtMessage.setText("Post Failure.");
                    }
                }
        );
    }

    public void testPost(){
        ModelUser user=new ModelUser();
        user.setUserID("0");
        user.setEmail("android@yahoo.com");
        user.setMobile("1234567890");
        user.setUserPass("androidpwd");
        //POST data
        ClsHTTP http=new ClsHTTP();
        http.setUrl(BaseURL.getPostUrl());
        flagPost=http.HTTPPostRequest(user);
    }
    protected void testGet() throws Exception {
        ClsHTTP http=new ClsHTTP();
        http.setUrl(BaseURL.getGetUrl());
        flag=http.HTTPGetRequest();
        Log.d("Connection : ", http.getConnectionOpen().toString());
        InputStream in = http.getInpStream();
        Log.d("IsError : ", http.getError().toString());

        //////////////////////////////////////////////////////////////////
        BufferedReader reader = new BufferedReader(new
                InputStreamReader(in,"UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while((inputStr=reader.readLine())!=null){
            responseStrBuilder.append(inputStr);
        }
        jsonArray=new JSONArray(responseStrBuilder.toString());
        /////////////////////////////////////////////////////////////////
        Message=http.getMessage();
        Log.d("HTTPMessage : ", Message);
    }

    public void initGet(){
        Thread thread=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            testGet();
                        }catch (Exception ex){
                            Log.e("RunException",ex.getMessage());
                        }
                    }
                }
        );
        thread.start();

        btnGet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(flag) {
                            test2();
                            String result="";
                            for(ModelUser user : lstUsers){
                                result=result+user.getUserID()+"\t"+
                                        user.getEmail()+"\t"+
                                        user.getMobile()+"\t"+
                                        user.getUserPass()+"\n";
                            }
                            txtMessage.setText(result);
                        }
                        else
                            txtMessage.setText("Request failed or not processed yet.");
                    }
                }
        );
    }

    public void test2(){
        lstUsers=new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ModelUser user=new ModelUser();
                user.setUserID(jsonObject.getString("UserID"));
                user.setEmail(jsonObject.getString("Email"));
                user.setMobile(jsonObject.getString("Mobile"));
                user.setUserPass(jsonObject.getString("UserPass"));
                lstUsers.add(user);
            }
        }catch (Exception ex){

        }
    }
}
