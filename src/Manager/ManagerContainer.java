package Manager;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Environment.EnvironmentAgent;
import Environment.EnvironmentGuard;
import Environment.EnvironmentMessage;
import Environment.EnvironmentMessageType;
import FibonacciAgent.FibonacciAgentGuard;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerContainer {
    public static void main(String[] args) throws KernellAgentExceptionBESA {

        ReportBESA.info("Lanzando config/manager.xml");
        AdmBESA adminBesa = AdmBESA.getInstance("config/manager.xml");

        ManagerState managerState = new ManagerState();
        StructBESA StructSender = new StructBESA();
        StructSender.bindGuard(ManagerGuard.class);
        ManagerAgent managerAgent = new ManagerAgent("ManagerAgent01", managerState, StructSender, 0.91);
        managerAgent.start();
        adminBesa.registerAgent(managerAgent, "ManagerAgent01", "ManagerAgent01");

        try {
            AgHandlerBESA handler = adminBesa.getHandlerByAlias("ManagerAgent01");
            EventBESA msj = new EventBESA(
                    ManagerGuard.class.getName(),
                    null
            );
            handler.sendEvent(msj);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(FibonacciAgentGuard.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
