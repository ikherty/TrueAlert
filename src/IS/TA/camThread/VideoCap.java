package IS.TA.camThread;

import static IS.TA.recognize.ObjectDetect.MatToBufferedImage;
import static IS.TA.recognize.ObjectDetect.frontalface_alt;
import java.awt.Image;
import java.awt.image.BufferedImage;
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

    public static Image getFrameFromCam() {//возвращает фрейм
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        BufferedImage bufferedImage = null;
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
        } else {
            Mat frame = new Mat();
            if (camera.read(frame)) {
                bufferedImage = MatToBufferedImage(frame);
            }
        }
        camera.release();
        return bufferedImage;
    }

    public static Image getRectFrameFromCam() {//возвращает обработанный фрейм
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        BufferedImage bufferedImage = null;
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return null;
        } else {
            Mat frame = new Mat();
            if (camera.read(frame)) {
                bufferedImage = MatToBufferedImage(frontalface_alt(frame));
            }
        }
        camera.release();
        return bufferedImage;
    }

    //public static void main(String args[]) {//для теста этого метода
    public static void ShowVideo() {//статический метод для вызова из главного фрейма
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);

        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
        } else {
            Mat frame = new Mat();
            while (true) {
                if (camera.read(frame)) {
                    Mat newframe = frontalface_alt(frame);//выделение лиц в прямоугольники
                    HighGui.imshow("True Alert", newframe);//вывод в новом окне обработанного фрейма
                    HighGui.waitKey(2);//2-delay
                }
            }
        }
        camera.release();
    }
}
