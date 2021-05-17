package Manager;

import BESA.Kernel.Agent.Event.DataBESA;

import java.util.ArrayList;
import java.util.List;

public class ManagerMessage extends DataBESA {
    private ManagerMessageType type;
    private String metaData;
    private String from;
    private double costTrip;
    private String userId;
    private String fromNode;
    private String toNode;

    public ManagerMessage(ManagerMessageType type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public ManagerMessageType getType() {
        return type;
    }

    public void setType(ManagerMessageType type) {
        this.type = type;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromNode() {
        return fromNode;
    }

    public void setFromNode(String fromNode) {
        this.fromNode = fromNode;
    }

    public String getToNode() {
        return toNode;
    }

    public void setToNode(String toNode) {
        this.toNode = toNode;
    }

    public double getCostTrip() {
        return costTrip;
    }

    public void setCostTrip(double costTrip) {
        this.costTrip = costTrip;
    }
}
