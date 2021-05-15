package User;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Manager.ManagerGuard;
import Manager.ManagerMessage;
import Manager.ManagerMessageType;

public class UserGuard extends GuardBESA {
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        UserMessage userMessage = (UserMessage) eventBESA.getData();
        UserState userState = (UserState) this.agent.getState();
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("user: "+this.agent.getAlias());
                System.out.println("manager: "+"ManagerAgent0"+(i+1));
                System.out.println("trip: "+userState.getInitNode()+" "+userMessage.getInfo());
                AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias("ManagerAgent0"+(i+1));
                ManagerMessage message = new ManagerMessage(ManagerMessageType.CREATE_TRIP);
                //from+" "+to
                message.setMetaData(userState.getInitNode()+" "+userMessage.getInfo());
                message.setFrom(this.agent.getAlias());
                EventBESA ev = new EventBESA(ManagerGuard.class.getName(),message);
                ah.sendEvent(ev);
            }
        } catch (ExceptionBESA exceptionBESA) {
            exceptionBESA.printStackTrace();
        }
    }
}
