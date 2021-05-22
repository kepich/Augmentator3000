package model.brightness;

import model.AugmentationMethod;
import model.AugmentationMethodFactory;
import model.AugmentationMethodType;

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
        for (float brightness = brightnessFrom; brightness < brightnessTo; brightness += brightnessStep) {
            Thread thread = new Thread(AugmentationMethodFactory.createBrightnessMethodThread(brightness, image, storage));
            thread.start();
            threads.add(thread);
            waitAllThreads();
        }
        joinAllThreads();
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
        return (int) ((brightnessTo - brightnessFrom) / brightnessStep);
    }
}
