package methods;

import model.AugmentationMethod;
import model.brightness.BrightnessMethodIterator;
import model.gamma.GammaMethodIterator;
import model.projection.ProjectionMethodIterator;
import model.scaling.ScalingMethodIterator;
import utils.MyLogger;

import java.io.*;
import java.util.Vector;

public abstract class MethodDataBank {
    public static final Vector<AugmentationMethod> methods = new Vector<>();
    public static final Vector<AugmentationMethod> simpleMethods = new Vector<>();

    static {
        loadData();

        simpleMethods.add(new ScalingMethodIterator());
        simpleMethods.add(new BrightnessMethodIterator());
        simpleMethods.add(new ProjectionMethodIterator());
        simpleMethods.add(new GammaMethodIterator());
    }

    public static void loadData() {
        try {
            FileInputStream fin = new FileInputStream("methods.data");
            ObjectInputStream in = new ObjectInputStream(fin);
            methods.addAll((Vector<AugmentationMethod>) in.readObject());
            in.close();
            MyLogger.log("MODEL", "Loading...");
        } catch (IOException | ClassNotFoundException ignored) {
            ignored.printStackTrace();
        }
    }

    public static void saveData() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("methods.data"));
            out.writeObject(methods);
            out.flush();
            out.close();
            MyLogger.log("MODEL", "Saving...");
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}
