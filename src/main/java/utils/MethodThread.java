package utils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class MethodThread extends Writable implements Runnable{
    public MethodThread(Vector<BufferedImage> storage) {
        super(storage);
    }
}
