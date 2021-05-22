package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Vector;

import static mainForm.MainFrame.AMOUNT_OF_THREADS;

public abstract class AugmentationMethod implements Cloneable, Serializable, Runnable {
    public final AugmentationMethodType type;
    public final String name;

    volatile protected Vector<Thread> threads = new Vector<>();
    public volatile Vector<BufferedImage> storage = new Vector<>();
    protected volatile Vector<BufferedImage> storageInput;
    protected volatile int maxInputSize;

    public void setStorageInputAndMaxSize(Vector<BufferedImage> storageInput, int maxInputSize){
        this.storageInput = storageInput;
        this.maxInputSize = maxInputSize;
    }

    protected AugmentationMethod(AugmentationMethodType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public void run() {
        int tempNumber = 0;

        while(tempNumber < maxInputSize){
            if (storageInput.size() > tempNumber){
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

    protected void waitAllThreads(){
        if(threads.size() > AMOUNT_OF_THREADS){
            for(Thread t_thread: threads){
                try {
                    t_thread.join();
                    System.out.println("Killed");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            threads.clear();
        }
    }

    protected void joinAllThreads(){
        for(Thread t_thread: threads){
            try {
                t_thread.join();
                System.out.println("Killed");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threads.clear();
    }
}
