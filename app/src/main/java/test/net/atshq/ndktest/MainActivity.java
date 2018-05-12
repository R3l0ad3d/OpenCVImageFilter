package test.net.atshq.ndktest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.OpenCVLoader;
import org.opencv.imgproc.Imgproc;

import test.net.atshq.ndktest.ImageProcess.ImageBlurClass;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG=MainActivity.class.getName();
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    static {
        if(OpenCVLoader.initDebug()){
            Log.d(TAG,"OpenCV Load Success ....");
        }else {
            Log.d(TAG,"OpenCV Load Failed ....");
        }
    }


    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.ivPreview);

        bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.photo);

        //bitmap=ImageBlurClass.GaussianBlurImage(bitmap);

        //bitmap=ImageConvertMat.ThreshImage(bitmap,Imgproc.THRESH_BINARY);

        //bitmap=ImageConvertMat.GrayscaleImage(bitmap);

        //bitmap=ImageConvertMat.GrayscaleImage(bitmap);

        //bitmap=ImageConvertMat.SharpnessImage(bitmap);

        //bitmap=ImageConvertMat.EnhancedContrastImage(bitmap);

        bitmap = ImageConvertMat.ConvolutionImage(bitmap);

        imageView.setImageBitmap(bitmap);

    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
