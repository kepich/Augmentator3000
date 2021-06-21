package model.brightness;

import model.AugmentationMethod;
import model.AugmentationMethodKit;
import model.AugmentationMethodType;
import model.ThreadPool;

import java.awt.image.BufferedImage;

public class BrightnessMethodIterator extends AugmentationMethod {
    public float brightnessFrom;
    public float brightnessTo;
    public float brightnessStep;

    public BrightnessMethodIterator() {
        super(AugmentationMethodType.BRIGHTNESS, "Brightness");
    }

    public BrightnessMethodIterator(float brightnessFrom, float brightnessTo, float brightnessStep) {
        super(AugmentationMethodType.BRIGHTNESS, "Brightness");

        this.brightnessFrom = brightnessFrom;
        this.brightnessTo = brightnessTo;
        this.brightnessStep = brightnessStep;
    }

    @Override
    protected void modifyImage(BufferedImage image) {
        for (float brightness = brightnessFrom; brightness <= brightnessTo; brightness += brightnessStep) {
            ThreadPool.runTask(AugmentationMethodKit.getBrightnessMethodThread(brightness, image, storageResult), priority);
        }
    }

    @Override
    public String toString() {
        return name + "[" + brightnessFrom + ", " + brightnessTo + "](" + brightnessStep + ")";
    }

    @Override
    public AugmentationMethod clone() {
        return new BrightnessMethodIterator(brightnessFrom, brightnessTo, brightnessStep);
    }

    @Override
    public int getEstimatedTime() {
        return (int) ((brightnessTo - brightnessFrom) / brightnessStep + 1);
    }
}
