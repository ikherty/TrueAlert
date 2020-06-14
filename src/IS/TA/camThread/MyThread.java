package IS.TA.camThread;

import static IS.TA.camThread.VideoCap.getRectFrameFromCam;
import java.awt.Image;
import java.awt.Panel;

/**
 *
 * @author Petrenko Valentina
 */
public class MyThread extends Thread {

    private Panel panel;

    public MyThread(String name, Panel panel) {
        super(name);
        this.panel = panel;
    }

    public void run() {
        System.out.printf("%s started... \n", Thread.currentThread().getName());
        Image img = null;
        try {
            while (true) {
                img = getRectFrameFromCam();
                if (img == null) {
                    break;
                }
                panel.getGraphics().drawImage(img, 0, 0, null);
                Thread.sleep(100 / 3);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
