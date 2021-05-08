package Environment;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BenchmarkTools.*;
import ContainersLauncher.BenchmarkConfig;
import FibonacciAgent.FibonacciAgentGuard;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvironmentContainer {

    public static void main(String[] args) throws KernellAgentExceptionBESA {
        ReportBESA.info("Lanzando config/environment.xml");
        AdmBESA adminBesa = AdmBESA.getInstance("config/environment.xml");
        BenchmarkConfig config = new BenchmarkConfig();

        EnvironmentState environmentState = new EnvironmentState();
        StructBESA StructSender = new StructBESA();
        StructSender.bindGuard(EnvironmentGuard.class);
        EnvironmentAgent environmentAgent = new EnvironmentAgent("environment", environmentState, StructSender, 0.91);
        environmentAgent.start();
        adminBesa.registerAgent(environmentAgent, "EnvironmentAgent", "EnvironmentAgent");

        /*AgHandlerBESA ah;
        try {
            ah = adminBesa.getHandlerByAlias("BenchmarkAgent");
            EventBESA msj = new EventBESA(
                    BenchmarkAgentSenderGuard.class.getName(),
                    new BenchmarkAgentMessage(
                            config.getNumberOfContainers(),
                            config.getNumberOfAgentsPerContainer()
                    )
            );
            ah.sendEvent(msj);
        } catch (ExceptionBESA ex) {
            Logger.getLogger(FibonacciAgentGuard.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }
}
