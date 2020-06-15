package IS.TA.camThread;

import static IS.TA.camThread.VideoCap.getBodyRectFrameFromCam;
import static IS.TA.camThread.VideoCap.getFaceRectFrameFromCam;
import java.awt.Image;
import java.awt.Panel;
import javax.swing.JRadioButton;

/**
 *
 * @author Petrenko Valentina,denis
 */
public class MyThread extends Thread {

    private Panel panel;
    private JRadioButton radioButton1,radioButton2;

    public MyThread(String name, Panel panel,JRadioButton radioButton1,JRadioButton radioButton2) {
        super(name);
        this.panel = panel;
        this.radioButton1 = radioButton1;
        this.radioButton2 = radioButton2;
    }

    public void run() {
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        Image img = null;
        try {
            while (true) {
                if(radioButton1.isSelected())
                    img = getBodyRectFrameFromCam();
                if(radioButton2.isSelected())
                    img = getFaceRectFrameFromCam();
                if (img == null) {
                    break;
                }
                Image modiImg = img.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH); //изменение размера 
                panel.getGraphics().drawImage(modiImg, 0, 0, null);
                Thread.sleep(100/3);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
