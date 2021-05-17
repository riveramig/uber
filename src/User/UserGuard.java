package User;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Manager.ManagerGuard;
import Manager.ManagerMessage;
import Manager.ManagerMessageType;

public class UserGuard extends GuardBESA {
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_RESET = "\u001B[0m";
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        UserMessage userMessage = (UserMessage) eventBESA.getData();
        UserState userState = (UserState) this.agent.getState();
        for (int i = 0; i < 3; i++) {
            boolean agentFound = false;
            while(!agentFound){
                String managerName = "ManagerAgent0"+(i+1);
                try {
                    System.out.println("user: "+this.agent.getAlias());
                    System.out.println("manager: "+managerName);
                    System.out.println("trip: "+userState.getInitNode()+" "+userMessage.getInfo());
                    AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(managerName);
                    ManagerMessage message = new ManagerMessage(ManagerMessageType.CREATE_TRIP);
                    //from+" "+to
                    message.setMetaData(userState.getInitNode()+" "+userMessage.getInfo());
                    message.setFrom(this.agent.getAlias());
                    EventBESA ev = new EventBESA(ManagerGuard.class.getName(),message);
                    ah.sendEvent(ev);
                    agentFound = true;
                }catch (ExceptionBESA exceptionBESA) {
                    System.out.println(ANSI_PURPLE_BACKGROUND+managerName+" not found"+ANSI_RESET);
                }
            }
        }
    }
}
