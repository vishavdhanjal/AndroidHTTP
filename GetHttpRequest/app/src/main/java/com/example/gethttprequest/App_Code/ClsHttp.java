package com.example.gethttprequest.App_Code;

import com.example.gethttprequest.App_Interface.InterfaceHTTP;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public JSONArray HttpGetRequest(String getUrl) {
        JSONArray jsonArray=null;
        try{
            isPostExecuted=false;
            isGetExecuted=false;
            getConnection(getUrl);
            if(isconnected)
                connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(in));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            jsonArray=new JSONArray(responseStrBuilder.toString());
        }catch (Exception ex){
            isError=true;
            Message=ex.getMessage();
        }finally {
            connection.disconnect();
            isconnected=false;
        }
        return jsonArray;
    }

    @Override
    public String HttpPostRequest(String postUrl, ModelUser user) {
        return null;
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

