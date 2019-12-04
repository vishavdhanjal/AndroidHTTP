package com.example.gethttprequest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.gethttprequest.App_Code.BaseURLs;
import com.example.gethttprequest.App_Code.ModelUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
    String Message="";
    TextView txtView;
    boolean post=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView=findViewById(R.id.txtView);
        Thread thread=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        ControllerUser controllerUser=new ControllerUser();
                        ArrayList<ModelUser> lstUsers=controllerUser.getAllUsers();
                        for(int i=0;i<lstUsers.size();i++)
                            Message+=lstUsers.get(i).getUserID()+"\t"+
                                    lstUsers.get(i).getEmail()+"\t"+
                                    lstUsers.get(i).getMobile()+"\t"+
                                    lstUsers.get(i).getUserPass()+"\n";
                        txtView.setText(Message);
                    }
                }
        );
        thread.start();
    }

    /*protected void testGET(){
        try {
            URL url = new URL(BaseURLs.getGetUsersUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            InputStream in = new BufferedInputStream(connection.getInputStream());

            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            JSONArray jsonArray=new JSONArray(responseStrBuilder.toString());
            jsonArray.length();
        } catch (Exception ex) {
            Message = ex.getMessage();
        }
    }*/
}