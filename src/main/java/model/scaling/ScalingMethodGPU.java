package model.scaling;

import com.aparapi.Kernel;
import com.aparapi.Range;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.util.Vector;

public class ScalingMethodGPU extends ScalingMethodThread {
    public ScalingMethodGPU(double xScale, double yScale, BufferedImage image, Vector<BufferedImage> storage) {
        super(xScale, yScale, image, storage);
    }

    @Override
    public void run() {
        double xMul = Math.exp(xScale);
        double yMul = Math.exp(xScale);

        int resultWidth = (int) (image.getWidth() * xMul);
        int resultHeight = (int) (image.getHeight() * yMul);

        int oldWidth = image.getWidth();
        BufferedImage resultImage = new BufferedImage(resultWidth, resultHeight, BufferedImage.TYPE_INT_RGB);

        byte[] dataSourceImage = ((DataBufferByte)(image.getRaster().getDataBuffer())).getData();
        int[] dataResultImage = ((DataBufferInt)(resultImage.getRaster().getDataBuffer())).getData();

        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int gid = getGlobalId();

                int resultRow = gid / resultWidth;
                int resultCol = gid % resultWidth;

                int sourceRow = (int) (resultRow / yMul);
                int sourceCol = (int) (resultCol / xMul);

                dataResultImage[resultRow * resultWidth + resultCol] =
                        dataSourceImage[(sourceRow * oldWidth + sourceCol) * 3] +
                                (dataSourceImage[(sourceRow * oldWidth + sourceCol) * 3 + 1] << 8) +
                                (dataSourceImage[(sourceRow * oldWidth + sourceCol) * 3 + 2] << 16);
            }
        };

        Range range = Range.create(dataResultImage.length);
        kernel.execute(range);
        kernel.dispose();

        writeFile(resultImage);
    }
}
