package utils;

import javax.swing.*;

public class InputNumberPanel extends JPanel {
    public JLabel name;

    public JTextField inputFrom;
    public JTextField inputTo;
    public JTextField inputStep;

    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel stepLabel;

    public InputNumberPanel(int x, int y, String description){
        this.setLayout(null);

        this.setBounds(x, y, 300, 60);
        initializeComponents(description);
    }

    private void initializeComponents(String description) {
        name = new JLabel(description);
        name.setBounds(0, 25, 100, 25);
        this.add(name);

        inputFrom = new JTextField();
        inputFrom.setBounds(name.getWidth() + 10, 25, 40, 25);
        this.add(inputFrom);
        fromLabel = new JLabel("from");
        fromLabel.setBounds(inputFrom.getX(), inputFrom.getY() - 25, 50, 25);
        this.add(fromLabel);

        inputTo = new JTextField();
        inputTo.setBounds(name.getWidth() + inputFrom.getWidth() + 20, 25, 40, 25);
        this.add(inputTo);
        toLabel = new JLabel("to");
        toLabel.setBounds(inputTo.getX(), inputTo.getY() - 25, 50, 25);
        this.add(toLabel);

        inputStep = new JTextField();
        inputStep.setBounds(name.getWidth() + inputFrom.getWidth() + inputTo.getWidth() + 30, 25, 40, 25);
        this.add(inputStep);
        stepLabel = new JLabel("step");
        stepLabel.setBounds(inputStep.getX(), inputStep.getY() - 25, 50, 25);
        this.add(stepLabel);
    }
}
