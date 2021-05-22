package model;

import model.brightness.BrightnessMethodCPU;
import model.brightness.BrightnessMethodThread;
import model.scaling.ScalingMethodCPU;
import model.scaling.ScalingMethodThread;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class AugmentationMethodFactory {
    private static final boolean IS_GPU = true;

    public static ScalingMethodThread createScalingMethodThread(double xScale, double yScale, BufferedImage image, Vector<BufferedImage> storage){
        return (IS_GPU) ? new ScalingMethodCPU(xScale, yScale, image, storage) : null;
    }

    public static BrightnessMethodThread createBrightnessMethodThread(float brightness, BufferedImage image, Vector<BufferedImage> storage) {
        return (IS_GPU) ? new BrightnessMethodCPU(brightness, image, storage) : null;
    }
}
