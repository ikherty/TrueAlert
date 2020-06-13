/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IS.TA.GUI;

import IS.TA.recognize.ObjectDetect;
import static IS.TA.recognize.ObjectDetect.MatToBufferedImage;
import static IS.TA.recognize.ObjectDetect.frontalface_alt;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author qw
 */

//тестовый вариант получения jframe из Mat frame
public class Main extends JPanel {
//    int count=0;
//  final int LIMIT = 10000;

    /**
     * @param args the command line arguments
     */
    public void paint(Graphics g) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Mat img = Imgcodecs.imread("/home/qw/test.jpg"); //для теста на одной картинке
//        BufferedImage bufferedImage = MatToBufferedImage(img);
        
        g.drawImage(qa(g), 20, 20, this);
//        repaint();

    }

    public Image qa(Graphics g) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
//        Graphics g;
        BufferedImage bufferedImage=null;
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
        } else {
            Mat frame = new Mat();
//            while (true) {
            if (camera.read(frame)) {
                System.out.println("hi");
//                    count++;
//                    if(count < LIMIT){
                Mat newframe = frontalface_alt(frame);//выделение лиц в прямоугольники
                Mat img = Imgcodecs.imread("/home/qw/test.jpg"); //для теста на одной картинке
                bufferedImage = MatToBufferedImage(newframe);//frontalface_alt(frame));
//                g = bufferedImage.getGraphics();
//                g.drawImage(bufferedImage, 200, 200, this);
                //pause();
//       }
            }
//            }
        }
        camera.release();
//        repaint();
        return bufferedImage;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Main());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setVisible(true);
    }
}
//рабочий пример
//import java.awt.Graphics;
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class Main extends JPanel {
//
//   public void paint(Graphics g) {
//      Image img = createImageWithText();
//      g.drawImage(img, 20,20,this);
//   }
//
//   private Image createImageWithText() {
//      BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
//      Graphics g = bufferedImage.getGraphics();
//
//      g.drawString("www.tutorialspoint.com", 20,20);
//      g.drawString("www.tutorialspoint.com", 20,40);
//      g.drawString("www.tutorialspoint.com", 20,60);
//      g.drawString("www.tutorialspoint.com", 20,80);
//      g.drawString("www.tutorialspoint.com", 20,100);
//      
//      return bufferedImage;
//   }
//   
//   public static void main(String[] args) {
//      JFrame frame = new JFrame();
//      frame.getContentPane().add(new Main());
//
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      frame.setSize(200, 200);
//      frame.setVisible(true);
//   }
//}
