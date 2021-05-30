package augmentator;

import UI.AppState;
import UI.startGenerationForm.ProgressForm;
import model.AugmentationMethodComposite;
import utils.FileTools;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.util.Vector;

public class Augmentator {
    private final AugmentationMethodComposite selectedMethod;

    public Augmentator(AugmentationMethodComposite selectedItem) {
        this.selectedMethod = selectedItem;
    }

    synchronized public void run(ProgressForm progressForm) {
        AppState.resultPath = Paths.get(AppState.selectedDirectory.getAbsolutePath() + "\\dataset\\");
        FileTools.createDir(AppState.resultPath);

        Vector<BufferedImage> inputFiles = FileTools.readImages(AppState.selectedDirectory);
        int estimatedTime = this.selectedMethod.getEstimatedTime() * inputFiles.size();

        this.selectedMethod.setStorageInputAndMaxSizeAndPriority(inputFiles, inputFiles.size(), -1);

        Thread thread = new Thread(this.selectedMethod);
        thread.setDaemon(true);
        thread.start();

        FileTools.writeImages(this.selectedMethod, estimatedTime, progressForm);

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
