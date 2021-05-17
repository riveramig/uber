package Manager;


import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Environment.EnvironmentGuard;
import Environment.EnvironmentMessage;
import Environment.EnvironmentMessageType;
import Vehicles.VehicleGuard;
import Vehicles.VehicleMessage;
import Vehicles.VehicleMessageType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ManagerGuard extends GuardBESA {
    @Override
    public void funcExecGuard(EventBESA eventBESA) {
        ManagerMessage message = (ManagerMessage) eventBESA.getData();
        ManagerState managerState = (ManagerState) this.agent.getState();
        ScheduledThreadPoolExecutor exec;
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
                    try {
                        AgHandlerBESA ah = this.agent.getAdmLocal().getHandlerByAlias("EnvironmentAgent");
                        EnvironmentMessage environmentMessage = new EnvironmentMessage(EnvironmentMessageType.BROADCAST_TRIP);
                        environmentMessage.setMetaData(from+","+to);
                        StringBuilder str = new StringBuilder("");
                        for(String neighbor: managerState.getNeighborsAssigned()){
                            str.append(neighbor).append(",");
                        }
                        environmentMessage.setUserId(userId);
                        environmentMessage.setNeighbors(str.toString());
                        environmentMessage.setFrom(this.agent.getAlias());
                        EventBESA ev = new EventBESA(EnvironmentGuard.class.getName(),environmentMessage);
                        ah.sendEvent(ev);
                    }catch (ExceptionBESA e){
                        e.printStackTrace();
                    }
                    // preguntar al ambiente los vehiculos asignar uno, que el vehiculo se mueva y se sume el costo del viaje
                    // en variable 'from' esta el barrio de origen del viaje
                    // en variable 'to' esta el barrio de destino
                }
                break;
            case VEHICLE_COST_OFFER:
                Hashtable<String, List<Offer>>  allTrips = managerState.getAllTripsManager();
                Offer offer = new Offer();
                offer.setCost(message.getCostTrip());
                offer.setFrom(message.getFromNode());
                offer.setTo(message.getToNode());
                offer.setVehicleId(message.getFrom());
                offer.setUserId(message.getUserId());
                List<Offer> offersPerTrip = allTrips.get(message.getUserId());
                if(offersPerTrip == null){
                    ArrayList<Offer>allOffers = new ArrayList<>();
                    allOffers.add(offer);
                    allTrips.put(message.getUserId(),allOffers);
                    //inicia hilo buffer
                    exec = new ScheduledThreadPoolExecutor(1);
                    ManagerAgent currentAgent = (ManagerAgent) this.agent;
                    exec.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                List<Offer>allOffers = allTrips.get(message.getUserId());
                                System.out.println("All offers for user "+message.getUserId()+": "+allOffers.size());
                                Offer lesCostOffer = allOffers.stream().min(Comparator.comparing(Offer::getCost)).orElse(null);
                                if(lesCostOffer != null){
                                    System.out.println("less offer found!!! "+lesCostOffer.getUserId()+" cost: "+lesCostOffer.getCost());
                                    AgHandlerBESA ah = currentAgent.getAdmLocal().getHandlerByAlias(lesCostOffer.getVehicleId());
                                    VehicleMessage vehicleMessage = new VehicleMessage(VehicleMessageType.TRIP_ACCEPTED);
                                    vehicleMessage.setManagerId(currentAgent.getAlias());
                                    vehicleMessage.setFrom(lesCostOffer.getFrom());
                                    vehicleMessage.setTo(lesCostOffer.getTo());
                                    vehicleMessage.setUserId(lesCostOffer.getUserId());
                                    vehicleMessage.setCost(lesCostOffer.getCost());
                                    EventBESA ev = new EventBESA(VehicleGuard.class.getName(),vehicleMessage);
                                    ah.sendEvent(ev);
                                    allTrips.remove(message.getUserId());
                                }else{
                                    System.out.println("Algo anda mal D:");
                                }
                            }catch (ExceptionBESA e) {
                                e.printStackTrace();
                            }
                        }
                    },500, TimeUnit.MILLISECONDS);
                    exec.shutdown();
                }else {
                    offersPerTrip.add(offer);
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
