package mainForm;

import model.AugmentationMethod;
import model.brightness.BrightnessMethod;
import model.scaling.ScalingMethod;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class MainFrame extends JFrame {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public static final int AMOUNT_OF_THREADS = 2;

    private final int leftVertHeight = 70;
    private final int leftWidth = 280;

    private final int middleWidth = 700;

    private final int rightVertHeight = 650;
    private final int rightWidth = 300;


    public OpenPanel openPanel = new OpenPanel(0, 0, leftWidth, leftVertHeight);
    public GalleryPanel processingGalleryPanel = new GalleryPanel(0, leftVertHeight, leftWidth, HEIGHT - leftVertHeight);
    public ViewerPanel viewerPanel = new ViewerPanel(leftWidth, 0, middleWidth, HEIGHT);
    public MenuPanel menuPanel = new MenuPanel(leftWidth + middleWidth, 0, rightWidth, rightVertHeight);
    public StatePanel statePanel = new StatePanel(leftWidth + middleWidth, rightVertHeight, rightWidth, HEIGHT - rightVertHeight);

    public static Vector<AugmentationMethod> methods = new Vector<>();
    public static Vector<AugmentationMethod> simpleMethods = new Vector<>();


    public MainFrame() {
        simpleMethods.add(new ScalingMethod());
        simpleMethods.add(new BrightnessMethod());

        try {
            FileInputStream fin = new FileInputStream("methods.data");
            ObjectInputStream in = new ObjectInputStream (fin);
            MainFrame.methods = (Vector<AugmentationMethod>)in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

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
        openPanel.addActionListener(processingGalleryPanel);
        openPanel.addActionListener(menuPanel);
        openPanel.bTempDir.addActionListener(openPanel);
        contentPane.add(openPanel);

        processingGalleryPanel.setBackground(new Color(0x8C8548));
        processingGalleryPanel.fileList.fileList.addListSelectionListener(viewerPanel);
        contentPane.add(processingGalleryPanel);

        viewerPanel.setBackground(new Color(0x4A488C));
        contentPane.add(viewerPanel);

        menuPanel.setBackground(new Color(0x87488C));
        contentPane.add(menuPanel);

        statePanel.setBackground(new Color(0x953B3B));
        contentPane.add(statePanel);
    }

}
