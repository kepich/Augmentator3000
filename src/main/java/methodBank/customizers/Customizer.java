package methodBank.customizers;

import model.AugmentationMethod;

public interface Customizer {
    void loadData(AugmentationMethod method);
    void saveData();
}
