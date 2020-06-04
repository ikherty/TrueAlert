/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IS.TA.camThread;
        
import org.opencv.core.Core;  
import org.opencv.core.Mat;  
import org.opencv.core.Size;  
import org.opencv.videoio.VideoCapture;  
import org.opencv.videoio.VideoWriter;  
import org.opencv.videoio.Videoio; 
/**
 *
 * @author qw
 */
public class Test {
     public static void main (String args[]) throws InterruptedException{  
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);  
        VideoCapture camera = new VideoCapture();  
          
        VideoWriter writer = new VideoWriter("c:/new/test.avi", VideoWriter.fourcc('X', 'V', 'I', 'D'), 15, new Size(1280, 720), true);  
          
        camera.set(Videoio.CAP_PROP_FRAME_WIDTH, 1280);  
        camera.set(Videoio.CAP_PROP_FRAME_HEIGHT, 720);  
          
        if(!camera.isOpened()){  
            System.out.println("Error");  
        }  
        else {  
            int index = 0;  
            Mat frame = new Mat();  
                 
            while(true){  
                if (camera.read(frame)){  
                    System.out.println("Captured Frame Width " + frame.width() + " Height " + frame.height());  
                    writer.write(frame);  
                    Thread.currentThread().sleep(66);  
                    index++;  
                }  
  
                if (index > 200) {  
                    break;  
                }  
                  
                frame.release();  
            }     
        }  
        writer.release();  
        camera.release();  
    } 
}
 