package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Vector;

import static mainForm.MainFrame.AMOUNT_OF_THREADS;

public abstract class AugmentationMethod implements Cloneable, Serializable, Runnable {
    protected AugmentationMethodType type;
    protected String name;

    volatile protected Vector<Thread> threads = new Vector<>();
    private volatile static int number = 0;
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
                System.out.println("Start modifying");
                modifyImage(image);
                System.out.println("Finish modifying");
            }
        }
    }

    public String getMethodName() {
        return name;
    }

    public AugmentationMethodType getType() {
        return type;
    }

    protected abstract void modifyImage(BufferedImage image);
    @Override
    public abstract String toString();

    public abstract AugmentationMethod clone();

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

    synchronized protected void writeFile(BufferedImage bufferedImage){
//        try {
//            ImageIO.write(bufferedImage, "jpg", new File(Augmentator.path + "\\" + this.getName() + "_" + number++ + ".jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println(this.toString() + "Writing... [" + this.storage.size() + "]");
        this.storage.add(bufferedImage);
    }
}
