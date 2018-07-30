package zoo.Pens.PenInstances;

import zoo.PenType;
import zoo.Pens.Pen;

public class Aquarium extends Pen {
    private int waterVolume;
    private int height;

    public int getWaterVolume() {
        return waterVolume;
    }

    public int getHeight() {
        return height;
    }

    public Aquarium(int length, int width, int height, int temp, String penName){
        super(PenType.AQUARIUM, length, width, temp, penName);
        this.waterVolume = length * width * height;
        this.height = height;

//        this.availableSpace = length * width * height;
    }
}