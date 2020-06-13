/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IS.TA.camThread;

import static IS.TA.camThread.VideoCap.getFrameFromCam;
import static IS.TA.camThread.VideoCap.getRectFrameFromCam;
import java.awt.Panel;
import javax.swing.JPanel;

/**
 *
 * @author qw
 */

public class JThread extends Thread {
    private Panel panel;
    public JThread(String name, Panel panel){
        super(name);
        this.panel=panel;
        
    }
      
    public void run(){
          
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try{
            while(true){
//            getRectFrameFromCam();
            panel.getGraphics().drawImage(getFrameFromCam(), 0, 0, null);
        //repaint();
            Thread.sleep(0);
            }
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s fiished... \n", Thread.currentThread().getName());
    }
}