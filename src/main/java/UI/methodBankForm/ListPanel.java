package UI.methodBankForm;

import model.AugmentationMethod;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ListPanel extends JPanel {
    public MethodList list;
    private JButton delete;
    private JButton add;

    private int buttonWidth = 60;
    private int buttonHeight = 20;

    public ListPanel(int x, int y, int width, int height, Vector<AugmentationMethod> data) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);

        initComponents(data);
    }

    public void addListSelectionListener(ListSelectionListener listSelectionListener){
        list.addListSelectionListener(listSelectionListener);
    }

    public AugmentationMethod getSelectedValue(){
        return list.methods.getSelectedValue();
    }

    private void initComponents(Vector<AugmentationMethod> data) {
        list = new MethodList(data);
        list.setBounds(0, buttonHeight, this.getWidth(), this.getHeight() - buttonHeight);
        this.add(list);

        delete = new JButton("Del");
        delete.setBounds(list.getWidth() - buttonWidth, 0, buttonWidth, buttonHeight);
        this.add(delete);

        add = new JButton("Add");
        add.setBounds(list.getWidth() - buttonWidth * 2, 0, buttonWidth, buttonHeight);
        this.add(add);
    }

    public void addAddListener(ActionListener actionListener){
        this.add.addActionListener(actionListener);
    }

    public void addDeleteListener(ActionListener actionListener){
        this.delete.addActionListener(actionListener);
    }
}
