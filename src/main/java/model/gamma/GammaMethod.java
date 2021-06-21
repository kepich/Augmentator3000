package model.gamma;

import model.MethodThread;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class GammaMethod extends MethodThread {
    private final float gamma;

    public GammaMethod(float gamma, BufferedImage image, Vector<BufferedImage> storage) {
        super(storage, image);
        this.gamma = gamma;
    }

    protected BufferedImage modify() {
        Mat image = ImageUtils.bufferedImage2Mat(this.image);
        Mat lookUpTable = new Mat(1, 256, CvType.CV_8UC3);
        byte[] lookUpTableData = new byte[(int) (lookUpTable.total() * lookUpTable.channels())];
        for (int i = 0; i < lookUpTable.cols() / 3; i++) {
            lookUpTableData[i] = saturate(Math.pow(i / 255.0, gamma) * 255.0);
            lookUpTableData[i + 1] = saturate(Math.pow(i / 255.0, gamma) * 255.0);
            lookUpTableData[i + 2] = saturate(Math.pow(i / 255.0, gamma) * 255.0);
        }
        lookUpTable.put(0, 0, lookUpTableData);
        Mat img = new Mat();
        Core.LUT(image, lookUpTable, img);
        return ImageUtils.mat2BufferedImage(img);
    }

    private byte saturate(double val) {
        int iVal = (int) Math.round(val);
        iVal = iVal > 255 ? 255 : Math.max(iVal, 0);
        return (byte) iVal;
    }

}