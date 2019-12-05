package com.example.gethttprequest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gethttprequest.App_Code.ModelUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String Message="";
    TextView txtView;
    boolean getRequest=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView=findViewById(R.id.txtView);
        Thread thread=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        ModelUser user=new ModelUser();
                        user.setUserID("17");

                        ControllerUser controllerUser=new ControllerUser();
                        controllerUser.deleteUser(user);

                        ArrayList<ModelUser> lstUsers=controllerUser.getAllUsers();
                        for(int i=0;i<lstUsers.size();i++)
                            Message+=lstUsers.get(i).getUserID()+"\t"+
                                    lstUsers.get(i).getEmail()+"\t"+
                                    lstUsers.get(i).getMobile()+"\t"+
                                    lstUsers.get(i).getUserPass()+"\n";
                        getRequest=true;

                    }
                }
        );
        thread.start();
        while(!getRequest){}
        if(getRequest)
            txtView.setText(Message);
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
