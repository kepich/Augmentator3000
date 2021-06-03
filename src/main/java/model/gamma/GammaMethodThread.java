package model.gamma;

import utils.MethodThread;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class GammaMethodThread extends MethodThread {
    protected final double gamma;

    public GammaMethodThread(double gamma, BufferedImage image, Vector<BufferedImage> storage) {
        super(storage, image);
        this.gamma = gamma;
    }
}
