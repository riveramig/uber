package Manager;


import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;

public class ManagerGuard extends GuardBESA {
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        ManagerMessage message = (ManagerMessage) eventBESA.getData();
        ManagerState managerState = (ManagerState) this.agent.getState();
        switch (message.getType()) {
            case CREATE_TRIP:
                String from = message.getMetaData().split(" ")[0];
                String to = message.getMetaData().split(" ")[1];
                String userId = message.getFrom();
                String isNeighborMine =
                        managerState.getNeighborsAssigned().stream().filter(str->str.equals(from)).findAny().orElse("");
                if(!isNeighborMine.isEmpty()){
                    System.out.println("found trip!! ("+from+"-"+to+")");
                    System.out.println("user id-> "+userId);
                    // preguntar al ambiente los vehiculos asignar uno, que el vehiculo se mueva y se sume el costo del viaje
                    // en variable 'from' esta el barrio de origen del viaje
                    // en variable 'to' esta el barrio de destino
                }
                break;

        }




        /*AgHandlerBESA ah;
        while(true){
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
        }*/

    }
}
