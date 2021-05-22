package model.scaling;


import java.awt.image.BufferedImage;
import java.util.Vector;

public class ScalingMethodCPU extends ScalingMethodThread {

    public ScalingMethodCPU(double xScale, double yScale, BufferedImage image, Vector<BufferedImage> storage) {
        super(xScale, yScale, image, storage);
    }

    @Override
    public void run() {
        double xMul = Math.exp(xScale);
        double yMul = Math.exp(xScale);

        int resultWidth = (int) (image.getWidth() * xMul);
        int resultHeight = (int) (image.getHeight() * yMul);

        BufferedImage resultImage = new BufferedImage(resultWidth, resultHeight, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < resultHeight; row++) {
            int sourceRow = (int) (row / yMul);
            for (int col = 0; col < resultWidth; col++) {
                resultImage.setRGB(col, row, image.getRGB((int) (col / xMul), sourceRow));
            }
        }
        writeFile(resultImage);
    }
}
