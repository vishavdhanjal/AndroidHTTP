package com.example.posthttprequest.App_Code;

public class ModelUser {
    private static final String PARAMETERS[]={"UserID","Email","Mobile","UserPass"};
    public static String[] getPARAMETERS() {return PARAMETERS;}

    protected String UserID;
    public String getUserID() {return UserID;}
    public void setUserID(String userID) {UserID = userID;}
    protected String Email;
    public String getEmail() {return Email;}
    public void setEmail(String email) {Email = email;}
    protected String Mobile;
    public String getMobile() {return Mobile;}
    public void setMobile(String mobile) {Mobile = mobile;}
    protected String UserPass;
    public String getUserPass() {return UserPass;}
    public void setUserPass(String userPass) {UserPass = userPass;}

    public ModelUser(String UserID, String Email, String Mobile, String UserPass){
        this.UserID=UserID;
        this.Email=Email;
        this.Mobile=Mobile;
        this.UserPass=UserPass;
    }
    public ModelUser(){

    }
}
