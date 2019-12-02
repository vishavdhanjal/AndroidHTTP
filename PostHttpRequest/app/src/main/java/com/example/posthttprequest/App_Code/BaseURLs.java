package com.example.posthttprequest.App_Code;

public class BaseURLs {
    public static String getDeleteUsersUrl() {return DELETE_USERS_URL;}
    public static String getPostUrl() {return CREATE_USER_URL;}
    public static String getGetUrl() {return GET_USERS_URL;}
    public static String getUpdateUsersUrl() {return UPDATE_USERS_URL;}

    protected static final String DELETE_USERS_URL="";
    protected static final String UPDATE_USERS_URL="";
    protected static final String CREATE_USER_URL="";
    protected static final String GET_USERS_URL="";
}
