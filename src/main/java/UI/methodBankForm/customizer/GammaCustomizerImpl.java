package UI.methodBankForm.customizer;

import UI.methodBankForm.customizer.inputs.InputNumberPanel;
import model.AugmentationMethod;
import model.gamma.GammaMethod;

import javax.swing.*;

public class GammaCustomizerImpl extends JPanel implements Customizer {
    public InputNumberPanel gammaPanel = new InputNumberPanel(0, 0, "Gamma: ");
    public GammaMethod gammaMethod;

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
        gammaMethod = (GammaMethod) method;

        gammaPanel.inputFrom.setText(String.valueOf(gammaMethod.gammaFrom));
        gammaPanel.inputTo.setText(String.valueOf(gammaMethod.gammaTo));
        gammaPanel.inputStep.setText(String.valueOf(gammaMethod.gammaStep));
    }

    @Override
    public void saveData() {
        gammaMethod.gammaFrom = Float.parseFloat(gammaPanel.inputFrom.getText());
        gammaMethod.gammaTo = Float.parseFloat(gammaPanel.inputTo.getText());
        gammaMethod.gammaStep = Float.parseFloat(gammaPanel.inputStep.getText());
    }
}
