package model;

import model.brightness.BrightnessMethodCPU;
import model.brightness.BrightnessMethodThread;
import model.scaling.ScalingMethodCPU;
import model.scaling.ScalingMethodGPU;
import model.scaling.ScalingMethodThread;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class AugmentationMethodFactory {
    private static final boolean IS_CPU = true;

    public static ScalingMethodThread createScalingMethodThread(double xScale, double yScale, BufferedImage image, Vector<BufferedImage> storage){
        return (IS_CPU) ? new ScalingMethodCPU(xScale, yScale, image, storage) : new ScalingMethodGPU(xScale, yScale, image, storage);
    }

    public static BrightnessMethodThread createBrightnessMethodThread(float brightness, BufferedImage image, Vector<BufferedImage> storage) {
        return (IS_CPU) ? new BrightnessMethodCPU(brightness, image, storage) : new BrightnessMethodCPU(brightness, image, storage);
    }
}
