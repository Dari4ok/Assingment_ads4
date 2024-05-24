import java.util.Map;

public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph(true, true); // undirected and weighted

        Graph.Vertex v1 = graph.new Vertex("1", "Almaty");
        Graph.Vertex v2 = graph.new Vertex("2", "Astana");
        Graph.Vertex v3 = graph.new Vertex("3", "Shymkent");
        Graph.Vertex v4 = graph.new Vertex("4", "Pavlodar");
        Graph.Vertex v5 = graph.new Vertex("5", "Oral");

        graph.addEdge(v1, v2, 1);
        graph.addEdge(v1, v3, 1);

        graph.addEdge(v2, v4, 1);

        graph.addEdge(v2, v5, 1);

        System.out.println(graph.hasEdge(v1, v2));
        System.out.println(graph.hasEdge(v1, v4));

        System.out.println(graph.getNeighbors(v1));

        graph.printGraph();
        System.out.println("_____________________________");
        graph.removeEdge(v2, v5);

        graph.printGraph();
    }
}