package UI.methodBankForm.customizer.inputs;

import javax.swing.*;

public class InputTablePanel extends JPanel {
    public JLabel name;

    public JTable inputFrom;
    public JTable inputTo;
    public JTable inputStep;

    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel stepLabel;

    public InputTablePanel(int x, int y, String description){
        this.setLayout(null);

        this.setBounds(x, y, 500, 300);
        initializeComponents(description);
    }

    private void initializeComponents(String description) {
        name = new JLabel(description);
        name.setBounds(0, 25, 100, 25);
        this.add(name);
        String[][] data = {
                {"0.0", "0.0", "0.0"},
                {"0.0", "0.0", "0.0"},
                {"0.0", "0.0", "0.0"}
        };
        String[] columns = {"", "", ""};

        inputFrom = new JTable(data, columns);
        inputFrom.setBounds(name.getWidth() + 10, 25, 120, 47);
        inputFrom.getColumnModel().getColumn(0).setMinWidth(40);
        inputFrom.getColumnModel().getColumn(1).setMinWidth(40);
        inputFrom.getColumnModel().getColumn(2).setMinWidth(40);
        this.add(inputFrom);
        fromLabel = new JLabel("from");
        fromLabel.setBounds(inputFrom.getX(), inputFrom.getY() - 25, 50, 25);
        this.add(fromLabel);

        data = new String[][]{
                {"0.0", "0.0", "0.0"},
                {"0.0", "0.0", "0.0"},
                {"0.0", "0.0", "0.0"}
        };
        inputTo = new JTable(data, columns);
        inputTo.setBounds(name.getWidth() + inputFrom.getWidth() + 20, 25, 120, 47);
        inputTo.getColumnModel().getColumn(0).setMinWidth(40);
        inputTo.getColumnModel().getColumn(1).setMinWidth(40);
        inputTo.getColumnModel().getColumn(2).setMinWidth(40);
        this.add(inputTo);
        toLabel = new JLabel("to");
        toLabel.setBounds(inputTo.getX(), inputTo.getY() - 25, 50, 25);
        this.add(toLabel);

        data = new String[][]{
                {"0.0", "0.0", "0.0"},
                {"0.0", "0.0", "0.0"},
                {"0.0", "0.0", "0.0"}
        };
        inputStep = new JTable(data, columns);
        inputStep.setBounds(name.getWidth() + inputFrom.getWidth() + inputTo.getWidth() + 30, 25, 120, 47);
        inputStep.getColumnModel().getColumn(0).setMinWidth(40);
        inputStep.getColumnModel().getColumn(1).setMinWidth(40);
        inputStep.getColumnModel().getColumn(2).setMinWidth(40);
        this.add(inputStep);
        stepLabel = new JLabel("step");
        stepLabel.setBounds(inputStep.getX(), inputStep.getY() - 25, 50, 25);
        this.add(stepLabel);
    }

    public void setDataFrom(double[][] matrixFrom) {
        setData(matrixFrom, inputFrom);
    }

    public void setDataTo(double[][] matrixTo) {
        setData(matrixTo, inputTo);
    }

    public void setDataStep(double[][] matrixStep) {
        setData(matrixStep, inputStep);
    }

    public double[][] getDataFrom() {
        return getData(inputFrom);
    }

    public double[][] getDataTo() {
        return getData(inputTo);
    }

    public double[][] getDataStep() {
        return getData(inputStep);
    }

    private void setData(double[][] matrix, JTable table){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                table.setValueAt(String.valueOf(matrix[i][j]), i, j);
            }
        }
    }

    private double[][] getData(JTable table){
        double[][] matrix = new double[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                matrix[i][j] = Double.parseDouble((String) table.getValueAt(i, j));
            }
        }

        return matrix;
    }
}
