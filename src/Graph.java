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

    //Dijkstra shortest path search. prints all paths
    public Map<Vertex, Integer> dijkstra(Vertex start) {
        Map<Vertex, Integer> distances = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        for (Vertex vertex : map.keySet()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
            }
            priorityQueue.add(vertex);
        }

        while (!priorityQueue.isEmpty()) {
            Vertex current = priorityQueue.poll();

            for (Edge edge : map.get(current)) {
                Vertex neighbor = edge.getDest();
                int newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    priorityQueue.remove(neighbor);
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, current);
                    priorityQueue.add(neighbor);
                }
            }
        }

        return distances;
    }

    public void printGraph() {
        for (Vertex vertex : map.keySet()) {
            System.out.print("Vertex " + vertex + " is connected to: ");
            for (Edge edge : map.get(vertex)) {
                System.out.print(edge.dest + " (weight " + edge.weight + "), ");
            }
            System.out.println();
        }
    }

    public void removeVertex(Vertex vertex) {
        if (!hasVertex(vertex)) return;
        map.remove(vertex);

        for (List<Edge> edges : map.values()) {
            edges.removeIf(edge -> edge.getDest().equals(vertex));
        }

        if (undirected) {
            for (Vertex v : getVertices()) {
                map.get(v).removeIf(edge -> edge.getDest().equals(vertex));
            }
        }
    }

    public void removeEdge(Vertex source, Vertex dest) {
        if (!hasEdge(source, dest)) return;
        map.get(source).removeIf(edge -> edge.getDest().equals(dest));
        if (undirected) {
            map.get(dest).removeIf(edge -> edge.getDest().equals(source));
        }
    }

    public List<Vertex> getNeighbors(Vertex vertex) {
        if (!hasVertex(vertex)) return new ArrayList<>();
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : map.get(vertex)) {
            neighbors.add(edge.getDest());
        }
        return neighbors;
    }
}
