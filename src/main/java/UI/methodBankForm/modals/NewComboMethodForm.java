package UI.methodBankForm.modals;

import javax.swing.*;

public class NewComboMethodForm extends JDialog {
    private final int WIDTH = 360;
    private final int HEIGHT = 160;

    public JLabel nameLabel;
    public JTextField nameField;
    public JButton acceptButton;
    public JButton cancelButton;

    public NewComboMethodForm() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setModal(true);
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.getContentPane().setLayout(null);

        this.setTitle("Enter the name of method");

        initComponents();
    }

    private void initComponents() {
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(30, 30, 60, 20);
        this.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 30, 200, 20);
        this.add(nameField);

        acceptButton = new JButton("Save");
        acceptButton.setBounds(30, 70, 110, 20);
        this.add(acceptButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 70, 110, 20);
        this.add(cancelButton);
    }
}
