package mainForm;

import acceptForm.AcceptForm;
import methodBank.MethodBankFrame;

import javax.swing.*;
import java.awt.event.*;

public class MenuPanel extends JPanel implements ActionListener {
    private JButton generate;
    private JFileChooser fileChooser;

    public MenuPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        JButton methodBank = new JButton("Method bank");
        methodBank.setBounds((int) (this.getWidth() * 0.025), 10, (int) (this.getWidth() * 0.9), 50);
        methodBank.setActionCommand("OpenBank");
        methodBank.addActionListener(e -> {
            if (e.getActionCommand().equals("OpenBank")) {
                MethodBankFrame methodBankFrame = new MethodBankFrame();

                methodBankFrame.setVisible(true);
            }
        });
        this.add(methodBank);

        this.generate = new JButton("Generate");
        generate.setBounds((int) (this.getWidth() * 0.025), 70, (int) (this.getWidth() * 0.9), 50);
        generate.setEnabled(false);

        generate.addActionListener(e -> {
            AcceptForm acceptForm = new AcceptForm(fileChooser);

            acceptForm.acceptButton.addActionListener(a -> {
                acceptForm.dispose();
            });

            acceptForm.cancelButton.addActionListener(a -> {
                acceptForm.dispose();
            });

            acceptForm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    acceptForm.dispose();
                }
            });

            acceptForm.setVisible(true);
        });

        this.add(generate);

        JButton results = new JButton("Results");
        results.setBounds((int) (this.getWidth() * 0.025), 130, (int) (this.getWidth() * 0.9), 50);
        this.add(results);

        JButton analyse = new JButton("Analyse");
        analyse.setBounds((int) (this.getWidth() * 0.025), 190, (int) (this.getWidth() * 0.9), 50);
        this.add(analyse);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("UpdateDir")) {
            fileChooser = (JFileChooser) e.getSource();
            generate.setEnabled(true);
            //rebuild(fileChooser.getSelectedFile().getPath());
        }
    }
}
