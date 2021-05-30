package model.projection;

import utils.MethodThread;

import java.awt.image.BufferedImage;
import java.util.Vector;

public abstract class ProjectionMethodThread extends MethodThread {
    protected int[] lu;
    protected int[] ru;
    protected int[] rd;
    protected int[] ld;

    public ProjectionMethodThread(
            int[] lu, int[] ru, int[] rd, int[] ld,
            BufferedImage image,
            Vector<BufferedImage> storage
            ) {
        super(storage, image);

        this.lu = lu;
        this.ru = ru;
        this.rd = rd;
        this.ld = ld;
    }
}
