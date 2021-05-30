import UI.mainForm.MainForm;

import static org.opencv.core.Core.NATIVE_LIBRARY_NAME;

public class App {

    public static void main(String[] args){

        // TODO: Load data
        System.loadLibrary(NATIVE_LIBRARY_NAME);
        MainForm mainForm = new MainForm();

        mainForm.setVisible(true);
    }


}
