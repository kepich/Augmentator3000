package augmentator;

import mainForm.FileList;
import model.AugmentationMethod;
import progressFrame.ProgressForm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

public class Augmentator {
    private final AugmentationMethod selectedMethod;
    private ProgressForm progressForm;
    private JFileChooser fileChooser;

    public static Path path;

    public Augmentator(JFileChooser fileChooser, AugmentationMethod selectedItem, ProgressForm progressForm) {
        this.fileChooser = fileChooser;
        this.selectedMethod = selectedItem;
        this.progressForm = progressForm;
    }

    synchronized public void run() {
        path = Paths.get(fileChooser.getSelectedFile().getAbsolutePath() + "\\dataset\\");
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f = new File(fileChooser.getSelectedFile().getAbsolutePath());
        File[] files = f.listFiles(new FileList.TextFileFilter());
        Vector<BufferedImage> inputFiles = Arrays.stream(files).map((file) -> {
            try {
                return ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        }).collect(Collectors.toCollection(Vector::new));
        this.selectedMethod.setStorageInputAndMaxSize(inputFiles, this.selectedMethod.getEstimatedTime());
        Thread mainThread = new Thread(this.selectedMethod);
        mainThread.start();

        File out;


        int tempNumber = 0;
        int estimatedTime = this.selectedMethod.getEstimatedTime() * files.length;


        while(tempNumber < estimatedTime){
            if (this.selectedMethod.storage.size() > tempNumber){
                BufferedImage image = this.selectedMethod.storage.get(tempNumber);
                this.selectedMethod.storage.set(tempNumber, null);

                out = new File(path + "\\" + this.selectedMethod.getMethodName() + "_" + tempNumber + ".jpg");

                try {
                    ImageIO.write(image, "jpg", out);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                this.progressForm.updateProgressBar((int)(((float)tempNumber++ / estimatedTime) * 100));
            }
        }

        try {
            mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.progressForm.updateProgressBar(100);
        this.progressForm.acceptButton.setEnabled(true);
    }
}
