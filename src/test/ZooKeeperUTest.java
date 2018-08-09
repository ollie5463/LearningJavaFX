package test;


import org.junit.Test;
import zoo.Pens.PenType;
import zoo.ZooKeeper;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ZooKeeperUTest {

    @Test
    public void createNewZooKeeper(){
        ZooKeeper zooKeeper = new ZooKeeper("rupesh", new ArrayList<>(){{add(PenType.AQUARIUM);add(PenType.DRY);}});
        assertEquals("rupesh", zooKeeper.getName());

    }
}
