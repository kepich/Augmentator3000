package UI.startGenerationForm;

import augmentator.Augmentator;
import model.AugmentationMethodComposite;
import model.ThreadPool;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ProgressForm extends JDialog {
    private final int WIDTH = 360;
    private final int HEIGHT = 160;
    private final JButton results;

    public volatile JProgressBar progressBar = new JProgressBar();
    private final JButton acceptButton = new JButton("Close");
    private final JButton cancelButton = new JButton("Cancel");

    public ProgressForm(AugmentationMethodComposite selectedItem, JButton results) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("0%");
        this.setModal(true);
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.getContentPane().setLayout(null);

        initComponents();
        this.results = results;
        Thread t = new Thread(() -> (new Augmentator(selectedItem)).run(this));
        ProgressForm p = this;

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                t.interrupt();
                ThreadPool.shutdownNow();
                p.dispose();
            }
        });

        t.start();
    }

    public void addFinishButtonActionListener(ActionListener actionListener) {
        acceptButton.addActionListener(actionListener);
    }

    private void initComponents() {
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setBounds(20, 30, WIDTH - 60, 20);
        this.add(progressBar);

        acceptButton.setBounds(30, 70, 110, 20);
        acceptButton.setEnabled(false);
        this.add(acceptButton);

        cancelButton.addActionListener(e -> {
            this.dispose();
        });
        cancelButton.setBounds(200, 70, 110, 20);
        this.add(cancelButton);
    }

    synchronized public void updateProgressBar(int percent) {
        SwingUtilities.invokeLater(() -> {
            this.progressBar.setValue(percent);
            this.setTitle(percent + "%");

            if (percent == 100) {
                this.acceptButton.setEnabled(true);
            }

            this.progressBar.updateUI();
        });

    }

    public void finish() {
        this.dispose();
        this.results.setEnabled(true);
    }
}
