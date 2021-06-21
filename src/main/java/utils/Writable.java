package utils;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class Writable {
    private final Vector<BufferedImage> storage;

    public Writable(Vector<BufferedImage> storage) {
        this.storage = storage;
    }

    synchronized protected void writeFile(BufferedImage bufferedImage) {
        this.storage.add(bufferedImage);
    }
}
