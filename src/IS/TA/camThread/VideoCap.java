/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IS.TA.camThread;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Petrenko Valentina
 */


public class VideoCap {
    //public static void main(String args[]) {
    public static void ShowVideo (){//статический метод для вызова из главного фрейма
    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    	VideoCapture camera = new VideoCapture(0);
    	
    	if(!camera.isOpened()){
    		//System.out.println("Error");
                JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение",
                JOptionPane.WARNING_MESSAGE);
    	}
    	else {
    		Mat frame = new Mat();
    	    while(true){
    	    	if (camera.read(frame)){
                    //вывод информации о потоке
//    	    		System.out.println("Frame Obtained");
//    	    		System.out.println("Captured Frame Width " + 
//    	    		frame.width() + " Height " + frame.height());
    	    		HighGui.imshow("True Alert", frame);
                        HighGui.waitKey(2);
    	    	}
    	    }	
    	} 
    	camera.release();
    }
}   
// Вероятно, этот код пригодится для встраивания в главное окно
/*import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import org.opencv.core.Mat;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
    private static BufferedImage matToBufferedImage(Mat frame) {
        int type = 0;
        if (frame.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (frame.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(frame.width(), frame.height(), type);
        WritableRaster raster = image.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        frame.get(0, 0, data);

        return image;
    }

    private static void showWindow(BufferedImage img) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(img.getWidth(), img.getHeight() + 30);
        frame.setTitle("Image captured");
        frame.setVisible(true);
    }
}

или из OpenCV_i_Java_Obrabotka_izobrazheniy_i_kompyuternoe_zrenie_2018.pdf стр.119+-

*/