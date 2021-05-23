package model.brightness;

import com.aparapi.Kernel;
import com.aparapi.Range;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.util.Vector;

public class BrightnessMethodGPU extends BrightnessMethodThread {
    public BrightnessMethodGPU(float brightness, BufferedImage image, Vector<BufferedImage> storage) {
        super(brightness, image, storage);
    }

    @Override
    public void run() {
        int width = image.getWidth();
        int height = image.getHeight();
        int brightnessOffset = (int) brightness;

        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        byte[] dataSourceImage = ((DataBufferByte)(image.getRaster().getDataBuffer())).getData();
        byte[] dataResultImage = ((DataBufferByte)(resultImage.getRaster().getDataBuffer())).getData();
        Kernel kernel = new Kernel() {
            @Override
            public void run() {
                int gid = getGlobalId();
                int color = (dataSourceImage[gid] & 0xFF) + brightnessOffset;
                int resColor = color;

                if (color > 255){
                    resColor = 255;
                }
                if (color < 0){
                    resColor = 0;
                }

                dataResultImage[gid] = (byte) (resColor & 0xFF);
            }
        };

        Range range = Range.create(dataResultImage.length);
        kernel.execute(range);
        kernel.dispose();

        writeFile(resultImage);
    }
}
