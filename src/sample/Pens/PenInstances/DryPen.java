package sample.Pens.PenInstances;

import sample.PenType;
import sample.Pens.Pen;

public class DryPen extends Pen {

    public DryPen(int length, int width, int temp) {
        super(PenType.DRY, length, width, temp);
        this.availableSpace = length * width;
    }
}
