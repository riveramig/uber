package Manager;


import BESA.Kernel.Agent.KernellAgentExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Log.ReportBESA;


public class ManagerContainer {
    public static void main(String[] args) throws KernellAgentExceptionBESA {

        ReportBESA.info("Lanzando config/manager.xml");
        AdmBESA adminBesa = AdmBESA.getInstance("config/manager.xml");

        ManagerState managerState1 = new ManagerState();
        managerState1.addNeighbor("chapinero");
        managerState1.addNeighbor("centro");
        managerState1.addNeighbor("suba");
        ManagerState managerState2 = new ManagerState();
        managerState2.addNeighbor("bosa");
        managerState2.addNeighbor("soacha");
        managerState2.addNeighbor("kennedy");
        ManagerState managerState3 = new ManagerState();
        managerState3.addNeighbor("engativa");
        managerState3.addNeighbor("teusaquillo");

        StructBESA StructSender = new StructBESA();
        StructSender.bindGuard(ManagerGuard.class);
        ManagerAgent managerAgent1 = new ManagerAgent("ManagerAgent01", managerState1, StructSender, 0.91);
        ManagerAgent managerAgent2 = new ManagerAgent("ManagerAgent02", managerState2, StructSender, 0.91);
        ManagerAgent managerAgent3 = new ManagerAgent("ManagerAgent03", managerState3, StructSender, 0.91);
        managerAgent1.start();
        managerAgent2.start();
        managerAgent3.start();
        adminBesa.registerAgent(managerAgent1, "ManagerAgent01", "ManagerAgent01");
        adminBesa.registerAgent(managerAgent2, "ManagerAgent02", "ManagerAgent02");
        adminBesa.registerAgent(managerAgent3, "ManagerAgent03", "ManagerAgent03");
    }
}
