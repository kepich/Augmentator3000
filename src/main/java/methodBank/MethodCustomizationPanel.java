package methodBank;

import methodBank.customizers.BrightnessCustomizerPanel;
import methodBank.customizers.Customizer;
import methodBank.customizers.ScalingCustomizerPanel;
import model.AugmentationMethod;
import model.AugmentationMethodType;

import javax.swing.*;

public class MethodCustomizationPanel extends JPanel {
    public ScalingCustomizerPanel scalingCustomizerPanel;
    public BrightnessCustomizerPanel brightnessCustomizerPanel;
    public Customizer activePanel;
    public JButton saveButton;

    public MethodCustomizationPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);

        this.setLayout(null);
        initComponents();
    }

    private void initComponents() {
        scalingCustomizerPanel = new ScalingCustomizerPanel((int)(this.getWidth() * 0.05), (int)(this.getHeight() * 0.05), this.getWidth(), 150);
        scalingCustomizerPanel.setVisible(false);
        this.add(scalingCustomizerPanel);

        brightnessCustomizerPanel = new BrightnessCustomizerPanel((int)(this.getWidth() * 0.05), (int)(this.getHeight() * 0.05), this.getWidth(), 150);
        brightnessCustomizerPanel.setVisible(false);
        this.add(brightnessCustomizerPanel);

        int buttonHeight = 30;
        int buttonWidth = 120;

        saveButton = new JButton("Save");
        saveButton.setBounds(this.getWidth() - buttonWidth - (int)(this.getWidth() * 0.1), this.getHeight() - buttonHeight, buttonWidth, buttonHeight);
        saveButton.setVisible(true);
        saveButton.addActionListener(e -> {
            activePanel.saveData();
        });
        this.add(saveButton);
    }

    public void hideAll(){
        scalingCustomizerPanel.setVisible(false);
        brightnessCustomizerPanel.setVisible(false);
    }

    public void showPanel(AugmentationMethod augmentationMethod){
        hideAll();
        switch (augmentationMethod.getType()) {
            case SCALING -> {
                this.setVisible(true);
                scalingCustomizerPanel.setVisible(true);
                activePanel = scalingCustomizerPanel;
                scalingCustomizerPanel.loadData(augmentationMethod);
            }
            case BRIGHTNESS -> {
                this.setVisible(true);
                brightnessCustomizerPanel.setVisible(true);
                activePanel = brightnessCustomizerPanel;
                brightnessCustomizerPanel.loadData(augmentationMethod);
            }
        }
    }
}
