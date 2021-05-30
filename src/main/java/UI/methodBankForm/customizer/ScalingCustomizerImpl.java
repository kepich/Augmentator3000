package UI.methodBankForm.customizer;

import model.AugmentationMethod;
import model.scaling.ScalingMethod;
import UI.methodBankForm.customizer.inputs.InputNumberPanel;

import javax.swing.*;

public class ScalingCustomizerImpl extends JPanel implements Customizer {
    public InputNumberPanel xScalePanel;
    public InputNumberPanel yScalePanel;

    public ScalingMethod scalingMethod;

    public ScalingCustomizerImpl(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        xScalePanel = new InputNumberPanel(0, 0, "X mul: ");
        this.add(xScalePanel);

        yScalePanel = new InputNumberPanel(0, xScalePanel.getHeight(), "Y mul: ");
        this.add(yScalePanel);
    }

    @Override
    public void loadData(AugmentationMethod method) {
        scalingMethod = (ScalingMethod) method;

        xScalePanel.inputFrom.setText(String.valueOf(scalingMethod.xScaleFrom));
        xScalePanel.inputTo.setText(String.valueOf(scalingMethod.xScaleTo));
        xScalePanel.inputStep.setText(String.valueOf(scalingMethod.xScaleStep));

        yScalePanel.inputFrom.setText(String.valueOf(scalingMethod.yScaleFrom));
        yScalePanel.inputTo.setText(String.valueOf(scalingMethod.yScaleTo));
        yScalePanel.inputStep.setText(String.valueOf(scalingMethod.yScaleStep));
    }

    @Override
    public void saveData() {
        scalingMethod.xScaleFrom = Float.parseFloat(xScalePanel.inputFrom.getText());
        scalingMethod.xScaleTo = Float.parseFloat(xScalePanel.inputTo.getText());
        scalingMethod.xScaleStep = Float.parseFloat(xScalePanel.inputStep.getText());

        scalingMethod.yScaleFrom = Float.parseFloat(yScalePanel.inputFrom.getText());
        scalingMethod.yScaleTo = Float.parseFloat(yScalePanel.inputTo.getText());
        scalingMethod.yScaleStep = Float.parseFloat(yScalePanel.inputStep.getText());
    }
}
