package model.gamma;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class GammaMethodCPU extends GammaMethodThread {

    public GammaMethodCPU(double gamma, BufferedImage image, Vector<BufferedImage> storage) {
        super(gamma, image, storage);
    }

    @Override
    public void run() {
        writeFile(modify((int) gamma));
    }

    private BufferedImage modify(int gamma) {
        Mat image = ImageUtils.bufferedImage2Mat(this.image);
        Mat lookUpTable = new Mat(1, 256, CvType.CV_8U);
        byte[] lookUpTableData = new byte[(int) (lookUpTable.total()*lookUpTable.channels())];
        for (int i = 0; i < lookUpTable.cols(); i++) {
            lookUpTableData[i] = saturate(Math.pow(i / 255.0, gamma) * 255.0);
        }
        lookUpTable.put(0, 0, lookUpTableData);
        Mat img = new Mat();
        Core.LUT(image, lookUpTable, img);
        return (BufferedImage) HighGui.toBufferedImage(img);
    }

    private byte saturate(double val) {
        int iVal = (int) Math.round(val);
        iVal = iVal > 255 ? 255 : (iVal < 0 ? 0 : iVal);
        return (byte) iVal;
    }

}