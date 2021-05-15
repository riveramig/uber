package User;

import BESA.Kernel.Agent.StateBESA;

public class UserState extends StateBESA {

    private String initNode;

    public UserState(String initNode) {
        this.initNode = initNode;
    }

    public String getInitNode() {
        return initNode;
    }

    public void setInitNode(String initNode) {
        this.initNode = initNode;
    }
}
