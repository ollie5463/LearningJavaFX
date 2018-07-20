package zoo.Pens.PenInstances;

import zoo.PenType;
import zoo.Pens.Pen;

public class Aviary extends Pen {
    public Aviary(int length, int width, int height, int temp, String penName) {
        super(PenType.AVIARY, length, width, temp, penName);
        this.availableSpace = length * width * height;
    }
}
