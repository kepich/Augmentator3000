package mainForm;

import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GalleryPanel extends JPanel implements ActionListener {
    public FileList fileList;

    public GalleryPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);

        initComponents();
    }

    private void initComponents() {
        this.add(new JLabel("Initial collection: "));

        fileList = FileList.instance(null);
        fileList.setBounds(this.getBounds());

        this.add(fileList);
    }

    public void rebuild(String path) {
        File f;

        if (path != null) {
            f = new File(path);
        } else {
            f = new File(System.getProperty("user.home"));
        }

        fileList.fileList.setListData(f.listFiles(new FileList.TextFileFilter()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("UpdateDir")) {
            JFileChooser fileChooser = (JFileChooser) e.getSource();
            rebuild(fileChooser.getSelectedFile().getPath());
        }
    }
}
