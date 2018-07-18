package zoo.Pens.PenInstances;

import zoo.PenType;
import zoo.Pens.Pen;

public class Aquarium extends Pen {
    public Aquarium(int length, int width, int height, int temp){
        super(PenType.AQUARIUM, length, width, temp);
        this.availableSpace = length * width * height;
    }
}