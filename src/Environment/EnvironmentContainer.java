package Environment;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.PeriodicGuardBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import BESA.Util.PeriodicDataBESA;
import ContainersLauncher.BenchmarkConfig;
import Graph.GraphWeighted;
import Graph.NodeWeighted;
import Vehicles.VehicleAgent;
import Vehicles.VehicleGuard;
import Vehicles.VehicleState;
import Vehicles.VehicleType;

import java.util.List;
import java.util.Random;
import java.util.UUID;


public class EnvironmentContainer {

    public static void main(String[] args) throws KernellAgentExceptionBESA {
        ReportBESA.info("Lanzando config/environment.xml");
        AdmBESA adminBesa = AdmBESA.getInstance("config/environment.xml");

        EnvironmentState environmentState = new EnvironmentState(createGraph());
        StructBESA StructSender = new StructBESA();
        StructSender.bindGuard(EnvironmentGuard.class);
        StructSender.bindGuard(EnvironmentPeriodicGuard.class);
        EnvironmentAgent environmentAgent = new EnvironmentAgent("EnvironmentAgent", environmentState, StructSender, 0.91);
        environmentAgent.start();
        adminBesa.registerAgent(environmentAgent, "EnvironmentAgent", "EnvironmentAgent");

        /**
         *
         *
         * Generar logica de autos
         *
         */
        int buses = (int) (5);
        int cars = (int) (buses*2);
        int bikes = (int) (cars*2);

        List<NodeWeighted> nodes = environmentState.getGraph().getAllGraphNodes();
        GraphWeighted graph = environmentState.getGraph();
        //Buses logic
        while (buses>0) {
            Random rand = new Random();
            UUID uuidb = UUID.randomUUID();
            int intRandom = rand.nextInt(nodes.size());
            String nodeRandomSelectedAlias = nodes.get(intRandom).name;
            String uuidAsStringb = uuidb.toString();
            try{
                VehicleAgent vehicle = generateVehicleAgent(uuidAsStringb, VehicleType.BUS);
                vehicle.start();
                adminBesa.registerAgent(vehicle,uuidAsStringb,uuidAsStringb);
                graph.addVehicleToNode(nodeRandomSelectedAlias,vehicle);
                } catch (ExceptionBESA exceptionBESA) {
                    exceptionBESA.printStackTrace();
                }
                buses--;
            }
        //Cars logic
        while (cars>0) {
            Random rand = new Random();
            UUID uuidc = UUID.randomUUID();
            int intRandom = rand.nextInt(nodes.size());
            String nodeRandomSelectedAlias = nodes.get(intRandom).name;
            String uuidAsStringc = uuidc.toString();
            try{
                VehicleAgent vehicle = generateVehicleAgent(uuidAsStringc, VehicleType.CAR);
                vehicle.start();
                adminBesa.registerAgent(vehicle,uuidAsStringc,uuidAsStringc);
                graph.addVehicleToNode(nodeRandomSelectedAlias,vehicle);
                } catch (ExceptionBESA exceptionBESA) {
                    exceptionBESA.printStackTrace();
                }
                cars--;
            }
        //Bikes logic
        while (bikes>0) {
            Random rand = new Random();
            UUID uuidbi = UUID.randomUUID();
            int intRandom = rand.nextInt(nodes.size());
            String nodeRandomSelectedAlias = nodes.get(intRandom).name;
            String uuidAsStringbi = uuidbi.toString();
            try{
                VehicleAgent vehicle = generateVehicleAgent(uuidAsStringbi, VehicleType.BIKE);
                vehicle.start();
                adminBesa.registerAgent(vehicle,uuidAsStringbi,uuidAsStringbi);
                graph.addVehicleToNode(nodeRandomSelectedAlias,vehicle);
                } catch (ExceptionBESA exceptionBESA) {
                    exceptionBESA.printStackTrace();
                }
                bikes--;
            }     
        // ---> fin logica carros
        try {
            AgHandlerBESA ah = adminBesa.getHandlerByAlias("EnvironmentAgent");
            PeriodicDataBESA periodicData = new PeriodicDataBESA(1000, PeriodicGuardBESA.START_PERIODIC_CALL);
            EventBESA eventPeriodic = new EventBESA(EnvironmentPeriodicGuard.class.getName(),periodicData);
            ah.sendEvent(eventPeriodic);
        } catch (ExceptionBESA exceptionBESA) {
            exceptionBESA.printStackTrace();
        }

    }

    private static VehicleAgent generateVehicleAgent(String uuidAsString, VehicleType vehicleType) throws KernellAgentExceptionBESA {
        VehicleState vehicleState = new VehicleState();
        vehicleState.setVehicleType(vehicleType);
        StructBESA structBESA = new StructBESA();
        structBESA.bindGuard(VehicleGuard.class);
        return new VehicleAgent(uuidAsString,vehicleState,structBESA,0.91);
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
