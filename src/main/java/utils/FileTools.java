package utils;

import UI.AppState;
import UI.mainForm.panels.galleryPanel.FileList;
import UI.startGenerationForm.ProgressForm;
import model.AugmentationMethod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

public abstract class FileTools {
    public static void createDir(Path path){
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeImages(AugmentationMethod method, int numberOfWritable, ProgressForm progressForm){
        int tempNumber = 0;

        while (tempNumber < numberOfWritable) {
            if (method.storageResult.size() > tempNumber) {
                BufferedImage image = method.storageResult.firstElement();
                if (image != null){
                    method.storageResult.remove(image);
                    writeImage(
                            new File(AppState.resultPath + "\\" + method.name + "_" + tempNumber + ".jpg"),
                            image
                    );
                    System.out.println(" Writing... [" + tempNumber + ": " + method.storageResult.size() + "("  + numberOfWritable+")]");
                    progressForm.updateProgressBar((int) (((float) tempNumber++ / numberOfWritable) * 100));
                }
            }
        }
        progressForm.updateProgressBar(100);
    }

    private static void writeImage(File out, BufferedImage image) {
        try {
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Vector<BufferedImage> readImages(File file){
        File[] files = file.listFiles(new FileList.TextFileFilter());
        if (files != null) {
            return Arrays.stream(files).map(FileTools::readImage).collect(Collectors.toCollection(Vector::new));
        } else {
            return new Vector<>();
        }
    }

    private static BufferedImage readImage(File file){
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    }
}
