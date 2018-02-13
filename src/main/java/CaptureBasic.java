import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.*;
import java.awt.image.BufferedImage;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class CaptureBasic extends JPanel {
    private static final long serialVersionUID=1L;

    private BufferedImage mImg;

    static {
        System.loadLibrary("opencv_java320");
        //注意程序运行的时候需要在VM option添加该行 指明opencv的dll文件所在路径
        //-Djava.library.path=$PROJECT_DIR$\opencv\x64
    }

    private BufferedImage mat2BI(Mat mat){
        int dataSize =mat.cols()*mat.rows()*(int)mat.elemSize();
        byte[] data=new byte[dataSize];
        mat.get(0, 0,data);
        int type=mat.channels()==1?
                BufferedImage.TYPE_BYTE_GRAY:BufferedImage.TYPE_3BYTE_BGR;

        if(type==BufferedImage.TYPE_3BYTE_BGR){
            for(int i=0;i<dataSize;i+=3){
                byte blue=data[i+0];
                data[i+0]=data[i+2];
                data[i+2]=blue;
            }
        }
        BufferedImage image=new BufferedImage(mat.cols(),mat.rows(),type);
        image.getRaster().setDataElements(0, 0, mat.cols(), mat.rows(), data);

        return image;
    }

    public void paintComponent(Graphics g){
        if(mImg!=null){
            g.drawImage(mImg, 0, 0, mImg.getWidth(),mImg.getHeight(),this);
        }
    }

    public static void main(String[] args){
        try{

            Mat capImg=new Mat();
            VideoCapture capture=new VideoCapture();
            capture.open(0);
//            capture.open("D:/111.rmvb");
            System.out.println(capture.isOpened());
            int height = (int)capture.get(Videoio.CAP_PROP_FRAME_HEIGHT);
            int width = (int)capture.get(Videoio.CAP_PROP_FRAME_WIDTH);
            if(height==0||width==0){
                throw new Exception("camera not found!");
            }

            JFrame frame=new JFrame("camera");
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            CaptureBasic panel=new CaptureBasic();
            frame.setContentPane(panel);
            frame.setVisible(true);
            frame.setSize(width+frame.getInsets().left+frame.getInsets().right,
                    height+frame.getInsets().top+frame.getInsets().bottom);
            while(frame.isShowing()){
                capture.read(capImg);
//                //灰化图像
//                Imgproc.cvtColor(capImg,capImg,Imgproc.COLOR_RGB2GRAY);
//                //均值滤波
//                Imgproc.blur(capImg, capImg , new Size(7,7),new Point(-1, -1),1);
//                //边缘算法
//                Imgproc.Canny(capImg,capImg,14,18);

                new CaptureBasic().checkFace(capImg);

                panel.mImg=panel.mat2BI(capImg);
                panel.repaint();
            }
            capture.release();
            frame.dispose();
        }catch(Exception e){
            System.out.println("例外：" + e);
        }finally{
            System.out.println("--done--");
        }

    }

    public void checkFace(Mat image){
        // Create a face detector from the cascade file in the resources
        // directory.
        //CascadeClassifier faceDetector = new CascadeClassifier(getClass().getResource("haarcascade_frontalface_alt2.xml").getPath());
        //Mat image = Highgui.imread(getClass().getResource("lena.png").getPath());
        //注意：源程序的路径会多打印一个‘/’，因此总是出现如下错误
        /*
         * Detected 0 faces Writing faceDetection.png libpng warning: Image
         * width is zero in IHDR libpng warning: Image height is zero in IHDR
         * libpng error: Invalid IHDR data
         */
        //因此，我们将第一个字符去掉
        String xmlfilePath=getClass().getResource("/haarcascade_frontalface_alt2.xml").getPath().substring(1);
        CascadeClassifier faceDetector = new CascadeClassifier(xmlfilePath);
        // Detect faces in the image.
        // MatOfRect is a special container class for Rect.
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

        // Draw a bounding box around each face.
        for (Rect rect : faceDetections.toArray()) {
//        Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
    }

}
