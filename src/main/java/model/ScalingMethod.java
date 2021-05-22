package model;

import com.aparapi.Kernel;
import com.aparapi.Range;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;

public class ScalingMethod extends AugmentationMethod {
    public float xScaleFrom;
    public float xScaleTo;
    public float xScaleStep;
    public float yScaleFrom;
    public float yScaleTo;
    public float yScaleStep;

    public ScalingMethod() {
        super(AugmentationMethodType.SCALING, "Scaling");
    }

    public ScalingMethod(float xScaleFrom, float xScaleTo, float xScaleStep, float yScaleFrom, float yScaleTo, float yScaleStep) {
        super(AugmentationMethodType.SCALING, "Scaling");

        this.xScaleFrom = xScaleFrom;
        this.xScaleTo = xScaleTo;
        this.xScaleStep = xScaleStep;
        this.yScaleFrom = yScaleFrom;
        this.yScaleTo = yScaleTo;
        this.yScaleStep = yScaleStep;
    }

    @Override
    protected void modifyImage(BufferedImage image){
        for (float xScale = xScaleFrom; xScale <= xScaleTo; xScale += xScaleStep) {
            for (float yScale = yScaleFrom; yScale <= yScaleTo; yScale += yScaleStep) {
                float finalXScale = xScale;
                float finalYScale = yScale;

                Thread thread = new Thread(() -> {
                    double xMul = Math.exp(finalXScale);
                    double yMul = Math.exp(finalYScale);

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
                            // resultImage.setRGB(i, finalRow, image.getRGB((int) (i / xMul), finalNewY));
                        }
                    };

                    Range range = Range.create(dataResultImage.length);
                    kernel.execute(range);
                    kernel.dispose();

                    writeFile(resultImage);
                });

                thread.start();
                threads.add(thread);

                waitAllThreads();

                if(yScaleFrom == yScaleTo){
                    break;
                }
            }

            if(xScaleFrom == xScaleTo){
                break;
            }
        }
        joinAllThreads();
    }

    @Override
    public String toString() {
        return name + "[" + xScaleFrom + ", " + yScaleTo + "](" + xScaleStep + "), [" + yScaleFrom + ", " + yScaleTo + "](" + yScaleStep + ")";
    }

    @Override
    public AugmentationMethod clone() {
        return new ScalingMethod(xScaleFrom, xScaleTo, xScaleStep, yScaleFrom, yScaleTo, yScaleStep);
    }

    @Override
    public int getEstimatedTime() {
        if(xScaleTo - xScaleFrom == 0) {
            return (int) ((yScaleTo - yScaleFrom) / yScaleStep);
        }

        if(yScaleTo - yScaleFrom == 0) {
            return (int) ((xScaleTo - xScaleFrom) / xScaleStep);
        }

        return (int) ((xScaleTo - xScaleFrom) / xScaleStep * (yScaleTo - yScaleFrom) / yScaleStep);
    }
}
