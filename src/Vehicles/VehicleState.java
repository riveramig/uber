package Vehicles;

import BESA.Kernel.Agent.StateBESA;

public class VehicleState extends StateBESA {
    private String vehicleId;

    private VehicleType vehicleType;

    private String initialPlace;

    public  VehicleState(){}
   
    public VehicleState(String vehicleId,VehicleType vehicleType, String initialPlace) {
       this.vehicleId = vehicleId;
       this.vehicleType = vehicleType;
       this.initialPlace = initialPlace;
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

    public String getInitialPlace() {
        return initialPlace;
    }

    public void setInitialPlace(String initialPlace) {
        this.initialPlace = initialPlace;
    }
}

