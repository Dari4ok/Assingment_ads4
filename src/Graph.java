import java.util.*;

public class Graph {
    private Map<Vertex, List<Edge>> map;
    private boolean undirected;
    private boolean weighted;

    public Graph(boolean undirected, boolean weighted) {
        this.undirected = undirected;
        this.weighted = weighted;
        this.map = new HashMap<>();
    }

    public class Vertex{
        private String id;
        private String data;

        public Vertex(String id, String data) {
            this.id = id;
            this.data = data;
        }
    }

    public class Edge {
        private Vertex source;
        private Vertex dest;
        private int weight;

        public Edge(Vertex source, Vertex dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }

    }

    public void addVertex(Vertex vertex) {
        if (!map.containsKey(vertex)) {
            map.put(vertex, new ArrayList<>());
        }
    }

    public boolean hasVertex(Vertex vertex) {
        return map.containsKey(vertex);
    }

    public boolean hasEdge(Vertex source, Vertex dest) {
        if (!hasVertex(source) || !hasVertex(dest)) return false;
        return map.get(source).stream().anyMatch(edge -> edge.dest.equals(dest));
    }

    public void addEdge(Vertex source, Vertex dest) {
        if (weighted) {
            throw new UnsupportedOperationException("Cannot add unweighted edges to a weighted graph.");
        }
        if (!hasVertex(source)) addVertex(source);
        if (!hasVertex(dest)) addVertex(dest);
        if (hasEdge(source, dest) || source.equals(dest)) return;

        map.get(source).add(new Edge(source, dest, 0));
        if (undirected) map.get(dest).add(new Edge(dest, source, 0));
    }


}
