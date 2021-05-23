package model.scaling;

import utils.MethodThread;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class ScalingMethodThread extends MethodThread {
    protected double xScale;
    protected double yScale;

    public ScalingMethodThread(double xScale, double yScale, BufferedImage image, Vector<BufferedImage> storage) {
        super(storage, image);
        this.xScale = xScale;
        this.yScale = yScale;
    }
}
