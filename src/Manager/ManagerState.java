package Manager;

import BESA.Kernel.Agent.StateBESA;

import java.util.ArrayList;

public class ManagerState extends StateBESA {

    private ArrayList<String> neighborsAssigned;

    public ManagerState(){
        this.neighborsAssigned =  new ArrayList<>();
    }

    public void addNeighbor(String neighbor) {
        this.neighborsAssigned.add(neighbor);
    }

    public ArrayList<String> getNeighborsAssigned() {
        return neighborsAssigned;
    }
}
