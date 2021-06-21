package UI.mainForm.panels;

import UI.AppState;
import UI.methodBankForm.MethodBankForm;
import UI.startGenerationForm.StartGenerationForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MenuPanel extends JPanel {
    public final JButton generate = new JButton("Generate");
    private final JButton results = new JButton("Results");
    private final JButton methodBank = new JButton("Method bank");
    private final JButton analyse = new JButton("Analyse");

    public MenuPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        methodBank.setBounds((int) (this.getWidth() * 0.025), 10, (int) (this.getWidth() * 0.9), 50);
        methodBank.addActionListener(e -> {
            MethodBankForm methodBankForm = new MethodBankForm();
            methodBankForm.setVisible(true);
        });
        this.add(methodBank);

        generate.setBounds((int) (this.getWidth() * 0.025), 70, (int) (this.getWidth() * 0.9), 50);
        generate.setEnabled(false);
        generate.addActionListener(e -> {
            StartGenerationForm startGenerationForm = new StartGenerationForm(results);

            startGenerationForm.acceptButton.addActionListener(a -> {
                startGenerationForm.dispose();
            });

            startGenerationForm.cancelButton.addActionListener(a -> {
                startGenerationForm.dispose();
            });

            startGenerationForm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    startGenerationForm.dispose();
                }
            });

            startGenerationForm.setVisible(true);
        });
        this.add(generate);

        results.setBounds((int) (this.getWidth() * 0.025), 130, (int) (this.getWidth() * 0.9), 50);
        results.setEnabled(false);
        results.addActionListener(e -> {
            try {
                Desktop.getDesktop().open(AppState.resultPath.toFile());
            } catch (IOException ioException) {
                System.out.println("File Not Found");
            }
        });
        this.add(results);

        analyse.setBounds((int) (this.getWidth() * 0.025), 190, (int) (this.getWidth() * 0.9), 50);
        this.add(analyse);
    }
}
