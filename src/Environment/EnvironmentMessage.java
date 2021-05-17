package Environment;

import BESA.Kernel.Agent.Event.DataBESA;

public class EnvironmentMessage extends DataBESA {

    private EnvironmentMessageType type;

    private String from;
    private String to;

    private String metaData;
    private String aliasSender;
    private String neighbors;
    private String userId;

    public EnvironmentMessage(EnvironmentMessageType type) {
        this.type = type;
    }

    public EnvironmentMessageType getType() {
        return type;
    }

    public void setType(EnvironmentMessageType type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(String neighbors) {
        this.neighbors = neighbors;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMetaData() {
        return metaData;
    }

    public void setMetaData(String metaData) {
        this.metaData = metaData;
    }

    public String getAliasSender() {
        return aliasSender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAliasSender(String aliasSender) {
        this.aliasSender = aliasSender;
    }
}
