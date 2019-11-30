package com.example.posthttprequest;

import com.example.posthttprequest.App_Code.BaseURLs;
import com.example.posthttprequest.App_Code.ClsHttp;
import com.example.posthttprequest.App_Code.ModelUser;

public class ControllerUser {
    String Message="";
    boolean isUserCreated=false;
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
}
