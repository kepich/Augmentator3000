package UI.methodBankForm.customizer;

import UI.methodBankForm.customizer.inputs.InputNumberPanel;
import model.AugmentationMethod;
import model.projection.ProjectionMethodIterator;

import javax.swing.*;

public class ProjectionCustomizerImpl extends JPanel implements Customizer {
    public InputNumberPanel xLU = new InputNumberPanel(0, 0, "Left Up X: ");
    public InputNumberPanel yLU = new InputNumberPanel(0, 0, "Left Up Y: ");

    public InputNumberPanel xRU = new InputNumberPanel(0, 0, "Right Up X: ");
    public InputNumberPanel yRU = new InputNumberPanel(0, 0, "Right Up Y: ");

    public InputNumberPanel xRD = new InputNumberPanel(0, 0, "Right Down X: ");
    public InputNumberPanel yRD = new InputNumberPanel(0, 0, "Right Down Y: ");

    public InputNumberPanel xLD = new InputNumberPanel(0, 0, "Left Down X: ");
    public InputNumberPanel yLD = new InputNumberPanel(0, 0, "Left Down Y: ");

    public ProjectionMethodIterator projectionMethodIterator;

    public ProjectionCustomizerImpl(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents();
    }

    private void initComponents() {
        xLU = new InputNumberPanel(0, 0, "Left Up X: ");
        yLU = new InputNumberPanel(0, xLU.getY() + xLU.getHeight(), "Left Up Y: ");
        this.add(xLU);
        this.add(yLU);

        xRU = new InputNumberPanel(0, yLU.getY() + yLU.getHeight() + 30, "Right Up X: ");
        yRU = new InputNumberPanel(0, xRU.getY() + xRU.getHeight(), "Right Up Y: ");
        this.add(xRU);
        this.add(yRU);

        xRD = new InputNumberPanel(0, yRU.getY() + yRU.getHeight() + 30, "Right Down X: ");
        yRD = new InputNumberPanel(0, xRD.getY() + xRD.getHeight(), "Right Down Y: ");
        this.add(xRD);
        this.add(yRD);

        xLD = new InputNumberPanel(0, yRD.getY() + yRD.getHeight() + 30, "Left Down X: ");
        yLD = new InputNumberPanel(0, xLD.getY() + xLD.getHeight(), "Left Down Y: ");
        this.add(xLD);
        this.add(yLD);
    }

    @Override
    public void loadData(AugmentationMethod method) {
        projectionMethodIterator = (ProjectionMethodIterator) method;

        xLU.inputFrom.setText(String.valueOf(projectionMethodIterator.xLU[0]));
        xLU.inputTo.setText(String.valueOf(projectionMethodIterator.xLU[1]));
        xLU.inputStep.setText(String.valueOf(projectionMethodIterator.xLU[2]));

        xRU.inputFrom.setText(String.valueOf(projectionMethodIterator.xRU[0]));
        xRU.inputTo.setText(String.valueOf(projectionMethodIterator.xRU[1]));
        xRU.inputStep.setText(String.valueOf(projectionMethodIterator.xRU[2]));

        xRD.inputFrom.setText(String.valueOf(projectionMethodIterator.xRD[0]));
        xRD.inputTo.setText(String.valueOf(projectionMethodIterator.xRD[1]));
        xRD.inputStep.setText(String.valueOf(projectionMethodIterator.xRD[2]));

        xLD.inputFrom.setText(String.valueOf(projectionMethodIterator.xLD[0]));
        xLD.inputTo.setText(String.valueOf(projectionMethodIterator.xLD[1]));
        xLD.inputStep.setText(String.valueOf(projectionMethodIterator.xLD[2]));

        yLU.inputFrom.setText(String.valueOf(projectionMethodIterator.yLU[0]));
        yLU.inputTo.setText(String.valueOf(projectionMethodIterator.yLU[1]));
        yLU.inputStep.setText(String.valueOf(projectionMethodIterator.yLU[2]));

        yRU.inputFrom.setText(String.valueOf(projectionMethodIterator.yRU[0]));
        yRU.inputTo.setText(String.valueOf(projectionMethodIterator.yRU[1]));
        yRU.inputStep.setText(String.valueOf(projectionMethodIterator.yRU[2]));

        yRD.inputFrom.setText(String.valueOf(projectionMethodIterator.yRD[0]));
        yRD.inputTo.setText(String.valueOf(projectionMethodIterator.yRD[1]));
        yRD.inputStep.setText(String.valueOf(projectionMethodIterator.yRD[2]));

        yLD.inputFrom.setText(String.valueOf(projectionMethodIterator.yLD[0]));
        yLD.inputTo.setText(String.valueOf(projectionMethodIterator.yLD[1]));
        yLD.inputStep.setText(String.valueOf(projectionMethodIterator.yLD[2]));
    }

    @Override
    public void saveData() {
        this.projectionMethodIterator.xLU = new int[]{
                Integer.parseInt(xLU.inputFrom.getText()),
                Integer.parseInt(xLU.inputTo.getText()),
                Integer.parseInt(xLU.inputStep.getText())
        };
        this.projectionMethodIterator.xRU = new int[]{
                Integer.parseInt(xRU.inputFrom.getText()),
                Integer.parseInt(xRU.inputTo.getText()),
                Integer.parseInt(xRU.inputStep.getText())
        };
        this.projectionMethodIterator.xRD = new int[]{
                Integer.parseInt(xRD.inputFrom.getText()),
                Integer.parseInt(xRD.inputTo.getText()),
                Integer.parseInt(xRD.inputStep.getText())
        };
        this.projectionMethodIterator.xLD = new int[]{
                Integer.parseInt(xLD.inputFrom.getText()),
                Integer.parseInt(xLD.inputTo.getText()),
                Integer.parseInt(xLD.inputStep.getText())
        };
        this.projectionMethodIterator.yLU = new int[]{
                Integer.parseInt(yLU.inputFrom.getText()),
                Integer.parseInt(yLU.inputTo.getText()),
                Integer.parseInt(yLU.inputStep.getText())
        };
        this.projectionMethodIterator.yRU = new int[]{
                Integer.parseInt(yRU.inputFrom.getText()),
                Integer.parseInt(yRU.inputTo.getText()),
                Integer.parseInt(yRU.inputStep.getText())
        };
        this.projectionMethodIterator.yRD = new int[]{
                Integer.parseInt(yRD.inputFrom.getText()),
                Integer.parseInt(yRD.inputTo.getText()),
                Integer.parseInt(yRD.inputStep.getText())
        };
        this.projectionMethodIterator.yLD = new int[]{
                Integer.parseInt(yLD.inputFrom.getText()),
                Integer.parseInt(yLD.inputTo.getText()),
                Integer.parseInt(yLD.inputStep.getText())
        };
    }
}
