package Environment;

import BESA.Kernel.Agent.Event.DataBESA;

public class EnvironmentMessage extends DataBESA {

    private EnvironmentMessageType type;

    private String from;
    private String to;

    private String metaData;
    private String aliasSender;

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

    public void setAliasSender(String aliasSender) {
        this.aliasSender = aliasSender;
    }
}
