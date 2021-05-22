package model;

import utils.MyLogger;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

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
            int width = image.getWidth();
            int height = image.getHeight();
            int fin_brightness = (int) brightness;

            //Thread thread = new Thread(() -> {
                BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                int red, green, blue, rgb;
                for (int row = 0; row < height; row++) {
                    for (int col = 0; col < width; col++) {
                        red = (image.getRGB(col, row) >> 16) & 0xFF;
                        green = (image.getRGB(col, row) >> 8) & 0xFF;
                        blue = image.getRGB(col, row) & 0xFF;
                        rgb = boardAdd(red, fin_brightness);
                        rgb = (rgb << 8) + boardAdd(green,  fin_brightness);
                        rgb = (rgb << 8) + boardAdd(blue, fin_brightness);
                        resultImage.setRGB(col, row, rgb);
                    }
                }

                writeFile(resultImage);
            //});


            //thread.start();
            //threads.add(thread);

            //waitAllThreads();
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
        return (int) ((brightnessTo - brightnessFrom) / brightnessStep);
    }

    private int boardAdd(int a, int b){
        int res = a + b;

        if (res > 255){
            return 255;
        }

        return Math.max(res, 0);
    }
}
