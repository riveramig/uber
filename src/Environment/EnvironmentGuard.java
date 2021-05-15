package Environment;

import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Log.ReportBESA;
import Graph.GraphWeighted;
import Graph.NodeWeighted;

public class EnvironmentGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        EnvironmentMessage message = (EnvironmentMessage) eventBESA.getData();
        EnvironmentState envState = (EnvironmentState) this.agent.getState();
        GraphWeighted currentGraph = envState.getGraph();

        switch (message.getType()){
            case GET_PATH_COST:
                NodeWeighted nodeFrom = currentGraph.getNodeByAlias(message.getFrom());
                NodeWeighted nodeTo = currentGraph.getNodeByAlias(message.getTo());
                if(nodeFrom == null || nodeTo == null){
                    ReportBESA.error("Node from or node to not found in the graph");
                    break;
                }
                double cost = currentGraph.DijkstraShortestPath(nodeFrom,nodeTo);
                // logic to send message of cost to car
                break;


        }

    }
}
