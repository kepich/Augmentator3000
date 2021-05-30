package utils;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public abstract class ImageUtils {
    public static ImageIcon getResizedImageIcon(BufferedImage bufferedImage, int resultWidth, int resultHeight){
        float multiplier = (float) resultWidth / (float) bufferedImage.getWidth();

        if (multiplier * bufferedImage.getHeight() > resultHeight) {
            multiplier = (float) resultHeight / (float) bufferedImage.getHeight();
        }

        return new ImageIcon(bufferedImage.getScaledInstance(
                (int) (bufferedImage.getWidth() * multiplier),
                (int) (bufferedImage.getHeight() * multiplier),
                Image.SCALE_SMOOTH)
        );
    }

    public static Mat bufferedImage2Mat(BufferedImage bufferedImage) {
        byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
        Mat result = new Mat(bufferedImage.getHeight(), bufferedImage.getWidth(), CvType.CV_8UC3);
        result.put(0, 0, data);

        return result;
    }

    public static BufferedImage mat2BufferedImage(Mat mat) {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, mob);
        byte[] ba = mob.toArray();
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(ba));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }
}
