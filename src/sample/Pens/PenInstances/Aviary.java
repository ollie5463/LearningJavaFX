package sample.Pens.PenInstances;

import sample.PenType;
import sample.Pens.Pen;

public class Aviary extends Pen {
    public Aviary(int length, int width, int height, int temp) {
        super(PenType.AVIARY, length, width, temp);
        this.availableSpace = length * width * height;
    }
}
