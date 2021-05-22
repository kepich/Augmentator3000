package mainForm;

import org.apache.commons.io.FilenameUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.FileFilter;

public class FileList extends JScrollPane {
    public JList<File> fileList;

    public static FileList instance(String path) {
        File f;

        if (path != null) {
            f = new File(path);
        } else {
            f = new File(System.getProperty("user.home"));
        }

        JList<File> fileList = new JList<>(f.listFiles(new TextFileFilter()));
        fileList.setCellRenderer(new FileRenderer());
        fileList.setVisibleRowCount(30);
        fileList.setFixedCellWidth(250);

        return new FileList(fileList);
    }

    public FileList(JList<File> list) {
        super(list);
        fileList = list;
    }

    static class FileRenderer extends DefaultListCellRenderer {
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
            File f = (File) value;
            l.setText(f.getName());
            l.setIcon(FileSystemView.getFileSystemView().getSystemIcon(f));

            return l;
        }
    }

    public static class TextFileFilter implements FileFilter {

        public boolean accept(File file) {
            return FilenameUtils.getExtension(file.getName().toLowerCase()).equals("jpg");
        }
    }
}