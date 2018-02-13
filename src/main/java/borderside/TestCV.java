package borderside;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Administrator on 2016/12/7.
 */
public class TestCV {

    private static void testCanny(String pathOfPic) {
        Mat srcImg = Imgcodecs.imread(pathOfPic);
        if (srcImg.empty()) {
            System.out.println("Please check the path of input image!");
            return;
        }
        final int imgRows = srcImg.rows();
        final int imgCols = srcImg.cols();

        // Step1: Denoise
        Imgproc.GaussianBlur(srcImg, srcImg, new Size(3, 3), 0, Core.BORDER_DEFAULT);

        // Step2: Convert to gray
        Mat grayImg = Mat.zeros(imgRows, imgCols, CvType.CV_8UC1);
        if (srcImg.channels() == 3) {
            Imgproc.cvtColor(srcImg, grayImg, Imgproc.COLOR_BGR2GRAY);
        }
        Imgproc.medianBlur(grayImg, grayImg, 3);

        // Step3: Binary
        int maskRoiX = (int)(imgCols/12.0);
        int maskRoiY = (int)(imgRows/8.0);
        int maskRoiW = (int)(10/12.0*imgCols);
        int maskRoiH = (int)(6/8.0*imgRows);
        Rect maskRoi = new Rect(maskRoiX, maskRoiY, maskRoiW, maskRoiH);
        Mat maskSrc = new Mat(grayImg, maskRoi);

        Utils utils = new Utils();
        utils.FindAdaptiveThreshold(maskSrc, 3, 0.80);
        double thCannyLow = utils.getM_cannyLowTh();
        double thCannyHigh = utils.getM_cannyHighTh();

        Mat maskImg = Mat.zeros(imgRows, imgCols, CvType.CV_8UC1);
        Imgproc.Canny(grayImg, maskImg, thCannyLow, thCannyHigh, 3, false);

        System.out.println("Canny threshold lowth = " + thCannyLow + "\thighth = " + thCannyHigh);
        Imgcodecs.imwrite("C:\\Users\\85141\\Pictures\\openCV\\reslt1.jpg", maskImg);
    }


    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String inputImgPath = "C:\\Users\\85141\\Pictures\\openCV\\test1.jpg";
        testCanny(inputImgPath);
    }
}