package methodBank;

import mainForm.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class MethodBankFrame extends JDialog {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public MethodPanel methodPanel;

    public MethodBankFrame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                try {
                    FileOutputStream fout = new FileOutputStream("methods.data");
                    ObjectOutputStream out = new ObjectOutputStream(fout);
                    out.writeObject(MainFrame.methods);
                    out.flush();
                    out.close();
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

                dispose();
            }
        });

        this.setTitle("Methods bank");
        this.setModal(true);
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.getContentPane().setLayout(null);

        initComponents();
    }

    private void initComponents() {
        var contentPane = getContentPane();

        methodPanel = new MethodPanel(0, 0, WIDTH, HEIGHT);
        methodPanel.setBackground(new Color(0x488c4d));
        contentPane.add(methodPanel);
    }
}
