package model.scaling;

import com.aparapi.Kernel;
import com.aparapi.Range;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Vector;

public class ScalingMethodGPU extends ScalingMethodThread {
    public ScalingMethodGPU(double xScale, double yScale, BufferedImage image, Vector<BufferedImage> storage) {
        super(xScale, yScale, image, storage);
    }

    @Override
    public void run() {
        double xMul = Math.exp(xScale);
        double yMul = Math.exp(yScale);

        int resultWidth = (int) (image.getWidth() * xMul);
        int resultHeight = (int) (image.getHeight() * yMul);

        BufferedImage resultImage = new BufferedImage(resultWidth, resultHeight, BufferedImage.TYPE_3BYTE_BGR);

        int oldWidth = image.getWidth();
        byte[] dataSourceImage = ((DataBufferByte)(image.getRaster().getDataBuffer())).getData();
        byte[] dataResultImage = ((DataBufferByte)(resultImage.getRaster().getDataBuffer())).getData();

        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int gid = getGlobalId();

                int resultRow = gid / resultWidth;
                int resultCol = gid % resultWidth;

                int sourceRow = (int) (resultRow / yMul);
                int sourceCol = (int) (resultCol / xMul);

                int tempSourceCell = (sourceRow * oldWidth + sourceCol) * 3;
                int tempResultCell = (resultRow * resultWidth + resultCol) * 3;

                dataResultImage[tempResultCell] = dataSourceImage[tempSourceCell];
                dataResultImage[tempResultCell + 1] = dataSourceImage[tempSourceCell + 1];
                dataResultImage[tempResultCell + 2] = dataSourceImage[tempSourceCell + 2];
            }
        };

        Range range = Range.create(resultWidth * resultHeight);
        kernel.execute(range);
        kernel.dispose();

        writeFile(resultImage);
    }
}
