package UI.methodBankForm.customizer;

import UI.methodBankForm.customizer.inputs.InputNumberPanel;
import model.AugmentationMethod;
import model.gamma.GammaMethodIterator;

import javax.swing.*;

public class GammaCustomizerImpl extends JPanel implements Customizer {
    public InputNumberPanel gammaPanel = new InputNumberPanel(0, 0, "Gamma: ");
    public GammaMethodIterator gammaMethodIterator;

    public GammaCustomizerImpl(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        this.add(gammaPanel);
    }

    @Override
    public void loadData(AugmentationMethod method) {
        gammaMethodIterator = (GammaMethodIterator) method;

        gammaPanel.inputFrom.setText(String.valueOf(gammaMethodIterator.gammaFrom));
        gammaPanel.inputTo.setText(String.valueOf(gammaMethodIterator.gammaTo));
        gammaPanel.inputStep.setText(String.valueOf(gammaMethodIterator.gammaStep));
    }

    @Override
    public void saveData() {
        gammaMethodIterator.gammaFrom = Float.parseFloat(gammaPanel.inputFrom.getText());
        gammaMethodIterator.gammaTo = Float.parseFloat(gammaPanel.inputTo.getText());
        gammaMethodIterator.gammaStep = Float.parseFloat(gammaPanel.inputStep.getText());
    }
}
