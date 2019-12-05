package com.example.gethttprequest.App_Code;

public class BaseURLs {
    public static String getDeleteUserUrl() {return DELETE_USER_URL;}
    public static String getPostUrl() {return CREATE_USER_URL;}
    public static String getGetUsersUrl() {return GET_USERS_URL;}
    public static String getUpdateUsersUrl() {return UPDATE_USERS_URL;}

    protected static final String DELETE_USER_URL="http://foilcollection.infisys.in/Default/DeleteUser?UserID=";
    protected static final String UPDATE_USERS_URL="";
    protected static final String CREATE_USER_URL="";
    protected static final String GET_USERS_URL="http://foilcollection.infisys.in//api/Default/Get";

    public static String getCreateUserUrl() {
        return CREATE_USER_URL;
    }

    public static String getGetUserUrl() {
        return GET_USER_URL;
    }

    protected static final String GET_USER_URL="http://foilcollection.infisys.in/Default/GetUser?UserID=";
}
