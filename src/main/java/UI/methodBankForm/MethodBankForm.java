package UI.methodBankForm;

import UI.methodBankForm.modals.AddNewMethodForm;
import UI.methodBankForm.modals.NewComboMethodForm;
import methods.MethodDataBank;
import model.AugmentationMethod;
import model.AugmentationMethodComposite;
import utils.MyLogger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class MethodBankForm extends JDialog {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    private ListPanel comboMethodsPanel;
    private ListPanel methodDescriptionPanel;

    private MethodCustomizationPanel methodCustomizationPanel;

    private final JLabel comboMethodsPanelLabel = new JLabel("Combo methods:");
    private final JLabel methodDescriptionPanelLabel = new JLabel("Method description:");
    private final JLabel methodCustomizationPanelLabel = new JLabel("Method customization:");

    public MethodBankForm() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                MethodDataBank.saveData();
                dispose();
            }
        });

        this.setTitle("Methods bank");
        this.setModal(true);
        this.setBounds(0, 0, WIDTH, HEIGHT);
        this.setResizable(false);
        this.getContentPane().setLayout(null);
        this.setBackground(new Color(0x488c4d));

        initComponents();
    }

    private void initComponents() {
        Container contentPane = getContentPane();

        comboMethodsPanelLabel.setBounds(140, 15, 100, 20);
        contentPane.add(comboMethodsPanelLabel);

        initComboMethodsPanel(contentPane);

        methodDescriptionPanelLabel.setBounds(460, 15, 150, 20);
        contentPane.add(methodDescriptionPanelLabel);

        initMethodDescriptionMethod(contentPane);

        methodCustomizationPanelLabel.setBounds(920, 15, 200, 20);
        contentPane.add(methodCustomizationPanelLabel);

        initMethodCustomizationPanel(contentPane);
    }

    private void initMethodCustomizationPanel(Container contentPane) {
        methodCustomizationPanel = new MethodCustomizationPanel(
                (int) (this.getWidth() * 0.55),
                (int) (this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.4),
                (int) (this.getHeight() * 0.85)
        );
        methodCustomizationPanel.setBackground(new Color(0x8C4848));
        methodCustomizationPanel.setVisible(false);
        contentPane.add(methodCustomizationPanel);
    }

    private void initMethodDescriptionMethod(Container contentPane) {
        methodDescriptionPanel = new ListPanel(
                (int) (this.getWidth() * 0.3),
                (int) (this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.2),
                (int) (this.getHeight() * 0.85),
                new Vector<>()
        );
        methodDescriptionPanel.addListSelectionListener(e -> {
            if (methodDescriptionPanel.getSelectedValue() != null) {
                methodCustomizationPanel.showPanel(methodDescriptionPanel.getSelectedValue());
                MyLogger.log(this.getClass().getName(), e.toString());
            }
        });
        methodDescriptionPanel.addAddListener(e -> {
            if (comboMethodsPanel.getSelectedValue() != null) {
                AddNewMethodForm addNewMethodForm = new AddNewMethodForm();

                addNewMethodForm.acceptButton.addActionListener(a -> {
                    if (addNewMethodForm.nameField.getSelectedItem() != null) {
                        AugmentationMethod newObject = (AugmentationMethod) addNewMethodForm.nameField.getSelectedItem();
                        ((AugmentationMethodComposite) (comboMethodsPanel.getSelectedValue())).methods.add(newObject);
                        addNewMethodForm.dispose();

                        methodDescriptionPanel.list.methods.setListData(((AugmentationMethodComposite) (comboMethodsPanel.getSelectedValue())).methods);
                        methodDescriptionPanel.list.methods.setSelectedValue(newObject, true);
                        methodDescriptionPanel.list.methods.updateUI();
                        methodDescriptionPanel.updateUI();
                    }
                });

                addNewMethodForm.cancelButton.addActionListener(a -> {
                    addNewMethodForm.dispose();
                });

                for (AugmentationMethod method : MethodDataBank.simpleMethods) {
                    if (method != comboMethodsPanel.getSelectedValue()) {
                        addNewMethodForm.nameField.addItem(method.clone());
                    }
                }

                addNewMethodForm.setVisible(true);
            }
        });
        methodDescriptionPanel.addDeleteListener(e -> {
            if (comboMethodsPanel.list.methods.getSelectedValue() != null) {
                ((AugmentationMethodComposite) (comboMethodsPanel.getSelectedValue())).methods.remove(methodDescriptionPanel.getSelectedValue());
                methodCustomizationPanel.setVisible(false);
                methodDescriptionPanel.updateUI();
                MethodDataBank.saveData();
            }
        });
        contentPane.add(methodDescriptionPanel);
    }

    private void initComboMethodsPanel(Container contentPane) {
        comboMethodsPanel = new ListPanel(
                (int) (this.getWidth() * 0.05),
                (int) (this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.2),
                (int) (this.getHeight() * 0.85),
                MethodDataBank.methods
        );
        comboMethodsPanel.addListSelectionListener(e -> {
            methodCustomizationPanel.setVisible(false);
            AugmentationMethodComposite selected = (AugmentationMethodComposite) ((JList<AugmentationMethod>) e.getSource()).getSelectedValue();
            if (selected != null) {
                methodDescriptionPanel.list.methods.setListData(selected.methods);
            } else {
                methodDescriptionPanel.list.methods.setListData(new Vector<>());
            }
        });
        comboMethodsPanel.addAddListener(e -> {
            NewComboMethodForm newComboMethodForm = new NewComboMethodForm();

            newComboMethodForm.acceptButton.addActionListener(a -> {
                if (!newComboMethodForm.nameField.getText().equals("")) {
                    AugmentationMethodComposite newObject = new AugmentationMethodComposite(newComboMethodForm.nameField.getText());
                    MethodDataBank.methods.add(newObject);
                    newComboMethodForm.dispose();
                    comboMethodsPanel.list.methods.setListData(MethodDataBank.methods);
                    comboMethodsPanel.list.methods.setSelectedValue(newObject, true);
                    comboMethodsPanel.list.methods.updateUI();
                    comboMethodsPanel.updateUI();
                }
            });

            newComboMethodForm.cancelButton.addActionListener(a -> {
                newComboMethodForm.dispose();
            });

            newComboMethodForm.setVisible(true);
        });
        comboMethodsPanel.addDeleteListener(e -> {
            if (comboMethodsPanel.list.methods.getSelectedValue() != null) {
                MethodDataBank.methods.remove(comboMethodsPanel.getSelectedValue());
                comboMethodsPanel.updateUI();
                methodDescriptionPanel.list.methods.setListData(new Vector<>());
                methodDescriptionPanel.list.methods.clearSelection();
                methodDescriptionPanel.updateUI();
                MethodDataBank.saveData();
            }
        });
        contentPane.add(comboMethodsPanel);
    }
}
