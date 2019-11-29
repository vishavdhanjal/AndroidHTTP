package com.example.apphttpgetandpost;

import android.graphics.ColorSpace;
import android.view.Display;

import java.net.HttpURLConnection;
import java.net.URL;

public interface InterfaceHttp {

    public void openConnection();
    public Boolean HTTPGetRequest();
    public Boolean HTTPPostRequest(ModelUser user);
}
