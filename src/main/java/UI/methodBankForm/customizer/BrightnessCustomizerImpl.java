package UI.methodBankForm.customizer;

import model.AugmentationMethod;
import model.brightness.BrightnessMethod;
import UI.methodBankForm.customizer.inputs.InputNumberPanel;

import javax.swing.*;

public class BrightnessCustomizerImpl extends JPanel implements Customizer {
    public InputNumberPanel brightnessPanel = new InputNumberPanel(0, 0, "Brightness: ");
    public BrightnessMethod brightnessMethod;

    public BrightnessCustomizerImpl(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        this.add(brightnessPanel);
    }

    @Override
    public void loadData(AugmentationMethod method) {
        brightnessMethod = (BrightnessMethod) method;

        brightnessPanel.inputFrom.setText(String.valueOf(brightnessMethod.brightnessFrom));
        brightnessPanel.inputTo.setText(String.valueOf(brightnessMethod.brightnessTo));
        brightnessPanel.inputStep.setText(String.valueOf(brightnessMethod.brightnessStep));
    }

    @Override
    public void saveData() {
        brightnessMethod.brightnessFrom = Float.parseFloat(brightnessPanel.inputFrom.getText());
        brightnessMethod.brightnessTo = Float.parseFloat(brightnessPanel.inputTo.getText());
        brightnessMethod.brightnessStep = Float.parseFloat(brightnessPanel.inputStep.getText());
    }
}
