package com.example.appretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    TextView postOutput;
    String Message="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postOutput=findViewById(R.id.postOutput);

        Thread thread =new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        testPost();
                    }
                }
        );
        thread.start();
    }

    protected void testPost(){
        try{
            URL url=new URL("http://foilcollection.infisys.in/Default/CreateUser");
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            String urlParameters="UserID=0&Email=sukhjit@yahoo.com&Mobile=9876543213&UserPass=sukhjit";
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream dStream=new DataOutputStream(connection.getOutputStream());
            dStream.writeUTF(urlParameters);
            dStream.flush();
            dStream.close();
            int responseCode=connection.getResponseCode();
            String output="Request URL : "+url;
            output+="\n"+"Request Parameters : "+ urlParameters;
            output+="\n"+"Response Code: "+ responseCode;

            BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line="";
            StringBuilder responseOutput=new StringBuilder();

            while((line=br.readLine())!=null){
                responseOutput.append(line);
            }
            br.close();
            output+="\n"+responseOutput.toString();
            Message=output;
            postOutput.setText(Message);
        }catch(Exception ex){
            Message=ex.getMessage();
        }
    }
}
