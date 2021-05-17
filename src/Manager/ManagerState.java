package Manager;

import BESA.Kernel.Agent.StateBESA;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ManagerState extends StateBESA {

    private ArrayList<String> neighborsAssigned;
    private Hashtable<String,List<Offer>> allTripsManager;

    public ManagerState(){
        this.neighborsAssigned =  new ArrayList<>();
        this.allTripsManager = new Hashtable<>();
    }

    public void addNeighbor(String neighbor) {
        this.neighborsAssigned.add(neighbor);
    }

    public ArrayList<String> getNeighborsAssigned() {
        return neighborsAssigned;
    }

    public Hashtable<String,List<Offer>> getAllTripsManager() {
        return allTripsManager;
    }

    public void setAllTripsManager(Hashtable<String,List<Offer>>  allTripsManager) {
        this.allTripsManager = allTripsManager;
    }
}
