package com.example.gethttprequest.App_Interface;

import com.example.gethttprequest.App_Code.ModelUser;

import org.json.JSONArray;

public interface InterfaceHTTP {

    JSONArray HttpGetRequest(String getUrl);
    String HttpPostRequest(String postUrl, ModelUser user);
}
