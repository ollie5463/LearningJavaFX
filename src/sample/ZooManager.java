package sample;

import java.util.ArrayList;

public class ZooManager {
    private static ArrayList<ZooKeeper> zooKeepers = new ArrayList<>();

    public ZooManager(){
        createZooKeepers();
    }

    private static void createZooKeepers(){
    }

    public static String getZooKeeper(String name){
        switch (name) {
            case "farhad":
                ZooKeeper farhad = new ZooKeeper("farhad", new ArrayList<PenType>() {{
                    add(PenType.AQUARIUM);
                    add(PenType.AVIARY);
                }});
                return farhad.toString();
            case "hardip":
                ZooKeeper hardip = new ZooKeeper("hardip", new ArrayList<PenType>() {{
                    add(PenType.DRY);
                    add(PenType.AVIARY);
                }});
                return hardip.toString();
            case "alan":
                ZooKeeper alan = new ZooKeeper("alan", new ArrayList<PenType>() {{
                    add(PenType.DRY);
                    add(PenType.PETTING);
                }});
                return alan.toString();
            case "alex":
                ZooKeeper alex = new ZooKeeper("alex", new ArrayList<PenType>() {{
                    add(PenType.AQUARIUM);
                    add(PenType.PARTWATERPARTDRY);
                }});
                return alex.toString();
            default:
                return "wrong";
        }
    }
}
