package UI.mainForm.panels.galleryPanel;

import javax.swing.*;
import java.io.File;

public class GalleryPanel extends JPanel {
    private final FileList fileList = FileList.instance(null);
    private final JLabel label = new JLabel("Initial collection: ");

    public GalleryPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        initComponents();
    }

    private void initComponents() {
        this.add(label);

        fileList.setBounds(this.getBounds());
        this.add(fileList);
    }

    public void rebuild() {
        fileList.rebuild();
    }

    public JList<File> getFileList() {
        return fileList.fileList;
    }
}
