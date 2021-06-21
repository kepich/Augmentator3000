package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Vector;

public abstract class AugmentationMethod implements Cloneable, Serializable, Runnable {
    public final AugmentationMethodType type;
    public final String name;
    public volatile Vector<BufferedImage> storageResult = new Vector<>();

    protected volatile Vector<BufferedImage> storageInput;
    protected volatile int maxInputSize;
    protected int priority;

    protected ThreadPool threadPool = new ThreadPool();

    public void setStorageInputAndMaxSizeAndPriority(Vector<BufferedImage> storageInput, int maxInputSize, int priority) {
        this.storageInput = storageInput;
        this.maxInputSize = maxInputSize;
        this.priority = priority;
    }

    protected AugmentationMethod(AugmentationMethodType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public void run() {
        int tempNumber = 0;

        while (tempNumber < maxInputSize) {
            if (storageInput.size() > tempNumber) {
                BufferedImage image = storageInput.get(tempNumber);
                storageInput.set(tempNumber++, null);
                modifyImage(image);
            }
        }
    }

    @Override
    public abstract String toString();

    public abstract AugmentationMethod clone();

    protected abstract void modifyImage(BufferedImage image);

    public abstract int getEstimatedTime();
}
