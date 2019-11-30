package com.example.posthttprequest.App_Interface;

import com.example.posthttprequest.App_Code.ModelUser;

import org.json.JSONObject;

import java.net.URL;

public interface InterfaceHTTP {

    JSONObject HttpGetRequest(URL url);
    String HttpPostRequest(String webUrl, ModelUser user);
}
