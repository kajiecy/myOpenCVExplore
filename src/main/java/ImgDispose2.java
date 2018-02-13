import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImgDispose2 {
    static {
        System.loadLibrary("opencv_java320");
        //注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
        //-Djava.library.path=$PROJECT_DIR$\opencv\x64
    }

    public static void main(String[] args){
        String name = "1";
        Mat image = Imgcodecs.imread("C:\\Users\\85141\\Pictures\\openCV\\test"+name+".jpg");

        Mat grayImg = image.clone();

        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15,15));
        //灰化图像
        Imgproc.cvtColor(image,grayImg,Imgproc.COLOR_RGB2GRAY);

//        模糊降噪
//        第一个参数表示图像输入
//        第二个参数表述图像输出
//        第三个参数表示低阈值
//        第四个参数表示高阈值
//        在Canny边缘检测算法中，将图像中的点归为三类：
//        被抑制点
//          灰度梯度值 < 低阈值
//        弱边缘点
//          低阈值 <= 灰度梯度值 <= 高阈值
//        强边缘点
//          高阈值 < 灰度梯度值

        Imgproc.blur(grayImg, grayImg , new Size(7,7),new Point(-1, -1),1);




        Imgcodecs.imwrite("C:\\Users\\85141\\Pictures\\openCV\\grayeffect.jpg", grayImg);
        Imgproc.Canny(grayImg,grayImg,14,18);

        Imgcodecs.imwrite("C:\\Users\\85141\\Pictures\\openCV\\grayeffect2.jpg", grayImg);
    }
}
