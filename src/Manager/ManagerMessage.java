package Manager;

import BESA.Kernel.Agent.Event.DataBESA;

public class ManagerMessage extends DataBESA {
    private ManagerMessageType type;
    private String metaData;
    private String from;

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
}
