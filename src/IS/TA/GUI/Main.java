/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IS.TA.GUI;

import static IS.TA.camThread.VideoCap.getFrameFromCam;
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
//        qa(g);
        g.drawImage(getFrameFromCam(), 120, 120, this);
        repaint();
    }

    public void qa(Graphics g) {

        
        repaint();
//        return bufferedImage;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Main());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1200);
        frame.setVisible(true);
    }
}
