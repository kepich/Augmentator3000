package utils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class MethodThread extends Writable implements Runnable {
    protected BufferedImage image;

    public MethodThread(Vector<BufferedImage> storage, BufferedImage image) {
        super(storage);
        this.image = image;
    }
}
