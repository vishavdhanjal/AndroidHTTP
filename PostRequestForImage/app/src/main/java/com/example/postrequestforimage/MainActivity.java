package com.example.postrequestforimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    String Message="";
    Boolean post = false;
    String photoPath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Restored/IMG-20200117-WA0001.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("S_path",photoPath);
/*        File f = new File(photoPath);
        File[] files = f.listFiles();
        for (File inFile : files)
            Log.d("S_path",inFile.toString());*/


        Thread thread=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                            final Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
                            String result = multipartRequest("http://vishavapp.sargunenterprises.com/Default/postImage",
                                    "", photoPath, "ImageFile");
                            Log.d("result", result);
                        }catch(Exception ex){
                            Log.d("PostError", ex.getMessage());
                        }
                    }
                }
        );
        thread.start();

    }

    protected void testPost(Bitmap bitmap) {
        try{

        }catch(Exception ex){
            Log.e("PostError", ex.getMessage());
        }finally{

        }

    }

    public String multipartRequest(String urlTo, String post, String filepath, String filefield) throws ParseException, IOException {
        File file = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(file);

        URL url = new URL(urlTo);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String auth = "Bearer " + "{token}";
        connection.setRequestProperty("Authorization", auth);

        String boundary = UUID.randomUUID().toString();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

        DataOutputStream request = new DataOutputStream(connection.getOutputStream());

        request.writeBytes("--" + boundary + "\r\n");
//        request.writeBytes("Content-Disposition: form-data; name=\"description\"\r\n\r\n");
//        request.writeBytes(fileDescription + "\r\n");

//        request.writeBytes("--" + boundary + "\r\n");
        request.writeBytes("Content-Disposition: form-data; name=\"ImageFile\"; filename=\"" + file.getName() + "\"\r\n\r\n");

        byte[] b = new byte[(int) file.length()];
        fileInputStream.read(b);
        request.write(b);
        request.writeBytes("\r\n");

        request.writeBytes("--" + boundary + "--\r\n");
        request.flush();
        int respCode = connection.getResponseCode();
        return ""+respCode;
    }
}
