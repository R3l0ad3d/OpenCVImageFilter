package test.net.atshq.ndktest.ImageProcess;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImageFilterClass {

    //Image Filtering
    public static Bitmap BilateralFilterImage(Bitmap bitmap){
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
        //source Matrix . Convert bitmap to Matrix
        Mat src = bitmapToMat(bitmap);

        //destination Matrix
        Mat dst = new Mat(bitmap.getWidth(),bitmap.getHeight(),CvType.CV_8UC1);

        //representing the diameter of the pixel neighborhood.
        int d=15;

        //representing the filter sigma in the color space.
        double sigmaColor=80;

        //representing the filter sigma in the coordinate space.
        double sigmaSpace=80;

        //representing the type of the border used.
        int borderType=Core.BORDER_DEFAULT;

        //convert 4 channels RGBA format to 3 channels RGB
        Imgproc.cvtColor(src,src,Imgproc.COLOR_RGBA2BGR);

        //apply bilateral filter
        Imgproc.bilateralFilter(src,dst,d,sigmaColor,sigmaSpace,borderType);

        //convert 3 channels RGB format to 4 channels RGBA
        Imgproc.cvtColor(dst,dst,Imgproc.COLOR_BGR2RGBA);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;
    }

    public static Bitmap Filter2DImage(Bitmap bitmap){

        //source Matrix . Convert bitmap to Matrix
        //Mat src = bitmapToMat(GrayscaleImage(bitmap));
        Mat src = bitmapToMat(bitmap);

        //destination Matrix
        Mat dst = new Mat();

        //representing the depth of the output image.
        int ddepth=-1;
        int kernelSize=2;
        // Creating kernel matrix
        Mat kernel = Mat.ones(kernelSize,kernelSize, CvType.CV_32F);

        for(int i = 0; i<kernel.rows(); i++) {
            for(int j = 0; j<kernel.cols(); j++) {
                double[] m = kernel.get(i, j);

                for(int k = 1; k<m.length; k++) {
                    m[k] = m[k]/(kernelSize * kernelSize);
                }
                kernel.put(i,j, m);
            }
        }

        Imgproc.filter2D(src,dst,ddepth,kernel);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;
    }

    public static Bitmap DilateImage(Bitmap bitmap){
        //source Matrix . Convert bitmap to Matrix
        Mat src = bitmapToMat(bitmap);

        //destination Matrix
        Mat dst = new Mat();

        int kernelSize=5;

        // Preparing the kernel matrix object
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new Size((kernelSize*kernelSize) + 1, (kernelSize*kernelSize)+1));

        //Apply dilate
        Imgproc.dilate(src,dst,kernel);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;

    }

    public static Bitmap ErodeImage(Bitmap bitmap){
        //source Matrix . Convert bitmap to Matrix
        Mat src = bitmapToMat(bitmap);

        //destination Matrix
        Mat dst = new Mat();

        int kernelSize=5;

        // Preparing the kernel matrix object
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new  Size((kernelSize*kernelSize) + 1, (kernelSize*kernelSize)+1));

        // Applying erode on the Image
        Imgproc.erode(src, dst, kernel);

        Utils.matToBitmap(dst,bitmap);

        return bitmap;
    }

    public static Bitmap MorphologyEx(Bitmap bitmap){
        //source Matrix . Convert bitmap to Matrix
        Mat src = bitmapToMat(bitmap);

        //destination Matrix
        Mat dst = new Mat();

        int kernelSize=5;

        // Creating kernel matrix
        Mat kernel = Mat.ones(kernelSize,kernelSize, CvType.CV_32F);

        // Applying Blur effect on the Image
        Imgproc.morphologyEx(src, dst, Imgproc.MORPH_TOPHAT, kernel);

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
