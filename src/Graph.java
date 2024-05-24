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

        public String getId() {
            return id;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "V" + id + "(" + data + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(id, vertex.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
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

        public Vertex getSource() {
            return source;
        }

        public Vertex getDest() {
            return dest;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge {" + source + ", -> " + dest + "} weight = " + weight;
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

    public void addEdge(Vertex source, Vertex dest, int weight) {
        if (!weighted) {
            throw new UnsupportedOperationException("Cannot add weighted edges to an unweighted graph.");
        }
        if (!hasVertex(source)) addVertex(source);
        if (!hasVertex(dest)) addVertex(dest);
        if (hasEdge(source, dest) || source.equals(dest)) return;

        map.get(source).add(new Edge(source, dest, weight));
        if (undirected) map.get(dest).add(new Edge(dest, source, weight));
    }

    public Set<Vertex> getVertices() {
        return map.keySet();
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (List<Edge> edgeList : map.values()) {
            edges.addAll(edgeList);
        }
        return edges;
    }

    // BFS search return true if contains and false otherwise
    public boolean search(Vertex start) {
        if (!hasVertex(start)) {
            //System.out.println("Vertex not found in the graph.");
            return false;
        }

        Set<Vertex> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            //System.out.print(current + " ");

            for (Edge edge : map.get(current)) {
                Vertex neighbor = edge.getDest();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return true;
    }




}
