package UI.startGenerationForm;

import methods.MethodDataBank;
import model.AugmentationMethod;
import model.AugmentationMethodComposite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGenerationForm extends JDialog {
    private final int WIDTH = 360;
    private final int HEIGHT = 160;
    private final JButton results;

    public JComboBox<AugmentationMethod> nameField;
    public JButton acceptButton;
    public JButton cancelButton;
    public ProgressForm progressForm;

    public StartGenerationForm(JButton results) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.results = results;

        this.setTitle("Choose method");
        this.setModal(true);
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.getContentPane().setLayout(null);


        initComponents();
    }

    private void initComponents() {
        nameField = new JComboBox();
        nameField.setBounds(100, 30, 200, 20);
        for(AugmentationMethod augmentationMethod: MethodDataBank.methods){
            nameField.addItem(augmentationMethod);
        }
        this.add(nameField);

        acceptButton = new JButton("Start");
        acceptButton.setBounds(30, 70, 110, 20);
        if(MethodDataBank.methods.isEmpty()){
            acceptButton.setEnabled(false);
        }
        acceptButton.addActionListener(e -> {
            progressForm = new ProgressForm((AugmentationMethodComposite)nameField.getSelectedItem(), results);
            progressForm.addFinishButtonActionListener(e1 -> progressForm.finish());
            this.dispose();
            progressForm.setVisible(true);
        });
        this.add(acceptButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 70, 110, 20);
        this.add(cancelButton);
    }
}
