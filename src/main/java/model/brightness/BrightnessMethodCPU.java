package model.brightness;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class BrightnessMethodCPU extends BrightnessMethodThread {

    public BrightnessMethodCPU(double brightness, BufferedImage image, Vector<BufferedImage> storage) {
        super(brightness, image, storage);
    }

    @Override
    public void run() {
        int width = image.getWidth();
        int height = image.getHeight();
        int fin_brightness = (int) brightness;

        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int red, green, blue, rgb;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                red = (image.getRGB(col, row) >> 16) & 0xFF;
                green = (image.getRGB(col, row) >> 8) & 0xFF;
                blue = image.getRGB(col, row) & 0xFF;
                rgb = boardAdd(red, fin_brightness);
                rgb = (rgb << 8) + boardAdd(green, fin_brightness);
                rgb = (rgb << 8) + boardAdd(blue, fin_brightness);
                resultImage.setRGB(col, row, rgb);
            }
        }

        writeFile(resultImage);
    }

    private int boardAdd(int a, int b){
        int res = a + b;

        if (res > 255){
            return 255;
        }

        return Math.max(res, 0);
    }
}