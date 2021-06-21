package UI.methodBankForm.customizer;

import UI.methodBankForm.customizer.inputs.InputNumberPanel;
import model.AugmentationMethod;
import model.brightness.BrightnessMethodIterator;

import javax.swing.*;

public class BrightnessCustomizerImpl extends JPanel implements Customizer {
    public InputNumberPanel brightnessPanel = new InputNumberPanel(0, 0, "Brightness: ");
    public BrightnessMethodIterator brightnessMethodIterator;

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
        brightnessMethodIterator = (BrightnessMethodIterator) method;

        brightnessPanel.inputFrom.setText(String.valueOf(brightnessMethodIterator.brightnessFrom));
        brightnessPanel.inputTo.setText(String.valueOf(brightnessMethodIterator.brightnessTo));
        brightnessPanel.inputStep.setText(String.valueOf(brightnessMethodIterator.brightnessStep));
    }

    @Override
    public void saveData() {
        brightnessMethodIterator.brightnessFrom = Float.parseFloat(brightnessPanel.inputFrom.getText());
        brightnessMethodIterator.brightnessTo = Float.parseFloat(brightnessPanel.inputTo.getText());
        brightnessMethodIterator.brightnessStep = Float.parseFloat(brightnessPanel.inputStep.getText());
    }
}
