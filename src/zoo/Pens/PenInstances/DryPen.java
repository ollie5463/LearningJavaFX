package zoo.Pens.PenInstances;

import zoo.PenType;
import zoo.Pens.Pen;

public class DryPen extends Pen {

    public DryPen(int length, int width, int temp) {
        super(PenType.DRY, length, width, temp);
        this.availableSpace = length * width;
    }
}
