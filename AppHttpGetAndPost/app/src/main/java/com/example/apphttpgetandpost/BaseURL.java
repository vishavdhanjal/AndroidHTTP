package com.example.apphttpgetandpost;

import java.net.URL;

public class BaseURL {
    protected static final String GET_URL="http://foilcollection.infisys.in/api/Default/Get";
    public static String getGetUrl() {return GET_URL;}

    protected static final String POST_URL="http://foilcollection.infisys.in/Default/CreateUser";

    public static String getPostUrl() {
        return POST_URL;
    }
}
