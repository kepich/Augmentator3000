package model.brightness;

import model.AugmentationMethod;
import model.AugmentationMethodType;
import utils.ThreadPool;

import java.awt.image.BufferedImage;

public class BrightnessMethod extends AugmentationMethod {
    public float brightnessFrom;
    public float brightnessTo;
    public float brightnessStep;

    public BrightnessMethod() {
        super(AugmentationMethodType.BRIGHTNESS, "Brightness");
    }

    public BrightnessMethod(float brightnessFrom, float brightnessTo, float brightnessStep) {
        super(AugmentationMethodType.BRIGHTNESS, "Brightness");

        this.brightnessFrom = brightnessFrom;
        this.brightnessTo = brightnessTo;
        this.brightnessStep = brightnessStep;
    }

    @Override
    protected void modifyImage(BufferedImage image) {
        for (float brightness = brightnessFrom; brightness <= brightnessTo; brightness += brightnessStep) {
            ThreadPool.runTask(new BrightnessMethodCPU(brightness, image, storageResult), priority);
        }
    }

    @Override
    public String toString() {
        return name + "[" + brightnessFrom + ", " + brightnessTo + "](" + brightnessStep + ")";
    }

    @Override
    public AugmentationMethod clone() {
        return new BrightnessMethod(brightnessFrom, brightnessTo, brightnessStep);
    }

    @Override
    public int getEstimatedTime() {
        return (int) ((brightnessTo - brightnessFrom) / brightnessStep + 1);
    }
}
