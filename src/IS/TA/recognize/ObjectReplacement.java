/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IS.TA.recognize;

import static IS.TA.recognize.ObjectDetect.MatToBufferedImage;
import static IS.TA.recognize.ObjectDetect.colorRGB;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author denis
 */
//без javaFX не получиться сделать 
public class ObjectReplacement extends ObjectDetect {
    
    public static Mat faceReplacement(Mat frame)
    {
        System.load("/Library/Java/Extensions/opencv4/libopencv_java420.dylib");
        Mat img = frame;
        if (img.empty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Не удалось загрузить изображение", "Предупреждение", JOptionPane.WARNING_MESSAGE);//варнинг
            return frame;
        }
        CascadeClassifier frontalface = new CascadeClassifier();
        String path = "/usr/local/Cellar/opencv/4.2.0_3/share/opencv4/haarcascades/";//путь к классификаторам
        String name1 = "haarcascade_frontalface_alt.xml";//haarcascade_frontalface_alt.xml для лиц
        if (!frontalface.load(path + name1)) {
            JOptionPane.showMessageDialog(new JFrame(), "Не удалось загрузить классификатор " + name1, "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return frame;
        }
        Mat img2 = Imgcodecs.imread("/Users/denis/Desktop/1562318458_preview_kavo-valakas.jpg");//436 x 436 pixel
        MatOfRect faceReplace = new MatOfRect();
        frontalface.detectMultiScale(img, faceReplace);
        
        //выделение в квадрат
        for (Rect r : faceReplace.toList()) {//размер квадрата изменяется за счет размера лица , чем ближе лицо , тем больше квадрат
            //возвращает матрицу, содержащую элементы из прямоугольного диапазона
            Mat roi = img.submat(new Rect(r.x, r.y, 436, 436));//436 - это размер фотографии("каво?")
             //в режиме реального времени отображается сторона квадрата
            System.out.println("weight = "+r.width); 
            img2.copyTo(roi);//вставляем фото (каво?) в фрейм
            Imgproc.rectangle(img, r, colorRGB(255, 255, 255), 2);//выделение лиц в прямоугольники
        }
        return img;
    }
    
}
