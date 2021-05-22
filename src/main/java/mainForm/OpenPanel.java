package mainForm;

import utils.MyLogger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenPanel extends JComponent implements ActionListener {
    public JLabel lTempDir;
    public JButton bTempDir;

    public OpenPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    private void initComponents() {
        lTempDir = new JLabel("Choose temp directory...");
        lTempDir.setBounds(10, 5, 150, 20);
        this.add(this.lTempDir);

        bTempDir = new JButton("Open");
        bTempDir.setBounds(10, 30, this.getWidth() - 2 * 10, 30);
        bTempDir.setActionCommand("OpenDir");

        this.add(this.bTempDir);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("OpenDir")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                lTempDir.setText(selectedFile.getAbsolutePath());

                ActionEvent actionEvent = new ActionEvent(fileChooser, 777, "UpdateDir");

                for (ActionListener actionListener : listenerList.getListeners(ActionListener.class)) {
                    actionListener.actionPerformed(actionEvent);
                }

                MyLogger.log(this.getClass().getName(), "Selected dir: " + selectedFile.getAbsolutePath());
            } else {
                MyLogger.log(this.getClass().getName(), "Shit!!!");

            }
        }
    }
}
