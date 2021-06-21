package UI.mainForm.panels;

import javax.swing.*;

import static javax.swing.SwingConstants.CENTER;

public class ViewerPanel extends JPanel {
    private JLabel picLabel;

    public ViewerPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        this.picLabel = new JLabel("", CENTER);
        this.picLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        picLabel.setHorizontalAlignment(CENTER);
        this.add(picLabel);
    }

    public void setPicture(ImageIcon imageIcon) {
        picLabel.setIcon(imageIcon);
    }

    public void setText(String text) {
        picLabel.setText(text);
    }
}

