package model;

import model.brightness.BrightnessMethod;
import model.gamma.GammaMethod;
import model.projection.ProjectionMethod;
import model.scaling.ScalingMethod;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class AugmentationMethodKit {
    public static ScalingMethod getScalingMethodThread(double xScale, double yScale, BufferedImage image, Vector<BufferedImage> storage) {
        return new ScalingMethod(xScale, yScale, image, storage);
    }

    public static ProjectionMethod getProjectionMethodThread(int[] lu, int[] ru, int[] rd, int[] ld,
                                                             BufferedImage image, Vector<BufferedImage> storage) {
        return new ProjectionMethod(lu, ru, rd, ld, image, storage);
    }

    public static GammaMethod getGammaMethodThread(float gamma, BufferedImage image, Vector<BufferedImage> storage) {
        return new GammaMethod(gamma, image, storage);
    }

    public static BrightnessMethod getBrightnessMethodThread(double brightness, BufferedImage image, Vector<BufferedImage> storage) {
        return new BrightnessMethod(brightness, image, storage);
    }
}
