package UI.methodBankForm;

import UI.methodBankForm.customizer.BrightnessCustomizerImpl;
import UI.methodBankForm.customizer.Customizer;
import UI.methodBankForm.customizer.ScalingCustomizerImpl;
import UI.methodBankForm.customizer.ProjectionCustomizerImpl;
import methods.MethodDataBank;
import model.AugmentationMethod;

import javax.swing.*;

public class MethodCustomizationPanel extends JPanel {
    private ScalingCustomizerImpl scalingCustomizerImpl;
    private BrightnessCustomizerImpl brightnessCustomizerImpl;
    private ProjectionCustomizerImpl projectionCustomizerImpl;

    private Customizer activePanel;
    private JButton saveButton = new JButton("Save");

    public MethodCustomizationPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);

        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        scalingCustomizerImpl = new ScalingCustomizerImpl(
                (int)(this.getWidth() * 0.05),
                (int)(this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.90),
                100
        );
        scalingCustomizerImpl.setVisible(false);
        this.add(scalingCustomizerImpl);

        brightnessCustomizerImpl = new BrightnessCustomizerImpl(
                (int)(this.getWidth() * 0.05),
                (int)(this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.90),
                50
        );
        brightnessCustomizerImpl.setVisible(false);
        this.add(brightnessCustomizerImpl);

        projectionCustomizerImpl = new ProjectionCustomizerImpl(
                (int)(this.getWidth() * 0.05),
                (int)(this.getHeight() * 0.05),
                (int) (this.getWidth() * 0.90),
                500
        );
        projectionCustomizerImpl.setVisible(false);
        this.add(projectionCustomizerImpl);

        int buttonHeight = 30;
        int buttonWidth = 120;

        saveButton.setBounds(this.getWidth() - buttonWidth - (int)(this.getWidth() * 0.1), this.getHeight() - buttonHeight, buttonWidth, buttonHeight);
        saveButton.setVisible(true);
        saveButton.addActionListener(e -> {
            activePanel.saveData();
            MethodDataBank.saveData();
        });
        this.add(saveButton);
    }

    public void hideAll(){
        scalingCustomizerImpl.setVisible(false);
        brightnessCustomizerImpl.setVisible(false);
        projectionCustomizerImpl.setVisible(false);
    }

    public void showPanel(AugmentationMethod augmentationMethod){
        hideAll();
        switch (augmentationMethod.type) {
            case SCALING -> {
                this.setVisible(true);
                scalingCustomizerImpl.setVisible(true);
                activePanel = scalingCustomizerImpl;
                scalingCustomizerImpl.loadData(augmentationMethod);
            }
            case BRIGHTNESS -> {
                this.setVisible(true);
                brightnessCustomizerImpl.setVisible(true);
                activePanel = brightnessCustomizerImpl;
                brightnessCustomizerImpl.loadData(augmentationMethod);
            }
            case PROJECTION -> {
                this.setVisible(true);
                projectionCustomizerImpl.setVisible(true);
                activePanel = projectionCustomizerImpl;
                projectionCustomizerImpl.loadData(augmentationMethod);
            }
        }
    }
}
