package swingpeek;

import java.awt.EventQueue;
import javax.swing.*;

public class SwingShowImg{

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                JFrame frame = new ImageViewerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class ImageViewerFrame extends JFrame{
    public ImageViewerFrame(){
        setTitle("ImageViewer");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        label = new JLabel();
        add(label);
        label.setIcon(new ImageIcon("D:\\test.jpg"));


//        chooser = new JFileChooser();
//        chooser.setCurrentDirectory(new File("."));
//        JMenuBar menubar = new JMenuBar();
//        setJMenuBar(menubar);
//        JMenu menu = new JMenu("File");
//        menubar.add(menu);
//        JMenuItem openItem = new JMenuItem("Open");
//        menu.add(openItem);
//        JMenuItem exitItem = new JMenuItem("Close");
//        menu.add(exitItem);
//        openItem.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // TODO Auto-generated method stub
//                int result = chooser.showOpenDialog(null);
//                if(result == JFileChooser.APPROVE_OPTION){
//                    String name = chooser.getSelectedFile().getPath();
//                    System.out.println("--------------------------"+name);
//                    label.setIcon(new ImageIcon(name));
//                }
//            }
//        });
//        exitItem.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                // TODO Auto-generated method stub
//                System.exit(0);
//            }
//        });
    }
    private JLabel label;
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 400;
}