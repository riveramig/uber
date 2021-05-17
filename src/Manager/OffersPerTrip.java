package Manager;

import java.util.ArrayList;
import java.util.List;

public class OffersPerTrip {

    private boolean hasStartedThread;
    private List<Offer> offersTrip;

    public OffersPerTrip() {
        this.offersTrip = new ArrayList<>();
    }

    public boolean isHasStartedThread() {
        return hasStartedThread;
    }

    public void setHasStartedThread(boolean hasStartedThread) {
        this.hasStartedThread = hasStartedThread;
    }

    public List<Offer> getOffersTrip() {
        return offersTrip;
    }

    public void setOffersTrip(List<Offer> offersTrip) {
        this.offersTrip = offersTrip;
    }

    public void addOffer(Offer offer) {
        this.offersTrip.add(offer);
    }
}
