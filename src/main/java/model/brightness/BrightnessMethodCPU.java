package model.brightness;

import org.opencv.core.Mat;
import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class BrightnessMethodCPU extends BrightnessMethodThread {

    public BrightnessMethodCPU(double brightness, BufferedImage image, Vector<BufferedImage> storage) {
        super(brightness, image, storage);
    }

    @Override
    public void run() {
        writeFile(modify((int) brightness));
    }

    private BufferedImage modify(int brightness){
        Mat image = ImageUtils.bufferedImage2Mat(this.image);
        image.convertTo(image, -1, 1, brightness);
        return ImageUtils.mat2BufferedImage(image);
    }
}