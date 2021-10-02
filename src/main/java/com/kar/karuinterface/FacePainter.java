package com.kar.karuinterface;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.Webcam;
import java.awt.image.DataBufferByte;
import org.opencv.core.CvType;

/**
 * Paint troll smile on all detected faces.
 *
 * @author Bartosz Firyn (SarXos)
 */
public class FacePainter implements Runnable, WebcamPanel.Painter {

    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private static final Stroke STROKE = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, new float[]{1.0f}, 0.0f);

    private Webcam webcam = null;
    private WebcamPanel.Painter painter = null;
    private CascadeClassifier faceDetector;
    private Mat image;
    private MatOfRect faceDetections;
    private Rect[] faceDetectionsArray;

    public FacePainter(Webcam webcam, WebcamPanel panel) throws IOException {
        super();
        loadLibraries();

        faceDetector = new CascadeClassifier();

        faceDetector.load("C:\\haarcascade_frontalface_alt.xml");

        this.webcam = webcam;
        panel.setPainter(this);
        painter = panel.getDefaultPainter();

        EXECUTOR.execute(this);
    }

    @Override
    public void run() {
        while (true) {
            if (!webcam.isOpen()) {
                return;
            }
            faceDetections = new MatOfRect();
            BufferedImage img = webcam.getImage();
            if (img != null) {
                image = bufferedImageToMat(img);
                faceDetector.detectMultiScale(image, faceDetections);
            }
            faceDetectionsArray = faceDetections.toArray();
        }
    }

    @Override
    public void paintPanel(WebcamPanel panel, Graphics2D g2) {
        if (painter != null) {
            painter.paintPanel(panel, g2);
        }
    }

    @Override
    public void paintImage(WebcamPanel panel, BufferedImage image, Graphics2D g2) {

        if (painter != null) {
            painter.paintImage(panel, image, g2);
        }

        if (faceDetections == null) {
            return;
        }
        for (Rect rect : faceDetectionsArray) {
            int dx = (int) (0.1 * rect.width);
            int dy = (int) (0.2 * rect.height);
            int x = (int) rect.x - dx;
            int y = (int) rect.y - dy;
            int w = (int) rect.width + 2 * dx;
            int h = (int) rect.height + dy;

            g2.setStroke(STROKE);
            g2.setColor(Color.RED);
            g2.drawRect(x, y, w, h);
            g2.setColor(Color.YELLOW);
            g2.drawString("Human", 3 / 2 * x, y - 3);
        }
    }

    private void loadLibraries() {
        String openCvPath = "C:\\Users\\Ahmet İlter ŞAHİN\\Documents\\NetBeansProjects\\KARUInterface\\java\\x64\\";
        System.load(openCvPath + Core.NATIVE_LIBRARY_NAME + ".dll");
    }

    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
}
