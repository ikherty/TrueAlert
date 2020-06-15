package IS.TA.camThread;

//import static IS.TA.recognize.ObjectDetect.MatToBufferedImage;
//import static IS.TA.recognize.ObjectDetect.face_Det;
import static IS.TA.recognize.ObjectDetect.*;
import static IS.TA.recognize.ObjectReplacement.faceReplacement;
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
 * @author Petrenko Valentina,
 */
public class VideoCap {

    private static VideoCapture camera = null;
    private static Mat frame = new Mat();

    public static String ChoosePort() {//можно объединить с выбором директории, для выбора директории убрать ограничение "только директория"
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showDialog(new JFrame(), "Выбрать порт");
        File file = null;
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);//если камера не выбрана, уведомление
        }
        return file.toString();
    }

    public static VideoCapture initCamera(Boolean i) {
        camera = new VideoCapture();//VideoCapture(0)-камера по умолчанию
        if(i==true)
            camera.open(0);
        if(i ==false )
            camera.open(ChoosePort());
        return camera;
    }

    public static Image getBodyRectFrameFromCam() {//возвращает обработанный фрейм(тело)
        BufferedImage bufferedImage = null;
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
        } else {
            Mat frame = new Mat();
            if (camera.read(frame)) {
                bufferedImage = MatToBufferedImage(upperbody_Det(frame));
            }
        }
        return bufferedImage;
    }
    
    public static Image getFaceReplaceFrameFromCam() {//возвращает обработанный фрейм(замена лица)
        BufferedImage bufferedImage = null;
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
        } else {
            Mat frame = new Mat();
            if (camera.read(frame)) {
                bufferedImage = MatToBufferedImage(faceReplacement(frame));
            }
        }
        return bufferedImage;
    }
    

    public static Image getFaceRectFrameFromCam() {//возвращает обработанный фрейм(лицо)
        BufferedImage bufferedImage = null;
        if (!camera.isOpened()) {
            JOptionPane.showMessageDialog(new JFrame(), "Камера не подключена!", "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return null;
        } else {
            if (camera.read(frame)) {
                bufferedImage = MatToBufferedImage(face_Det(frame));
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
            while (true) {
                if (camera.read(frame)) {
                    Mat newframe = upperbody_Det(frame);//выделение лиц в прямоугольники
                    HighGui.imshow("True Alert", newframe);//вывод в новом окне обработанного фрейма
                    HighGui.waitKey(2);//2-delay
                }
            }
        }
        camera.release();
    }
}
