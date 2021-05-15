package User;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StateBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Environment.EnvironmentGuard;
import Environment.EnvironmentMessage;
import Environment.EnvironmentMessageType;

public class UserAgent extends AgentBESA {
    public UserAgent(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA {
        super(alias, state, structAgent, passwd);
    }

    @Override
    public void setupAgent() {

        //ask for Random location
        try {
            UserState usState = (UserState)this.state;
            AgHandlerBESA ah = this.getAdmLocal().getHandlerByAlias("EnvironmentAgent");
            EnvironmentMessage environmentMessage = new EnvironmentMessage(EnvironmentMessageType.ASK_RANDOM_LOCATION);
            environmentMessage.setMetaData(usState.getInitNode());
            environmentMessage.setFrom(this.getAlias());
            EventBESA ev = new EventBESA(EnvironmentGuard.class.getName(),environmentMessage);
            ah.sendEvent(ev);
        } catch (ExceptionBESA exceptionBESA) {
            exceptionBESA.printStackTrace();
        }

    }

    @Override
    public void shutdownAgent() {

    }
}
