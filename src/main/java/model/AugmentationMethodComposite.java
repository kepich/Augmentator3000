package model;

import java.awt.image.BufferedImage;
import java.util.Vector;

public class AugmentationMethodComposite extends AugmentationMethod {
    public Vector<AugmentationMethod> methods = new Vector<>();

    public AugmentationMethodComposite(String name) {
        super(AugmentationMethodType.COMPOSITE, name);
    }

    @Override
    public void run() {
        Vector<BufferedImage> tempStorage = storageInput;
        int maxSize = tempStorage.size();

        for (AugmentationMethod method : this.methods) {
            Vector<BufferedImage> finalTempStorage = tempStorage;
            maxSize *= method.getEstimatedTime();
            method.setStorageInputAndMaxSize(finalTempStorage, maxSize);
            Thread thread = new Thread(method);

            tempStorage = method.storage;
            thread.start();
            threads.add(thread);
        }
        this.storage = tempStorage;
    }

    @Override
    protected void modifyImage(BufferedImage image) {

    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public AugmentationMethod clone() {
        return null;
    }

    @Override
    public int getEstimatedTime() {
        int result = 1;
        for (AugmentationMethod method : this.methods) {
            result *= method.getEstimatedTime();
        }
        return result;
    }

    public void add(AugmentationMethod method) {
        this.methods.add(method);
    }
}
