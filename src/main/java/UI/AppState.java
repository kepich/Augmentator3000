package UI;

import java.io.File;
import java.nio.file.Path;

public abstract class AppState {
    public static File selectedDirectory = new File(System.getProperty("user.home"));
    public static Path resultPath;
}
