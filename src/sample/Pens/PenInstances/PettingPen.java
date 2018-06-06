package sample.Pens.PenInstances;

import sample.PenType;
import sample.Pens.Pen;

public class PettingPen extends Pen {

    public PettingPen(int length, int width, int temp){
        super(PenType.PETTING, length, width, temp);
    }
}

