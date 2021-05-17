package Environment;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Graph.GraphWeighted;
import Graph.NodeWeighted;
import Manager.ManagerMessage;
import User.UserGuard;
import User.UserMessage;
import Vehicles.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

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
            case ASK_RANDOM_LOCATION:
                String currentLocation = message.getMetaData();
                String randLoc = currentLocation;
                while(currentLocation.equals(randLoc)){
                    Random rand = new Random();
                    int intRandom = rand.nextInt(currentGraph.getAllGraphNodes().size());
                    randLoc = currentGraph.getAllGraphNodes().get(intRandom).name;
                }
                try {
                    AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(message.getFrom());
                    UserMessage message1 = new UserMessage(randLoc);
                    EventBESA ev = new EventBESA(UserGuard.class.getName(),message1);
                    ah.sendEvent(ev);
                } catch (ExceptionBESA exceptionBESA) {
                    exceptionBESA.printStackTrace();
                }
                break;
            case BROADCAST_TRIP:
                String[] neighborsToBroadcast = message.getNeighbors().split(",");
                String from = message.getMetaData().split(",")[0];
                String to = message.getMetaData().split(",")[1];
                for (int i = 0; i < neighborsToBroadcast.length; i++) {
                    String neighbor = neighborsToBroadcast[i];
                    NodeWeighted currentNeighbor = currentGraph.getNodeByAlias(neighbor);
                    Iterator<VehicleAgent> allVehiclesIt = currentNeighbor.vehiclesInNode.values().iterator();
                    while (allVehiclesIt.hasNext()) {
                        VehicleAgent vAgent = allVehiclesIt.next();
                        try{
                            AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias(vAgent.getAlias());
                            VehicleMessage vMessage = new VehicleMessage(VehicleMessageType.TRIP_REQUEST);
                            vMessage.setFrom(from);
                            vMessage.setTo(to);
                            vMessage.setManagerId(message.getFrom());
                            vMessage.setUserId(message.getUserId());
                            EventBESA ev = new EventBESA(VehicleGuard.class.getName(),vMessage);
                            ah.sendEvent(ev);
                        }catch (ExceptionBESA e){
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case FINISH_CAR_TRIP:
                String filename = "E:\\trips.csv";
                String vehicleId = message.getAliasSender();
                String fromNode = message.getFrom();
                String toNode = message.getTo();
                String userId = message.getUserId();
                String costTrip = message.getMetaData();
                currentGraph.updateVehiclePosition(fromNode,toNode,vehicleId);
                currentGraph.removeUserTripFinish(fromNode,userId);
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
                    writer.append(fromNode+"|"+toNode+"|"+vehicleId+"|"+userId+"|"+costTrip+"|"+System.currentTimeMillis()+"\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
