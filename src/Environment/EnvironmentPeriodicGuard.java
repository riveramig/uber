package Environment;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Graph.GraphWeighted;
import Graph.NodeWeighted;
import User.UserAgent;
import User.UserGuard;
import User.UserState;
import Vehicles.VehicleAgent;
import Vehicles.VehicleState;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class EnvironmentPeriodicGuard extends PeriodicGuardBESA {
    @Override
    public void funcPeriodicExecGuard(EventBESA eventBESA) {

        EnvironmentState envState = (EnvironmentState) this.agent.getState();
        long currentMilis = System.currentTimeMillis();
        int passengers = (int) ((Math.sin(currentMilis)+2)*10);
        List<NodeWeighted> nodes = envState.getGraph().getAllGraphNodes();
        GraphWeighted graph = envState.getGraph();
        while(passengers>0){
            Random rand = new Random();
            UUID uuid = UUID.randomUUID();
            int intRandom = rand.nextInt(nodes.size());
            String nodeRandomSelectedAlias = nodes.get(intRandom).name;
            String uuidAsString = uuid.toString();
            try {
                UserAgent user = this.generateAgentUser(uuidAsString);
                user.start();
                this.getAgent().getAdmLocal().registerAgent(user,uuidAsString,uuidAsString);
                graph.AddUserToNode(nodeRandomSelectedAlias,this.generateAgentUser(uuidAsString));
                //Logica usuario pedir servicio
            } catch (ExceptionBESA exceptionBESA) {
                exceptionBESA.printStackTrace();
            }
            passengers--;
        }
        /*for(NodeWeighted node: nodes) {
            System.out.println("users in Node: "+node.name+" -> "+node.usersInNode.size());
            System.out.println("vehicles in node: ");
            for(VehicleAgent v: node.vehiclesInNode) {
                System.out.println("vehicle type "+((VehicleState)v.getState()).getVehicleType());
            }
            System.out.println("----------------------------------------------------------------------");
        }*/
    }

    private UserAgent generateAgentUser(String alias) throws ExceptionBESA {
        UserState userState = new UserState();
        StructBESA structBESA = new StructBESA();
        structBESA.bindGuard(UserGuard.class);
        AgHandlerBESA ah = this.getAgent().getAdmLocal().getHandlerByAlias("EnvironmentAgent");
        return new UserAgent(alias,userState,structBESA,0.91);
    }
}
