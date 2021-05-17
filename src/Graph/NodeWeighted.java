package Graph;

import User.UserAgent;
import Vehicles.VehicleAgent;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class NodeWeighted {
    // The int n and String name are just arbitrary attributes
    // we've chosen for our nodes these attributes can of course
    // be whatever you need
    public String name;
    private boolean visited;
    LinkedList<EdgeWeighted> edges;
    public ConcurrentHashMap<String, VehicleAgent> vehiclesInNode;
    public ConcurrentHashMap<String, UserAgent> usersInNode;

    public NodeWeighted(String name) {
        this.name = name;
        visited = false;
        edges = new LinkedList<>();
        vehiclesInNode = new ConcurrentHashMap<>();
        usersInNode = new ConcurrentHashMap<>();
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

    void addVehicleInNode(String alias, VehicleAgent vId) {
        this.vehiclesInNode.put(alias, vId);
    }

    void removeVehicleNode(String alias) {
        this.vehiclesInNode.remove(alias);
    }

    void addUserInNode(String alias, UserAgent userId) {
        this.usersInNode.put(alias,userId);
    }

    void removeUserInNode(String alias) {
        this.usersInNode.remove(alias);
    }


}
