package zoo;


import zoo.Pens.PenType;

import java.util.ArrayList;

public class ZooKeeper {
    private String name;
    private ArrayList<PenType> penTypesResponsibleFor;
    private ArrayList<String> pensResponsibleFor = new ArrayList<>();

    public ZooKeeper(String name, ArrayList<PenType> penTypesResponsibleFor){
        this.name = name;
        this.penTypesResponsibleFor = penTypesResponsibleFor;
    }

    @Override
    public String toString() {
        return this.name ;
    }

    public void setPensResponsibleFor(String pensResponsibleFor){
        this.pensResponsibleFor.add(pensResponsibleFor);
    }

    public void setPensResponsibleFor(ArrayList<String> pensResponsibleFor){
        this.pensResponsibleFor = pensResponsibleFor;
    }

    public ArrayList<String> getPensResponsibleFor(){
        return this.pensResponsibleFor;
    }
    public ArrayList<PenType> getPenTypesResponsibleFor() {
        return penTypesResponsibleFor;
    }

}
