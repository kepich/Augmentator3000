package methodBank;

import addNewComboMethodForm.NewComboMethodForm;
import addNewMethodForm.AddNewMethodForm;
import mainForm.MainFrame;
import model.AugmentationMethod;
import model.AugmentationMethodComposite;
import utils.MyLogger;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class MethodPanel extends JPanel {
    public ListPanel comboMethodsPanel;
    public ListPanel methodDescriptionPanel;

    public MethodCustomizationPanel methodCustomizationPanel;

    public MethodPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        JLabel comboMethodsPanelLabel = new JLabel("Combo methods:");
        comboMethodsPanelLabel.setBounds(140, 15, 100, 20);
        this.add(comboMethodsPanelLabel);

        comboMethodsPanel = new ListPanel(
                (int) (this.getWidth() * 0.05),
                (int) (this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.2),
                (int) (this.getHeight() * 0.85),
                MainFrame.methods);
        this.add(comboMethodsPanel);
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
                    MainFrame.methods.add(newObject);
                    newComboMethodForm.dispose();
                    comboMethodsPanel.list.methods.setListData(MainFrame.methods);
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
                MainFrame.methods.remove(comboMethodsPanel.list.methods.getSelectedValue());
                comboMethodsPanel.updateUI();
                methodDescriptionPanel.list.methods.setListData(new Vector<>());
                methodDescriptionPanel.list.methods.clearSelection();
                methodDescriptionPanel.updateUI();
            }
        });

        JLabel methodDescriptionPanelLabel = new JLabel("Method description:");
        methodDescriptionPanelLabel.setBounds(460, 15, 150, 20);
        this.add(methodDescriptionPanelLabel);

        methodDescriptionPanel = new ListPanel(
                (int) (this.getWidth() * 0.3),
                (int) (this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.2),
                (int) (this.getHeight() * 0.85),
                new Vector<>());
        this.add(methodDescriptionPanel);
        methodDescriptionPanel.addListSelectionListener(e -> {
            AugmentationMethod selected = ((JList<AugmentationMethod>) e.getSource()).getSelectedValue();

            if (selected != null) {
                methodCustomizationPanel.showPanel(selected);

                MyLogger.log(MethodPanel.class.getName(), e.toString());
            }
        });


        methodDescriptionPanel.addAddListener(e -> {
            if (comboMethodsPanel.list.methods.getSelectedValue() != null) {
                AddNewMethodForm addNewMethodForm = new AddNewMethodForm();

                addNewMethodForm.acceptButton.addActionListener(a -> {
                    if (addNewMethodForm.nameField.getSelectedItem() != null) {
                        AugmentationMethod newObject = (AugmentationMethod) addNewMethodForm.nameField.getSelectedItem();
                        ((AugmentationMethodComposite) (comboMethodsPanel.list.methods.getSelectedValue())).methods.add(newObject);
                        methodDescriptionPanel.updateUI();
                        addNewMethodForm.dispose();

                        methodDescriptionPanel.list.methods.setListData(((AugmentationMethodComposite) (comboMethodsPanel.list.methods.getSelectedValue())).methods);
                        methodDescriptionPanel.list.methods.setSelectedValue(newObject, true);
                        methodDescriptionPanel.list.methods.updateUI();
                        methodDescriptionPanel.updateUI();
                    }
                });

                addNewMethodForm.cancelButton.addActionListener(a -> {
                    addNewMethodForm.dispose();
                });

                for (AugmentationMethod method : MainFrame.simpleMethods) {
                    if (method != comboMethodsPanel.list.methods.getSelectedValue()) {
                        addNewMethodForm.nameField.addItem(method.clone());
                    }
                }

                addNewMethodForm.setVisible(true);
            }
        });
        methodDescriptionPanel.addDeleteListener(e -> {
            if (comboMethodsPanel.list.methods.getSelectedValue() != null) {
                ((AugmentationMethodComposite) (comboMethodsPanel.list.methods.getSelectedValue())).methods.remove(methodDescriptionPanel.list.methods.getSelectedValue());
                methodCustomizationPanel.setVisible(false);
                methodDescriptionPanel.updateUI();
            }
        });

        JLabel methodCustomizationPanelLabel = new JLabel("Method customization:");
        methodCustomizationPanelLabel.setBounds(920, 15, 200, 20);
        this.add(methodCustomizationPanelLabel);

        methodCustomizationPanel = new MethodCustomizationPanel((int) (this.getWidth() * 0.55), (int) (this.getHeight() * 0.05), (int) (this.getWidth() * 0.4), (int) (this.getHeight() * 0.85));
        methodCustomizationPanel.setBackground(new Color(0x8C4848));
        methodCustomizationPanel.setVisible(false);
        this.add(methodCustomizationPanel);
    }
}
