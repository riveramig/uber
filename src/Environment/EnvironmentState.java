package Environment;

import BESA.Kernel.Agent.StateBESA;
import Graph.GraphWeighted;

public class EnvironmentState extends StateBESA {

    private GraphWeighted graph;

    public EnvironmentState(GraphWeighted graph) {
        this.graph = graph;
    }

    public GraphWeighted getGraph() {
        return graph;
    }

    public void setGraph(GraphWeighted graph) {
        this.graph = graph;
    }
}
