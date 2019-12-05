package com.example.gethttprequest;


import android.graphics.ColorSpace;

import com.example.gethttprequest.App_Code.BaseURLs;
import com.example.gethttprequest.App_Code.ClsHttp;
import com.example.gethttprequest.App_Code.ModelUser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControllerUser {
    String Message="";
    boolean isUserCreated=false;
    boolean isUserDeleted=false;
    public String createUser(ModelUser user){
        isUserCreated=false;
        try{
            ClsHttp http=new ClsHttp();
            Message=http.HttpPostRequest(BaseURLs.getPostUrl(),user);
            isUserCreated=true;
        }catch (Exception ex){
            Message=ex.getMessage();
        }
        return Message;
    }

    public ArrayList<ModelUser> getAllUsers(){
        ArrayList<ModelUser> lstUsers=new ArrayList<>();
        try{
            ClsHttp http=new ClsHttp();
            JSONArray jsonArray =http.HttpGetRequest(BaseURLs.getGetUsersUrl());
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                ModelUser user=new ModelUser();
                user.setUserID(jsonObject.getString("UserID"));
                user.setMobile(jsonObject.getString("Mobile"));
                user.setEmail(jsonObject.getString("Email"));
                user.setUserPass(jsonObject.getString("UserPass"));
                lstUsers.add(user);
            }
        }catch(Exception ex){
            Message=ex.getMessage();
        }
        return lstUsers;
    }
    public ArrayList<ModelUser> getUser(ModelUser modelUser){
        ArrayList<ModelUser> lstUsers=new ArrayList<>();
        try{
            ClsHttp http=new ClsHttp();
            JSONArray jsonArray =http.HttpGetRequest(BaseURLs.getGetUserUrl()+modelUser.getUserID());
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                ModelUser user=new ModelUser();
                user.setUserID(jsonObject.getString("UserID"));
                user.setMobile(jsonObject.getString("Mobile"));
                user.setEmail(jsonObject.getString("Email"));
                user.setUserPass(jsonObject.getString("UserPass"));
                lstUsers.add(user);
            }
        }catch(Exception ex){
            Message=ex.getMessage();
        }
        return lstUsers;
    }
/*    public String deleteUser(ModelUser user){
        isUserDeleted=false;
        try{
            ClsHttp http=new ClsHttp();
            Message=http.HttpPostRequest(BaseURLs.getDeleteUsersUrl(),user);
            isUserDeleted=true;
        }catch (Exception ex){
            Message=ex.getMessage();
        }
        return Message;
    }*/
}
