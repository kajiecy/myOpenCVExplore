import org.opencv.videoio.VideoCapture;

public class VideoTest {
    static {
        System.loadLibrary("opencv_java320");
        //注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
        //-Djava.library.path=$PROJECT_DIR$\opencv\x64
    }

    VideoCapture capture = new VideoCapture();
}
