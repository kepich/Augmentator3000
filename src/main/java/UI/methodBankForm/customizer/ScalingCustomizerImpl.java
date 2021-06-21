package UI.methodBankForm.customizer;

import UI.methodBankForm.customizer.inputs.InputNumberPanel;
import model.AugmentationMethod;
import model.scaling.ScalingMethodIterator;

import javax.swing.*;

public class ScalingCustomizerImpl extends JPanel implements Customizer {
    public InputNumberPanel xScalePanel;
    public InputNumberPanel yScalePanel;

    public ScalingMethodIterator scalingMethodIterator;

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
        scalingMethodIterator = (ScalingMethodIterator) method;

        xScalePanel.inputFrom.setText(String.valueOf(scalingMethodIterator.xScaleFrom));
        xScalePanel.inputTo.setText(String.valueOf(scalingMethodIterator.xScaleTo));
        xScalePanel.inputStep.setText(String.valueOf(scalingMethodIterator.xScaleStep));

        yScalePanel.inputFrom.setText(String.valueOf(scalingMethodIterator.yScaleFrom));
        yScalePanel.inputTo.setText(String.valueOf(scalingMethodIterator.yScaleTo));
        yScalePanel.inputStep.setText(String.valueOf(scalingMethodIterator.yScaleStep));
    }

    @Override
    public void saveData() {
        scalingMethodIterator.xScaleFrom = Float.parseFloat(xScalePanel.inputFrom.getText());
        scalingMethodIterator.xScaleTo = Float.parseFloat(xScalePanel.inputTo.getText());
        scalingMethodIterator.xScaleStep = Float.parseFloat(xScalePanel.inputStep.getText());

        scalingMethodIterator.yScaleFrom = Float.parseFloat(yScalePanel.inputFrom.getText());
        scalingMethodIterator.yScaleTo = Float.parseFloat(yScalePanel.inputTo.getText());
        scalingMethodIterator.yScaleStep = Float.parseFloat(yScalePanel.inputStep.getText());
    }
}
