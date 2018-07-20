package zoo.Pens.PenInstances;

import zoo.PenType;
import zoo.Pens.Pen;

public class PettingPen extends Pen {

    public PettingPen(int length, int width, int temp, String penName){
        super(PenType.PETTING, length, width, temp, penName);
    }
}

