package model.gamma;

import model.AugmentationMethod;
import model.AugmentationMethodKit;
import model.AugmentationMethodType;
import model.ThreadPool;

import java.awt.image.BufferedImage;

public class GammaMethodIterator extends AugmentationMethod {
    public float gammaFrom;
    public float gammaTo;
    public float gammaStep;

    public GammaMethodIterator() {
        super(AugmentationMethodType.GAMMA, "Gamma");
    }

    public GammaMethodIterator(float gammaFrom, float gammaTo, float gammaStep) {
        super(AugmentationMethodType.GAMMA, "Gamma");

        this.gammaFrom = gammaFrom;
        this.gammaTo = gammaTo;
        this.gammaStep = gammaStep;
    }

    @Override
    protected void modifyImage(BufferedImage image) {
        for (float gamma = gammaFrom; gamma <= gammaTo; gamma = (gamma + gammaStep)) {
            ThreadPool.runTask(AugmentationMethodKit.getGammaMethodThread(gamma, image, storageResult), priority);
        }
    }

    @Override
    public String toString() {
        return name + "[" + gammaFrom + ", " + gammaTo + "](" + gammaStep + ")";
    }

    @Override
    public AugmentationMethod clone() {
        return new GammaMethodIterator(gammaFrom, gammaTo, gammaStep);
    }

    @Override
    public int getEstimatedTime() {
        return (int) ((gammaTo - gammaFrom) / gammaStep + 1);
    }
}
