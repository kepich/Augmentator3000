package model.scaling;

import model.AugmentationMethod;
import model.AugmentationMethodKit;
import model.AugmentationMethodType;
import model.ThreadPool;

import java.awt.image.BufferedImage;

public class ScalingMethodIterator extends AugmentationMethod {
    public float xScaleFrom;
    public float xScaleTo;
    public float xScaleStep;
    public float yScaleFrom;
    public float yScaleTo;
    public float yScaleStep;

    public ScalingMethodIterator() {
        super(AugmentationMethodType.SCALING, "Scaling");
    }

    public ScalingMethodIterator(float xScaleFrom, float xScaleTo, float xScaleStep, float yScaleFrom, float yScaleTo, float yScaleStep) {
        super(AugmentationMethodType.SCALING, "Scaling");

        this.xScaleFrom = xScaleFrom;
        this.xScaleTo = xScaleTo;
        this.xScaleStep = xScaleStep;
        this.yScaleFrom = yScaleFrom;
        this.yScaleTo = yScaleTo;
        this.yScaleStep = yScaleStep;
    }

    @Override
    protected void modifyImage(BufferedImage image) {
        for (float xScale = xScaleFrom; xScale <= xScaleTo; xScale += xScaleStep) {
            for (float yScale = yScaleFrom; yScale <= yScaleTo; yScale += yScaleStep) {
                ThreadPool.runTask(AugmentationMethodKit.getScalingMethodThread(xScale, yScale, image, storageResult), priority);

                if (yScaleFrom == yScaleTo) {
                    break;
                }
            }
            if (xScaleFrom == xScaleTo) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return name + "[" + xScaleFrom + ", " + yScaleTo + "](" + xScaleStep + "), [" + yScaleFrom + ", " + yScaleTo + "](" + yScaleStep + ")";
    }

    @Override
    public AugmentationMethod clone() {
        return new ScalingMethodIterator(xScaleFrom, xScaleTo, xScaleStep, yScaleFrom, yScaleTo, yScaleStep);
    }

    @Override
    public int getEstimatedTime() {
        if (xScaleTo - xScaleFrom == 0) {
            return (int) ((yScaleTo - yScaleFrom) / yScaleStep);
        }

        if (yScaleTo - yScaleFrom == 0) {
            return (int) ((xScaleTo - xScaleFrom) / xScaleStep);
        }

        return (int) (((xScaleTo - xScaleFrom) / xScaleStep + 1) * ((yScaleTo - yScaleFrom) / yScaleStep + 1));
    }
}
