package model.projection;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.awt.image.BufferedImage;
import java.util.Vector;

import static org.opencv.core.Core.BORDER_CONSTANT;
import static org.opencv.imgproc.Imgproc.INTER_LINEAR;
import static utils.ImageUtils.bufferedImage2Mat;
import static utils.ImageUtils.mat2BufferedImage;

public class ProjectionMethodCPU extends ProjectionMethodThread {
    public ProjectionMethodCPU(int[] lu, int[] ru, int[] rd, int[] ld, BufferedImage image, Vector<BufferedImage> storage) {
        super(lu, ru, rd, ld, image, storage);
    }

    @Override
    public void run() {
        Mat imageMat = bufferedImage2Mat(image);

        Mat srcTri = new Mat(4, 2, CvType.CV_32FC1);
        srcTri.put(0,
                0,
                new float[]{
                        0, 0,
                        image.getWidth(), 0,
                        0, image.getHeight(),
                        image.getWidth(), image.getHeight()
        });

        Mat dstTri = new Mat(4, 2, CvType.CV_32FC1);
        dstTri.put(0,
                0,
                new float[]{
                        this.lu[0], this.lu[1],
                        this.ru[0], this.ru[1],
                        this.ld[0], this.ld[1],
                        this.rd[0], this.rd[1]}
        );

        Mat M = Imgproc.getPerspectiveTransform(srcTri, dstTri);
        Imgproc.warpPerspective(imageMat, imageMat, M, imageMat.size());
        BufferedImage resultImage = mat2BufferedImage(imageMat);

        writeFile(resultImage);
    }
}
