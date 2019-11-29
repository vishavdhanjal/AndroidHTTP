package com.example.apphttpgetandpost;

import android.icu.util.Output;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class ClsHTTP implements InterfaceHttp {
    protected String Message="";
    public String getMessage() {return Message;}

    protected String url;
    public void setUrl(String url) {this.url = url;}

    protected HttpURLConnection httpURLConnection;

    protected InputStream inpStream;
    public InputStream getInpStream() {return inpStream;}

    protected Boolean isError=false;
    public Boolean getError() {return isError;}

    Boolean isConnectionOpen=false;
    public Boolean getConnectionOpen() {return isConnectionOpen;}

    @Override
    public void openConnection() {
        try{
            URL path=new URL(url);
            httpURLConnection=(HttpURLConnection)path.openConnection();
            isConnectionOpen=true;
        }catch (Exception ex){
            isError=true;
            isConnectionOpen=false;
            Message=ex.getMessage();
        }
    }

    @Override
    public Boolean HTTPGetRequest() {
        Boolean flag=false;
        try{
            openConnection();
            if(isConnectionOpen){
                Message="HttpConnection Open Successfully.";
                inpStream = new BufferedInputStream(httpURLConnection.getInputStream());
                flag=true;
            }else{
                Message="HttpConnection failed.";
            }
        }catch (Exception ex){
            isError=true;
            Message=ex.getMessage();
            flag=false;
        }
        finally {
            httpURLConnection.disconnect();
        }

        return flag;
    }

    @Override
    public Boolean HTTPPostRequest(ModelUser user) {
        Boolean flag=false;
        try{
            openConnection();
            if(isConnectionOpen){
                Message="HttpConnection Open Successfully.";
                /*httpURLConnection.setDoOutput(true);
                httpURLConnection.setChunkedStreamingMode(0);
                OutputStream out=new BufferedOutputStream(httpURLConnection.getOutputStream());*/
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "text/plain");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setChunkedStreamingMode(0);
                OutputStreamWriter outputPost=new OutputStreamWriter(httpURLConnection.getOutputStream());
                String stringToSend = URLEncoder.encode(user.getUserID(), "UTF-8");
                outputPost.write("UserID="+stringToSend);
                stringToSend = URLEncoder.encode(user.getEmail(), "UTF-8");
                outputPost.write("Email="+stringToSend);
                stringToSend = URLEncoder.encode(user.getMobile(), "UTF-8");
                outputPost.write("Mobile="+stringToSend);
                stringToSend = URLEncoder.encode(user.getUserPass(), "UTF-8");
                outputPost.write("UserPass="+stringToSend);
                outputPost.close();
                flag=true;
            } else{
                Message="HttpConnection failed.";
            }
        }catch (Exception ex){
            isError=true;
            Message=ex.getMessage();
            flag=false;
        }
        finally {
            httpURLConnection.disconnect();
        }
        return flag;
    }
}
