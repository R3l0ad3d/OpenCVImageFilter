package test.net.atshq.ndktest.ImageProcess;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;


public class ImageBlurClass {
    //Image Blur
    public static Bitmap BlurAverageImage(Bitmap bitmap){
        Mat src = bitmapToMat(bitmap);
        Mat dst = new Mat();

        // Creating the Size and Point objects
        Size size = new Size(45, 45);
        Point point = new Point(20, 30);

        Imgproc.blur(src, dst, size, point, Core.BORDER_DEFAULT);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;
    }

    public static Bitmap GaussianBlurImage(Bitmap bitmap){
        Mat src = bitmapToMat(bitmap);
        Mat dst = new Mat(bitmap.getWidth(),bitmap.getHeight(),CvType.CV_8UC1);

        Size size = new Size(45,45);

        Imgproc.GaussianBlur(src,dst,size,0);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;
    }

    public static Bitmap MedianBlurImage(Bitmap bitmap){
        Mat src = bitmapToMat(bitmap);
        Mat dst = new Mat(bitmap.getWidth(),bitmap.getHeight(),CvType.CV_8UC1);


        Imgproc.medianBlur(src,dst,25);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;
    }
    //Bitmap to matrix convert
    protected static Mat bitmapToMat(Bitmap bitmap){
        //bitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
        Mat mat=new Mat(bitmap.getWidth(),bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap,mat);

        return mat;
    }
}
