package UI.mainForm.panels;

import javax.swing.*;
import java.awt.event.ActionListener;

public class OpenPanel extends JPanel {
    private final JLabel lTempDir = new JLabel("Choose temp directory...");
    private final JButton bTempDir = new JButton("Open");

    public OpenPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    public void addOpenButtonActionListener(ActionListener actionListener) {
        this.bTempDir.addActionListener(actionListener);
    }

    private void initComponents() {
        lTempDir.setBounds(10, 5, 150, 20);
        this.add(this.lTempDir);

        bTempDir.setBounds(10, 30, this.getWidth() - 2 * 10, 30);
        this.add(this.bTempDir);
    }

    public void setText(String text) {
        lTempDir.setText(text);
    }
}
