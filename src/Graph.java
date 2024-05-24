import java.util.*;

public class Graph {
    private Map<Vertex, List<Edge>> map;
    private boolean undirected;
    private boolean weighted;

    public class Vertex{
        private String id;
        private String data;
    }

    public class Edge {
        private Vertex source;
        private Vertex dest;
        private int weight;
    }

}
