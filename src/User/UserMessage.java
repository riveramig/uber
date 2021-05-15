package User;

import BESA.Kernel.Agent.Event.DataBESA;

public class UserMessage extends DataBESA {

    private String info;

    public UserMessage(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
