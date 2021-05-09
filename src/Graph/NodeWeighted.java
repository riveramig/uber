package Graph;

import User.UserAgent;
import User.VehicleAgent;

import java.util.LinkedList;

public class NodeWeighted {
    // The int n and String name are just arbitrary attributes
    // we've chosen for our nodes these attributes can of course
    // be whatever you need
    public String name;
    private boolean visited;
    LinkedList<EdgeWeighted> edges;
    public LinkedList<VehicleAgent> vehiclesInNode;
    public LinkedList<UserAgent> usersInNode;

    public NodeWeighted(String name) {
        this.name = name;
        visited = false;
        edges = new LinkedList<>();
        vehiclesInNode = new LinkedList<>();
        usersInNode = new LinkedList<>();
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }

    void addVehicleInNode(VehicleAgent vId) {
        this.vehiclesInNode.add(vId);
    }

    void removeVehicleNode(String vId) {
        this.vehiclesInNode.remove(vId);
    }

    void addUserInNode(UserAgent userId) {
        this.usersInNode.add(userId);
    }

    void removeUserInNode(String userId) {
        this.usersInNode.remove(userId);
    }


}
