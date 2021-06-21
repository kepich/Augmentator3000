package model.brightness;

import model.MethodThread;
import org.opencv.core.Mat;
import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class BrightnessMethod extends MethodThread {
    private final double brightness;

    public BrightnessMethod(double brightness, BufferedImage image, Vector<BufferedImage> storage) {
        super(storage, image);
        this.brightness = brightness;
    }

    protected BufferedImage modify() {
        Mat image = ImageUtils.bufferedImage2Mat(this.image);
        image.convertTo(image, -1, 1, (int) brightness);
        return ImageUtils.mat2BufferedImage(image);
    }
}