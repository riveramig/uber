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
import Graph.GraphWeighted;
import Graph.NodeWeighted;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvironmentContainer {

    public static void main(String[] args) throws KernellAgentExceptionBESA {
        ReportBESA.info("Lanzando config/environment.xml");
        AdmBESA adminBesa = AdmBESA.getInstance("config/environment.xml");
        BenchmarkConfig config = new BenchmarkConfig();

        EnvironmentState environmentState = new EnvironmentState(createGraph());
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

    public static synchronized GraphWeighted createGraph() {
        GraphWeighted graphWeighted = new GraphWeighted(true);
        NodeWeighted kennedy = new NodeWeighted("kennedy");
        NodeWeighted soacha = new NodeWeighted("soacha");
        NodeWeighted bosa = new NodeWeighted("bosa");
        NodeWeighted centro = new NodeWeighted("centro");
        NodeWeighted chapinero = new NodeWeighted("chapinero");
        NodeWeighted suba = new NodeWeighted("suba");
        NodeWeighted engativa = new NodeWeighted("engativa");
        NodeWeighted teusaquillo = new NodeWeighted("teusaquillo");

        graphWeighted.addEdge(chapinero,suba,1);
        graphWeighted.addEdge(suba,chapinero,1);

        graphWeighted.addEdge(centro,chapinero,4);
        graphWeighted.addEdge(chapinero,centro,4);

        graphWeighted.addEdge(suba,engativa,3);
        graphWeighted.addEdge(engativa,suba,3);

        graphWeighted.addEdge(centro,suba,7);
        graphWeighted.addEdge(suba,centro,7);

        graphWeighted.addEdge(teusaquillo,engativa,5);
        graphWeighted.addEdge(engativa,teusaquillo,5);

        graphWeighted.addEdge(kennedy,teusaquillo,8);
        graphWeighted.addEdge(teusaquillo,kennedy,8);

        graphWeighted.addEdge(kennedy,soacha,5);
        graphWeighted.addEdge(soacha,kennedy,5);

        graphWeighted.addEdge(soacha,bosa,4);
        graphWeighted.addEdge(bosa,soacha,4);

        graphWeighted.addEdge(centro,bosa,6);
        graphWeighted.addEdge(bosa,centro,6);

        graphWeighted.addEdge(centro,soacha,12);
        graphWeighted.addEdge(soacha,centro,12);

        graphWeighted.addEdge(teusaquillo,centro,2);
        graphWeighted.addEdge(centro,teusaquillo,2);


        System.out.println("shortest path: "+graphWeighted.DijkstraShortestPath(suba,centro));

        return graphWeighted;
    }
}
