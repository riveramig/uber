package Vehicles;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Environment.EnvironmentGuard;
import Environment.EnvironmentMessage;
import Environment.EnvironmentMessageType;
import Graph.GraphWeighted;
import Graph.NodeWeighted;
import Manager.ManagerGuard;
import Manager.ManagerMessage;
import Manager.ManagerMessageType;

public class VehicleGuard extends GuardBESA {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        VehicleMessage message = (VehicleMessage) eventBESA.getData();
        VehicleState vehicleState = (VehicleState) this.agent.getState();
        GraphWeighted graphWeighted = vehicleState.getGraph();
        switch (message.getType()){
            case TRIP_REQUEST:
                String vehicleCurrentNode = vehicleState.getCurrentNodeLocation();
                System.out.println("Receiving broadcast trip message!!");
                System.out.println(ANSI_YELLOW+"From: "+message.getFrom()+"->"+message.getTo()+ANSI_RESET);
                double cost = 0;
                if(vehicleCurrentNode.equals(message.getFrom())){
                    // Estoy en el mismo nodo que el pasajero que requiere el viaje
                    NodeWeighted nodeFrom = graphWeighted.getNodeByAlias(message.getFrom());
                    NodeWeighted nodeTo = graphWeighted.getNodeByAlias(message.getTo());
                    cost = graphWeighted.DijkstraShortestPath(nodeFrom,nodeTo);
                    graphWeighted.resetNodesVisited();
                } else {
                    // No estoy en el mismo nodo
                    // primero el costo de ir al nodo donde se encuentra el pasajeiro
                    NodeWeighted nodeFromFirstStretch = graphWeighted.getNodeByAlias(vehicleState.getCurrentNodeLocation());
                    NodeWeighted nodeToFirstStretch = graphWeighted.getNodeByAlias(message.getFrom());
                    NodeWeighted nodeToSecondStretch = graphWeighted.getNodeByAlias(message.getTo());
                    double partial1 = graphWeighted.DijkstraShortestPath(nodeFromFirstStretch,nodeToFirstStretch);
                    graphWeighted.resetNodesVisited();
                    double partial2 = graphWeighted.DijkstraShortestPath(nodeToFirstStretch,nodeToSecondStretch);
                    graphWeighted.resetNodesVisited();
                    cost = partial1 + partial2;
                }
                try {
                    AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(message.getManagerId());
                    ManagerMessage managerMessage = new ManagerMessage(ManagerMessageType.VEHICLE_COST_OFFER);
                    managerMessage.setFrom(this.agent.getAlias());
                    managerMessage.setUserId(message.getUserId());
                    managerMessage.setFromNode(message.getFrom());
                    managerMessage.setToNode(message.getTo());
                    managerMessage.setCostTrip(cost);
                    EventBESA ev = new EventBESA(ManagerGuard.class.getName(),managerMessage);
                    ah.sendEvent(ev);
                } catch (ExceptionBESA e) {
                    e.printStackTrace();
                }
                break;
            case TRIP_ACCEPTED:
                try {
                    AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias("EnvironmentAgent");
                    EnvironmentMessage envMessage = new EnvironmentMessage(EnvironmentMessageType.FINISH_CAR_TRIP);
                    envMessage.setAliasSender(this.agent.getAlias());
                    envMessage.setUserId(message.getUserId());
                    envMessage.setFrom(message.getFrom());
                    envMessage.setTo(message.getTo());
                    envMessage.setMetaData(message.getCost()+"");
                    EventBESA ev = new EventBESA(EnvironmentGuard.class.getName(),envMessage);
                    ah.sendEvent(ev);
                } catch (ExceptionBESA e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
