package model.scaling;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.util.Vector;

import static utils.ImageUtils.bufferedImage2Mat;
import static utils.ImageUtils.mat2BufferedImage;

public class ScalingMethodCPU extends ScalingMethodThread {

    public ScalingMethodCPU(double xScale, double yScale, BufferedImage image, Vector<BufferedImage> storage) {
        super(xScale, yScale, image, storage);
    }

    @Override
    public void run() {
        double xMul = Math.exp(xScale);
        double yMul = Math.exp(yScale);

        int resultWidth = (int) (image.getWidth() * xMul);
        int resultHeight = (int) (image.getHeight() * yMul);

        Mat imageMat = bufferedImage2Mat(image);
        Imgproc.resize(imageMat, imageMat, new Size(resultWidth, resultHeight));
        BufferedImage resultImage = mat2BufferedImage(imageMat);

        writeFile(resultImage);
    }
}
