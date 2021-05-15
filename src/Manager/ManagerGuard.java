package Manager;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Environment.EnvironmentGuard;
import Environment.EnvironmentMessage;
import Environment.EnvironmentMessageType;

public class ManagerGuard extends GuardBESA {
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        AgHandlerBESA ah;
        System.out.println("Enviando mensaje");
        try {
            ah = this.agent.getAdmLocal().getHandlerByAlias("EnvironmentAgent");
            EnvironmentMessage message = new EnvironmentMessage(EnvironmentMessageType.HELLO);
            message.setFrom("ManagerAgent01");
            message.setMetaData("Hola desde agente manageeeer");
            EventBESA msj = new EventBESA(
                    EnvironmentGuard.class.getName(),
                    message
            );
            ah.sendEvent(msj);
        } catch (ExceptionBESA exceptionBESA) {
            exceptionBESA.printStackTrace();
        }
    }
}
