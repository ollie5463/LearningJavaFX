package zoo.Pens.PenInstances;

import zoo.PenType;
import zoo.Pens.Pen;

public class DryPen extends Pen {

    public DryPen(int length, int width, int temp, String penName) {
        super(PenType.DRY, length, width, temp, penName);
        this.availableSpace = length * width;
    }
}
