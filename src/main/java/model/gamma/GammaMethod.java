package model.gamma;

import model.AugmentationMethod;
import model.AugmentationMethodType;
import utils.ThreadPool;

import java.awt.image.BufferedImage;

public class GammaMethod extends AugmentationMethod {
    public float gammaFrom;
    public float gammaTo;
    public float gammaStep;

    public GammaMethod() {
        super(AugmentationMethodType.GAMMA, "Gamma");
    }

    public GammaMethod(float gammaFrom, float gammaTo, float gammaStep) {
        super(AugmentationMethodType.GAMMA, "Gamma");

        this.gammaFrom = gammaFrom;
        this.gammaTo = gammaTo;
        this.gammaStep = gammaStep;
    }

    @Override
    protected void modifyImage(BufferedImage image) {
        for (float gamma = gammaFrom; gamma <= gammaTo; gamma += gammaStep) {
            ThreadPool.runTask(new GammaMethodCPU(gamma, image, storageResult), priority);
        }
    }

    @Override
    public String toString() {
        return name + "[" + gammaFrom + ", " + gammaTo + "](" + gammaStep + ")";
    }

    @Override
    public AugmentationMethod clone() {
        return new GammaMethod(gammaFrom, gammaTo, gammaStep);
    }

    @Override
    public int getEstimatedTime() {
        return (int) ((gammaTo - gammaFrom) / gammaStep + 1);
    }
}
