package acceptForm;

import mainForm.MainFrame;
import model.AugmentationMethod;
import model.AugmentationMethodComposite;
import progressFrame.ProgressForm;

import javax.swing.*;

public class AcceptForm extends JDialog {
    private final int WIDTH = 360;
    private final int HEIGHT = 160;
    private final JFileChooser fileChooser;
    private final JButton results;

    public JComboBox<AugmentationMethod> nameField;
    public JButton acceptButton;
    public JButton cancelButton;
    public ProgressForm progressForm;

    public AcceptForm(JFileChooser fileChooser, JButton results) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.fileChooser = fileChooser;
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
        for(AugmentationMethod augmentationMethod: MainFrame.methods){
            nameField.addItem(augmentationMethod);
        }
        this.add(nameField);

        acceptButton = new JButton("Start");
        acceptButton.setBounds(30, 70, 110, 20);
        if(MainFrame.methods.isEmpty()){
            acceptButton.setEnabled(false);
        }
        acceptButton.addActionListener(e -> {
            progressForm = new ProgressForm(fileChooser, (AugmentationMethodComposite)nameField.getSelectedItem(), results);
            this.dispose();
            progressForm.setVisible(true);
        });
        this.add(acceptButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 70, 110, 20);
        this.add(cancelButton);
    }
}
