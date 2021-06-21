package model;

import utils.Writable;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class MethodThread extends Writable implements Runnable {
    protected BufferedImage image;

    public MethodThread(Vector<BufferedImage> storage, BufferedImage image) {
        super(storage);
        this.image = image;
    }

    @Override
    public void run() {
        writeFile(modify());
    }

    protected abstract BufferedImage modify();
}
