package progressFrame;

import augmentator.Augmentator;
import model.AugmentationMethod;
import model.AugmentationMethodComposite;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgressForm extends JDialog {
    private final int WIDTH = 360;
    private final int HEIGHT = 160;
    public final Augmentator augmentator;
    private final JButton results;

    public volatile JProgressBar progressBar;
    public JButton acceptButton;
    public JButton cancelButton;

    public ProgressForm(JFileChooser fileChooser, AugmentationMethodComposite selectedItem, JButton results) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("0%");
        this.setModal(true);
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.getContentPane().setLayout(null);

        initComponents();
        augmentator = new Augmentator(fileChooser, selectedItem, this);
        this.results = results;
        Thread t = new Thread(augmentator::run);
        ProgressForm p = this;

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                t.interrupt();
                p.dispose();
            }
        });

        t.start();
    }

    private void initComponents() {
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setBounds(20, 30, WIDTH - 60, 20);
        this.add(progressBar);

        acceptButton = new JButton("Close");
        acceptButton.setBounds(30, 70, 110, 20);
        acceptButton.setEnabled(false);
        acceptButton.addActionListener(e -> {
            this.dispose();
            this.results.setEnabled(true);
        });
        this.add(acceptButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            this.dispose();
        });

        cancelButton.setBounds(200, 70, 110, 20);
        this.add(cancelButton);
    }

    synchronized public void updateProgressBar(int percent){
        SwingUtilities.invokeLater(() -> {
            this.progressBar.setValue(percent);
            this.setTitle(percent + "%");
            this.progressBar.updateUI();
        });

    }
}
