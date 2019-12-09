package com.example.cameraactivity.SnipperCode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.camera2.CameraDevice;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraActivity extends Activity{
    private Boolean isCameraAvailable=false;
    public Boolean getCameraAvailable() {return isCameraAvailable;}
    private String Message="";
    public String getMessage() {return Message;}
    private Bitmap bmp;
    public Bitmap getBmp() {return bmp;}

    public boolean checkCameraHardware(Context context){
        isCameraAvailable=false;
        try{
            if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                Message="Camera available on device.";
                isCameraAvailable=true;
            }
            else
            {
                Message="Camera not available on device.";
                isCameraAvailable=false;
            }
        }catch(Exception ex){
            Message=ex.getMessage();
            isCameraAvailable=false;
        }
        return isCameraAvailable;
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void dispatchTakePictureIntent(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity( activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }


}