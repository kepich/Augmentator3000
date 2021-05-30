package model.projection;

import model.AugmentationMethod;
import model.AugmentationMethodType;
import utils.ThreadPool;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ProjectionMethod extends AugmentationMethod {
    public int[] xLU = {0, 0, 0};
    public int[] yLU = {0, 0, 0};

    public int[] xRU = {0, 0, 0};
    public int[] yRU = {0, 0, 0};

    public int[] xRD = {0, 0, 0};
    public int[] yRD = {0, 0, 0};

    public int[] xLD = {0, 0, 0};
    public int[] yLD = {0, 0, 0};

    public ProjectionMethod() {
        super(AugmentationMethodType.PROJECTION, "Projection");
    }

    protected ProjectionMethod(int[] xLU, int[] yLU, int[] xRU, int[] yRU, int[] xRD, int[] yRD, int[] xLD, int[] yLD) {
        super(AugmentationMethodType.PROJECTION, "Projection");
        this.xLU = xLU;
        this.yLU = yLU;

        this.xRU = xRU;
        this.yRU = yRU;

        this.xRD = xRD;
        this.yRD = yRD;

        this.xLD = xLD;
        this.yLD = yLD;

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public AugmentationMethod clone() {
        return new ProjectionMethod(
                Arrays.copyOf(this.xLU, 3),
                Arrays.copyOf(this.yLU, 3),
                Arrays.copyOf(this.xRU, 3),
                Arrays.copyOf(this.yRU, 3),
                Arrays.copyOf(this.xRD, 3),
                Arrays.copyOf(this.yRD, 3),
                Arrays.copyOf(this.xLD, 3),
                Arrays.copyOf(this.yLD, 3)
        );
    }

    @Override
    protected void modifyImage(BufferedImage image) {
        for (
            int[]   lu = {this.xLU[0], this.yLU[0]},
                    ru = {this.xRU[0], this.yRU[0]},
                    rd = {this.xRD[0], this.yRD[0]},
                    ld = {this.xLD[0], this.yLD[0]};
            lu[0] <= this.xLU[1] || lu[1] <= this.yLU[1] ||
                    ru[0] <= this.xRU[1] || ru[1] <= this.yRU[1] ||
                    rd[0] <= this.xRD[1] || rd[1] <= this.yRD[1] ||
                    ld[0] <= this.xLD[1] || ld[1] <= this.yLD[1];

            lu[0] += xLU[2], lu[1] += yLU[2],
                    ru[0] += xRU[2], ru[1] += yRU[2],
                    rd[0] += xRD[2], rd[1] += yRD[2],
                    ld[0] += xLD[2], ld[1] += yLD[2]
        ) {
            ThreadPool.runTask(new ProjectionMethodCPU(lu, ru, rd, ld, image, storageResult), priority);
        }
    }

    @Override
    public int getEstimatedTime() {
        int res = 0;
        res = getMaxTime(res, xLU, yLU, xRU, yRU);
        res = getMaxTime(res, xRD, yRD, xLD, yLD);
        return res;
    }

    private int getMaxTime(int res, int[] xLU, int[] yLU, int[] xRU, int[] yRU) {
        if (xLU[2] != 0){
            res = Math.max(res, (xLU[1] - xLU[0]) / xLU[2]);
        }
        if (yLU[2] != 0){
            res = Math.max(res, (yLU[1] - yLU[0]) / yLU[2]);
        }
        if (xRU[2] != 0){
            res = Math.max(res, (xRU[1] - xRU[0]) / xRU[2]);
        }
        if (yRU[2] != 0){
            res = Math.max(res, (yRU[1] - yRU[0]) / yRU[2]);
        }
        return res;
    }
}
