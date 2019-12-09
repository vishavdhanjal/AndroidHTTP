package com.example.cameraactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cameraactivity.SnipperCode.CameraActivity;
import com.example.cameraactivity.SnipperCode.CameraPreview;

public class MainActivity extends AppCompatActivity {
    Camera camera;
    String Message="";
    TextView txtMessage;
    private CameraPreview mPreview;
    ImageView imgView;
    Bitmap bmp;
    Boolean isPhotoTaken=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessage=findViewById(R.id.txtMessage);
        imgView=findViewById(R.id.imgView);

        txtMessage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cameraAvailability();
                        txtMessage.setText(Message);
                        //while(!isPhotoTaken){}
                        if(isPhotoTaken){
                            txtMessage.setText("");
                            imgView.setImageBitmap(bmp);
                        }
                    }
                }
        );
    }


    private void cameraAvailability(){
        isPhotoTaken=false;
        try{
            CameraActivity cameraActivity=new CameraActivity();
            cameraActivity.checkCameraHardware(this);
            Message=cameraActivity.getMessage();
            cameraActivity.dispatchTakePictureIntent(this);

            isPhotoTaken=true;
            bmp=cameraActivity.getBmp();
        }catch(Exception ex){
            Message=ex.getMessage();
        }
    }
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imgView.setImageBitmap(imageBitmap);
            }

        }catch (Exception ex){
            Message=ex.getMessage();
        }
    }
}
