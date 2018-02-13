import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImgDispose1 {
    static {
        System.loadLibrary("opencv_java320");
        //注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
        //-Djava.library.path=$PROJECT_DIR$\opencv\x64
    }

    public static void main(String[] args){
        String name = "1";
        Mat image = Imgcodecs.imread("C:\\Users\\85141\\Pictures\\openCV\\test"+name+".jpg");

        Mat fushiImg = image.clone();
        Mat fatImg = image.clone();
        Mat fuzzyImg = image.clone();

        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15,15));

        //膨胀
        Imgproc.dilate(image, fatImg, element, new Point(-1, -1), 1);
        //腐蚀
        Imgproc.erode(image, fushiImg, element, new Point(-1, -1), 1);
        //模糊
        Imgproc.blur(image, fuzzyImg , new Size(15,15),new Point(-1, -1),1);

        String filename = "C:\\Users\\85141\\Pictures\\openCV\\reslut"+name+".jpg";
        System.out.println(String.format("Writing %s", filename));
        System.out.println(filename);

        Imgcodecs.imwrite("C:\\Users\\85141\\Pictures\\openCV\\fateffect.jpg", fatImg);
        Imgcodecs.imwrite("C:\\Users\\85141\\Pictures\\openCV\\fushieffect.jpg", fushiImg);
        Imgcodecs.imwrite("C:\\Users\\85141\\Pictures\\openCV\\fuzzyeffect.jpg", fuzzyImg);
    }
}
