package Vehicles;

import BESA.Kernel.Agent.StateBESA;
import Graph.GraphWeighted;

public class VehicleState extends StateBESA {
    private String vehicleId;

    private VehicleType vehicleType;

    private String currentNodeLocation;

    private GraphWeighted graph;

    private boolean isEmpty;

    public  VehicleState(){}
   
    public VehicleState(String vehicleId,VehicleType vehicleType, String initialPlace) {
       this.vehicleId = vehicleId;
       this.vehicleType = vehicleType;
       this.currentNodeLocation = initialPlace;
       this.isEmpty = false;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCurrentNodeLocation() {
        return currentNodeLocation;
    }

    public void setCurrentNodeLocation(String currentNodeLocation) {
        this.currentNodeLocation = currentNodeLocation;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public GraphWeighted getGraph() {
        return graph;
    }

    public void setGraph(GraphWeighted environ) {
        this.graph = environ;
    }
}

