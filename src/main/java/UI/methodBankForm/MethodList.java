package UI.methodBankForm;

import model.AugmentationMethod;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;

public class MethodList extends JScrollPane {
    public JList<AugmentationMethod> methods;

    private MethodList(JList<AugmentationMethod> list) {
        super(list);
        methods = list;
    }

    public void addListSelectionListener(ListSelectionListener listSelectionListener) {
        methods.addListSelectionListener(listSelectionListener);
    }

    public MethodList(Vector<AugmentationMethod> dataList) {
        this(new JList<>());

        this.methods.setListData(dataList);

        methods.setCellRenderer(new MethodRenderer());
        methods.setVisibleRowCount(30);
        methods.setFixedCellWidth(this.getWidth());
    }

    static class MethodRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(
                JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            Component c = super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
            JLabel l = (JLabel) c;
            AugmentationMethod method = (AugmentationMethod) value;
            l.setText(method.toString());

            return l;
        }
    }
}