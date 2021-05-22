package methodBank.customizers;

import model.AugmentationMethod;
import model.BrightnessMethod;
import utils.InputNumberPanel;
import utils.MyLogger;

import javax.swing.*;

public class BrightnessCustomizerPanel extends JPanel implements Customizer {
    public InputNumberPanel brightnessPanel;
    public BrightnessMethod brightnessMethod;

    public BrightnessCustomizerPanel(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        brightnessPanel = new InputNumberPanel(0, 0, "Brightness: ");
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

        MyLogger.log("MODEL", "Saving...");
    }
}
