package User;

import BESA.Kernel.Agent.StateBESA;

public class VehicleState extends StateBESA {
    private String vehicleId;

    private String vehicleType;

    private String initialPlace;
   
    public VehicleState(String vehicleId,String vehicleType, String initialPlace) {
       this.vehicleId = vehicleId;
       this.vehicleType = vehicleType;
       this.initialPlace = initialPlace;
    }

}

