package com.example.posthttprequest.App_Code;

import com.example.posthttprequest.App_Interface.InterfaceHTTP;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClsHttp implements InterfaceHTTP {
    protected final int RESPONSE_CODE=200;
    Boolean isconnected=false;
    Boolean isError=false;
    Boolean isPostExecuted=false;
    Boolean isGetExecuted=false;

    String Message="";

    protected URL url;
    protected HttpURLConnection connection;

    @Override
    public JSONObject HttpGetRequest(URL url) {

        try{
            isPostExecuted=false;
            isGetExecuted=false;
        }catch (Exception ex){
            isError=true;
            Message=ex.getMessage();
        }

        return null;
    }

    @Override
    public String HttpPostRequest(String webUrl, ModelUser user) {
        try{
            isPostExecuted=false;
            isGetExecuted=false;
            getConnection(webUrl);
            if(isconnected){
                connection.setRequestMethod("DELETE");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeUTF(prepareURLParameters(user));
                dStream.flush();
                dStream.close();
                int responseCode = connection.getResponseCode();
                if(responseCode==RESPONSE_CODE)
                    Message="Post Request Successfully.";
                else
                    Message="Post Request failed.";
                isPostExecuted=true;
            }else{
                Message="HTTP connection for POST method failed.";
            }
        }catch (Exception ex){
            isError=true;
            Message=ex.getMessage();
        }
        finally{
            closeConnection();
        }
        return Message;
    }

    private String prepareURLParameters(ModelUser user){
        String urlParameters="";
        try{
            urlParameters="UserID="+user.getUserID();
            if(user.getEmail()!="")
                urlParameters+="&Email="+user.getEmail();
            if(user.getMobile()!="")
            urlParameters+="&Mobile="+user.getMobile();
            if(user.getUserPass()!="")
                urlParameters+="&UserPass="+user.getUserPass();
        }catch (Exception ex){
            Message=ex.getMessage();
        }
        return urlParameters;
    }

    public void getConnection(String webURL) {
        try{
            if (!isconnected) {
                url = new URL(webURL);
                connection = (HttpURLConnection) url.openConnection();
                isconnected=true;
                Message="Http Connected Successfully";
            }
            else{
                Message="HTTP Connection failed.";
            }
        }catch (Exception ex){
            isError=true;
            Message=ex.getMessage();
        }
    }

    protected void closeConnection(){
        try{
            if(isconnected){
                connection.disconnect();
                isconnected=false;
            }
        }catch (Exception ex){
            isError=true;
            Message=ex.getMessage();
        }
    }
}

