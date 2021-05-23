package model.brightness;

import utils.MethodThread;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class BrightnessMethodThread extends MethodThread {
    protected final double brightness;

    public BrightnessMethodThread(double brightness, BufferedImage image, Vector<BufferedImage> storage) {
        super(storage, image);
        this.brightness = brightness;
    }
}
