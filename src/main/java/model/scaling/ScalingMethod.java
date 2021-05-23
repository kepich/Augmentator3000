package model.scaling;

import model.AugmentationMethod;
import model.AugmentationMethodFactory;
import model.AugmentationMethodType;

import java.awt.image.BufferedImage;

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
                Thread thread = new Thread(AugmentationMethodFactory.createScalingMethodThread(xScale, yScale, image, storage));
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

        return (int) (((xScaleTo - xScaleFrom) / xScaleStep + 1) * ((yScaleTo - yScaleFrom) / yScaleStep + 1));
    }
}
