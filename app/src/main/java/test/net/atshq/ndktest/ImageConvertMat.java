package test.net.atshq.ndktest;

import android.graphics.Bitmap;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageConvertMat {

    //Thresh image
    public static Bitmap ThreshImage(Bitmap bitmap,int Imgproc_THRESH_FLAG ){
        Mat src=bitmapToMat(bitmap);
        Mat dst = new Mat(bitmap.getWidth(),bitmap.getHeight(),CvType.CV_8UC1);

        Imgproc.threshold(src,dst,127,255,Imgproc_THRESH_FLAG); //Thresh binary image convert

        Utils.matToBitmap(dst,bitmap); //convert Mat to bitmap

        return bitmap;

    }

    //Adaptive ThreshHold Image
    public static Bitmap AdaptiveThreshHoldImage(Bitmap bitmap,int ADAPTIVE_THRESH,int Imgproc_THRESH_FLAG){
        Mat src=bitmapToMat(bitmap);
        Mat dst = new Mat(bitmap.getWidth(),bitmap.getHeight(),CvType.CV_8UC1);

        //representing the value that is to be given if pixel value is more than the threshold value.
        double maxValue = 125;

        //representing size of the pixelneighborhood used to calculate the threshold value
        int blockSize=11;

        //representing the constant used in the both methods (subtracted from the mean or weighted mean).
        double c=12;

        Imgproc.adaptiveThreshold(src,dst,maxValue,ADAPTIVE_THRESH,Imgproc_THRESH_FLAG,blockSize,c);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;

    }

    //COLORMAP
    public static Bitmap ColorMapImage(Bitmap bitmap,int Imgproc_COLORMAP_FLAG){
        //source Matrix . Convert bitmap to Matrix
        Mat src = bitmapToMat(bitmap);

        //destination Matrix
        Mat dst = new Mat();

        Imgproc.applyColorMap(src,dst,Imgproc_COLORMAP_FLAG);

        Utils.matToBitmap(dst,bitmap);
        return bitmap;
    }

    public static Bitmap GrayscaleImage(Bitmap bitmap){
        Mat src= bitmapToMat(bitmap);
        Mat dst = new Mat(bitmap.getWidth(),bitmap.getHeight(),CvType.CV_8UC1);
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
        Utils.matToBitmap(dst,bitmap);
        return bitmap;
    }

    public static Bitmap EnhancedContrastImage(Bitmap bitmap){
        //source Matrix . Convert bitmap to Matrix
        Mat src = new Mat(bitmap.getWidth(),bitmap.getHeight(),CvType.CV_8UC1);

        //destination Matrix
        Mat dst = new Mat(src.rows(),src.cols(),src.type());

        Imgproc.equalizeHist(src,dst);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;
    }

    public static Bitmap SharpnessImage(Bitmap bitmap){
        //source Matrix . Convert bitmap to Matrix
        Mat src =bitmapToMat(bitmap);

        //destination Matrix
        Mat dst = new Mat();

        Imgproc.GaussianBlur(src,dst,new Size(45,45),0);
        Core.addWeighted(src,1.5,dst,-0.5,1,dst);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;

    }

    //Convolution Image
    public static Bitmap ConvolutionImage(Bitmap bitmap){
//source Matrix . Convert bitmap to Matrix
        Mat src =bitmapToMat(bitmap);

        //destination Matrix
        Mat dst = new Mat();

        int kernelSize=3;

        Mat kernel = new Mat(kernelSize,kernelSize, CvType.CV_32F){
            {
                put(0,0,0);
                put(0,1,0);
                put(0,2,0);

                put(1,0,0);
                put(1,1,1);
                put(1,2,0);

                put(2,0,0);
                put(2,1,0);
                put(2,2,0);
            }
        };

        Imgproc.filter2D(src,dst,-1,kernel);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;
    }

    //Bitmap to matrix convert
    protected static Mat bitmapToMat(Bitmap bitmap){
        Mat mat = new Mat();
        Bitmap bmp32 = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, mat);
        Utils.bitmapToMat(bitmap,mat);

        return mat;
    }


}
