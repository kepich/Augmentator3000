package UI.mainForm;

import UI.AppState;
import UI.mainForm.panels.MenuPanel;
import UI.mainForm.panels.OpenPanel;
import UI.mainForm.panels.ViewerPanel;
import UI.mainForm.panels.galleryPanel.GalleryPanel;
import utils.ImageUtils;
import utils.MyLogger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainForm extends JFrame {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private final int leftVertHeight = 70;
    private final int leftWidth = 280;

    private final int middleWidth = 700;
    private final int rightWidth = 300;

    private final OpenPanel openPanel = new OpenPanel(0, 0, leftWidth, leftVertHeight);
    private final GalleryPanel processingGalleryPanel = new GalleryPanel(0, leftVertHeight, leftWidth, HEIGHT - leftVertHeight);
    private final ViewerPanel viewerPanel = new ViewerPanel(leftWidth, 0, middleWidth, HEIGHT);
    private final MenuPanel menuPanel = new MenuPanel(leftWidth + middleWidth, 0, rightWidth, HEIGHT);

    public MainForm() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.getContentPane().setLayout(null);
        this.setTitle("AUGMENTATOR 3000");
        initComponents();
    }

    private void initComponents() {
        var contentPane = getContentPane();

        openPanel.setBackground(new Color(0x488c4d));
        openPanel.addOpenButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(openPanel);

                if (result == JFileChooser.APPROVE_OPTION) {
                    AppState.selectedDirectory = fileChooser.getSelectedFile();
                    openPanel.setText(AppState.selectedDirectory.getAbsolutePath());

                    menuPanel.generate.setEnabled(true);
                    processingGalleryPanel.rebuild();

                    MyLogger.log(this.getClass().getName(), "Selected dir: " + AppState.selectedDirectory.getAbsolutePath());
                }
            }
        });
        contentPane.add(openPanel);

        processingGalleryPanel.setBackground(new Color(0x8C8548));
        processingGalleryPanel.getFileList().addListSelectionListener(e -> {
            MyLogger.log(viewerPanel.getClass().getName(), e.toString());

            try {
                BufferedImage image = ImageIO.read(processingGalleryPanel.getFileList().getSelectedValue());
                viewerPanel.setPicture(ImageUtils.getResizedImageIcon(image, viewerPanel.getWidth(), viewerPanel.getHeight()));
            } catch (IOException ioException) {
                viewerPanel.setText("Неподдерживаемый формат изображения");
                ioException.printStackTrace();
            }
        });
        contentPane.add(processingGalleryPanel);

        viewerPanel.setBackground(new Color(0x4A488C));
        contentPane.add(viewerPanel);

        menuPanel.setBackground(new Color(0x87488C));
        contentPane.add(menuPanel);
    }

}
