package sample;

import java.util.ArrayList;

import java.util.ArrayList;

public class ZooKeeper {
    private String name;
    private ArrayList<PenType> pensResponsibleFor = new ArrayList<>();

    ZooKeeper(String name, ArrayList<PenType> pensResponsibleFor){
        this.name = name;
        this.pensResponsibleFor = pensResponsibleFor;
    }

    @Override
    public String toString() {
        return this.name + this.pensResponsibleFor;
    }

    public String getName(){
        return this.name;
    }
    public ArrayList<PenType> getPensResponsibleFor(){
        return this.pensResponsibleFor;
    }

}
