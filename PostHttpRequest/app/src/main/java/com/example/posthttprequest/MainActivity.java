package com.example.posthttprequest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.posthttprequest.App_Code.ModelUser;

public class MainActivity extends AppCompatActivity {
    TextView postOutput;
    String Message = "";
    Boolean isUserCreated=false;
    Boolean isUserDeleted=false;
    ModelUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postOutput = findViewById(R.id.postOutput);

        user=new ModelUser("20","","","");

        Thread thread=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        ControllerUser controllerUser=new ControllerUser();
                        Message = controllerUser.deleteUser(user);
                        isUserDeleted=controllerUser.isUserDeleted;
                    }
                }
        );
        thread.start();

        while(!isUserDeleted);
        if(isUserDeleted)
            postOutput.setText(Message);
    }

/*    protected void testPost() {
        try {
            URL url = new URL("http://");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String urlParameters = "UserID=0&Email=xyp@yahoo.com&Mobile=9876543213&UserPass=xyz";
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
            dStream.writeUTF(urlParameters);
            dStream.flush();
            dStream.close();
            int responseCode = connection.getResponseCode();
            String output = "Request URL : " + url;
            output += "\n" + "Request Parameters : " + urlParameters;
            output += "\n" + "Response Code: " + responseCode;

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder responseOutput = new StringBuilder();

            while ((line = br.readLine()) != null) {
                responseOutput.append(line);
            }
            br.close();
            output += "\n" + responseOutput.toString();
            Message = output;
            post=true;
        } catch (Exception ex) {
            Message = ex.getMessage();
        }
    }*/
}
