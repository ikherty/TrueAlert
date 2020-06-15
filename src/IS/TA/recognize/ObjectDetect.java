package IS.TA.recognize;

/**
 *
 * @author Petrenko Valentina
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class ObjectDetect {

    public static Scalar colorRGB(double red, double green, double blue) {
        return new Scalar(blue, green, red);
    }

    public static BufferedImage MatToBufferedImage(Mat m) {
        if (m == null || m.empty()) {
            return null;
        }
        if (m.depth() == CvType.CV_8U) {
        } else if (m.depth() == CvType.CV_16U) { // CV_16U => CV_8U
            Mat m_16 = new Mat();
            m.convertTo(m_16, CvType.CV_8U, 255.0 / 65535);
            m = m_16;
        } else if (m.depth() == CvType.CV_32F) { // CV_32F => CV_8U
            Mat m_32 = new Mat();
            m.convertTo(m_32, CvType.CV_8U, 255);
            m = m_32;
        } else {
            return null;
        }
        int type = 0;
        if (m.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (m.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        } else if (m.channels() == 4) {
            type = BufferedImage.TYPE_4BYTE_ABGR;
        } else {
            return null;
        }

        byte[] buf = new byte[m.channels() * m.cols() * m.rows()];
        m.get(0, 0, buf);
        byte tmp = 0;
        if (m.channels() == 4) { // BGRA => ABGR
            for (int i = 0; i < buf.length; i += 4) {
                tmp = buf[i + 3];
                buf[i + 3] = buf[i + 2];
                buf[i + 2] = buf[i + 1];
                buf[i + 1] = buf[i];
                buf[i] = tmp;
            }
        }

        BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buf, 0, data, 0, buf.length);
        return image;
    }

    public static void showImage(Mat img, String title) {
        BufferedImage im = MatToBufferedImage(img);
        if (im == null) {
            return;
        }

        int w = 1000, h = 600;
        JFrame window = new JFrame(title);
        window.setSize(w, h);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon(im);
        JLabel label = new JLabel(imageIcon);

        JScrollPane pane = new JScrollPane(label);
        window.setContentPane(pane);

        if (im.getWidth() < w && im.getHeight() < h) {
            window.pack();
        }
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

<<<<<<< Updated upstream
    public static Mat frontalface_alt(Mat frame) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img = frame;
=======
    
    public static Mat face_Det(Mat frame)
    {
        System.load("/Library/Java/Extensions/opencv4/libopencv_java420.dylib");
        Mat img = frame;
        if (img.empty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Не удалось загрузить изображение", "Предупреждение", JOptionPane.WARNING_MESSAGE);//варнинг
            return frame;
        }
        CascadeClassifier upperbody_detector = new CascadeClassifier();
        String path = "/usr/local/Cellar/opencv/4.2.0_3/share/opencv4/haarcascades/";//путь к классификаторам
        String name = "haarcascade_frontalface_alt.xml";
        if (!upperbody_detector.load(path + name)) {
            JOptionPane.showMessageDialog(new JFrame(), "Не удалось загрузить классификатор " + name, "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return frame;
        }
        MatOfRect body = new MatOfRect();
        upperbody_detector.detectMultiScale(img, body);
        for (Rect r : body.toList()) {//выделение в прямоугольник
            Imgproc.rectangle(img, r, colorRGB(255, 255, 255), 2);//выделение лиц в прямоугольники
        }
        return img;

    }
            
            
    public static Mat upperbody_Det(Mat frame) {  //нахождение 
        System.load("/Library/Java/Extensions/opencv4/libopencv_java420.dylib");
        Mat img = frame;//Imgcodecs.imread("/home/qw/test.jpg"); //для теста на одной картинке
>>>>>>> Stashed changes
        if (img.empty()) {
            JOptionPane.showMessageDialog(new JFrame(), "Не удалось загрузить изображение", "Предупреждение", JOptionPane.WARNING_MESSAGE);//варнинг
            return frame;
        }
        CascadeClassifier upperbody_detector = new CascadeClassifier();
        String path = "/usr/local/Cellar/opencv/4.2.0_3/share/opencv4/haarcascades/";//путь к классификаторам
        String name = "haarcascade_upperbody.xml";//haarcascade_frontalface_alt.xml для лиц /haarcascade_upperbody.xml
        if (!upperbody_detector.load(path + name)) {
            JOptionPane.showMessageDialog(new JFrame(), "Не удалось загрузить классификатор " + name, "Предупреждение", JOptionPane.WARNING_MESSAGE);
            return frame;
        }
<<<<<<< Updated upstream
        MatOfRect faces = new MatOfRect();
        face_detector.detectMultiScale(img, faces);
        for (Rect r : faces.toList()) {//выделение в белый прямоугольник
            Imgproc.rectangle(img, r, colorRGB(255, 255, 255), 2);
=======
        MatOfRect body = new MatOfRect();
        upperbody_detector.detectMultiScale(img, body);
        for (Rect r : body.toList()) {//выделение в прямоугольник
            Imgproc.rectangle(img, r, colorRGB(255, 255, 255), 2);//выделение лиц в прямоугольники
>>>>>>> Stashed changes
        }
        return img;

    }
}
