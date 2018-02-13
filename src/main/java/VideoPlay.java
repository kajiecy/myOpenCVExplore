import java.awt.EventQueue;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;



import java.awt.BorderLayout;

public class VideoPlay {

    static{
        System.loadLibrary("opencv_java320");

    }

    private JFrame frame;
    private static JLabel lblNewLabel;

    /**
     * Launch the application. 
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VideoPlay window = new VideoPlay();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //我们的操作  
        VideoCapture capture=new VideoCapture("D:\\2.mp4");//读取视频
        System.out.println(capture.isOpened());
        if (!capture.isOpened()) {
            System.out.println("Error");
        } else {
            Mat webcam_image = new Mat();
            capture.read(webcam_image);
            while (true) {
                capture.read(webcam_image);
                if( !webcam_image.empty() ){
                    BufferedImage b=mat2BufferedImage.matToBufferedImage(webcam_image);
                    lblNewLabel.setIcon(new ImageIcon(b));
                }else{
                    System.out.println("视频已结束!");
                    capture.release();
                    break;
                }

            }
        }
    }

    /**
     * Create the application. 
     */
    public VideoPlay() {
        initialize();
    }

    /**
     * Initialize the contents of the frame. 
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 1000, 800);
        frame.getContentPane().add(lblNewLabel);
    }

}