package mainForm;

import utils.MyLogger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;

public class ViewerPanel extends JPanel implements ListSelectionListener, ActionListener {
    private JLabel picLabel;

    public ViewerPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        this.picLabel = new JLabel("", CENTER);
        this.picLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(picLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: Redraw
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        MyLogger.log(this.getClass().getName(), e.toString());
        JList<File> source = (JList<File>) e.getSource();

        try {
            BufferedImage image = ImageIO.read(source.getSelectedValue());

            float multiplier = (float) this.getWidth() / (float) image.getWidth();
            if (multiplier * image.getHeight() > this.getHeight()) {
                multiplier = (float) this.getHeight() / (float) image.getHeight();
            }

            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(
                    (int) (image.getWidth() * multiplier),
                    (int) (image.getHeight() * multiplier),
                    Image.SCALE_SMOOTH)
            );

            picLabel.setIcon(imageIcon);
            picLabel.setHorizontalAlignment(CENTER);

        } catch (IOException ioException) {
            picLabel.setText("Неподдерживаемый формат изображения");
            ioException.printStackTrace();
        }
    }
}

