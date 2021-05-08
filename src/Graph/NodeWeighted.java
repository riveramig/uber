package Graph;

import java.util.LinkedList;

public class NodeWeighted {
    // The int n and String name are just arbitrary attributes
    // we've chosen for our nodes these attributes can of course
    // be whatever you need
    String name;
    private boolean visited;
    LinkedList<EdgeWeighted> edges;
    LinkedList<String> vehiclesInNode;
    LinkedList<String> usersInNode;

    public NodeWeighted(String name) {
        this.name = name;
        visited = false;
        edges = new LinkedList<>();
        vehiclesInNode = new LinkedList<>();
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

    void addVehicleInNode(String vId) {
        this.vehiclesInNode.add(vId);
    }

    void removeVehicleNode(String vId) {
        this.vehiclesInNode.remove(vId);
    }

    void addUserInNode(String userId) {
        this.usersInNode.add(userId);
    }

    void removeUserInNode(String userId) {
        this.usersInNode.remove(userId);
    }
}
