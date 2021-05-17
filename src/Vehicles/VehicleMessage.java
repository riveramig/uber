package Vehicles;

import BESA.Kernel.Agent.Event.DataBESA;

public class VehicleMessage extends DataBESA {

    private String from;
    private String to;
    private String userId;
    private String managerId;
    private double cost;
    private VehicleMessageType type;

    public VehicleMessage(VehicleMessageType type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public VehicleMessageType getType() {
        return type;
    }

    public void setType(VehicleMessageType type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
