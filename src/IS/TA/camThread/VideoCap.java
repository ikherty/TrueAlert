package IS.TA.camThread;

import static IS.TA.recognize.ObjectDetect.MatToBufferedImage;
import static IS.TA.recognize.ObjectDetect.frontalface_alt;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Petrenko Valentina
 */
public class VideoCap {
    private static VideoCapture camera = null;

    public static String ChoosePort() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showDialog(new JFrame(), "Выбрать порт");
        File file = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }
        return file.toString();
    }

    public static VideoCapture initCamera() {
        camera = new VideoCapture();//VideoCapture(0)-камера по умолчанию
        camera.open(ChoosePort());
        return camera;
    }
    //tryIoctl VIDEOIO(V4L2:/dev/video0)

    public static Image getFrameFromCam() {//возвращает фрейм
        BufferedImage bufferedImage = null;
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
        } else {
            Mat frame = new Mat();
            if (camera.read(frame)) {
                bufferedImage = MatToBufferedImage(frame);
            }
        }
        return bufferedImage;
    }

    public static Image getRectFrameFromCam() {//возвращает обработанный фрейм
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
        return bufferedImage;
    }
//Метод для корректного вывода потока в отдельное окно
    public static void main(String args[]) {//для теста этого метода
        camera.open(ChoosePort());
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
