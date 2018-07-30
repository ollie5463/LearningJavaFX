package zoo.Pens.PenInstances;

import zoo.PenType;
import zoo.Pens.Pen;

public class Aviary extends Pen {
    int height;

    public int getHeight() {
        return height;
    }

    public Aviary(int length, int width, int height, int temp, String penName) {
        super(PenType.AVIARY, length, width, temp, penName);
        this.height = height;
        this.availableSpace = length * width * height;
    }
}
