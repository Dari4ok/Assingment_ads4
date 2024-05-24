# GraphTest

GraphTest is a Java project that demonstrates the creation and manipulation of a graph data structure. This project allows you to create an undirected and weighted graph, add and remove edges, and retrieve information about the graph. The project includes functionality for basic graph operations as well as searching and finding shortest paths using Dijkstra's algorithm.

## Installation

1. Clone the repository:

```sh
git clone https://github.com/yourusername/graphtest.git
cd graphtest
```

2. Compile the Java files:

```sh
javac GraphTest.java Graph.java
```

## Usage

Run the `GraphTest` program:

```sh
java GraphTest
```

This will execute the `main` method in the `GraphTest` class, which performs the following steps:

1. Creates an undirected and weighted graph.
2. Adds vertices labeled "1" to "5" with names of cities in Kazakhstan.
3. Adds edges between some of the vertices.
4. Checks for the existence of certain edges and prints the results.
5. Retrieves and prints the neighbors of a specific vertex.
6. Prints the graph.
7. Removes an edge and prints the graph again to show the change.

## Example Output

```sh
true
false
[V2(Astana), V3(Shymkent)]
Vertex V1(Almaty) is connected to: V2(Astana) (weight 1), V3(Shymkent) (weight 1), 
Vertex V2(Astana) is connected to: V1(Almaty) (weight 1), V4(Pavlodar) (weight 1), V5(Oral) (weight 1), 
Vertex V3(Shymkent) is connected to: V1(Almaty) (weight 1), 
Vertex V4(Pavlodar) is connected to: V2(Astana) (weight 1), 
Vertex V5(Oral) is connected to: V2(Astana) (weight 1), 
_____________________________
Vertex V1(Almaty) is connected to: V2(Astana) (weight 1), V3(Shymkent) (weight 1), 
Vertex V2(Astana) is connected to: V1(Almaty) (weight 1), V4(Pavlodar) (weight 1), 
Vertex V3(Shymkent) is connected to: V1(Almaty) (weight 1), 
Vertex V4(Pavlodar) is connected to: V2(Astana) (weight 1), 
Vertex V5(Oral) is connected to: 
```

## Classes

### Graph

The `Graph` class represents a graph data structure. It includes inner classes `Vertex` and `Edge` to represent the vertices and edges of the graph, respectively.

#### Methods

- `Graph(boolean undirected, boolean weighted)`: Constructor to initialize the graph.
- `void addVertex(Vertex vertex)`: Adds a vertex to the graph.
- `boolean hasVertex(Vertex vertex)`: Checks if a vertex exists in the graph.
- `boolean hasEdge(Vertex source, Vertex dest)`: Checks if an edge exists between two vertices.
- `void addEdge(Vertex source, Vertex dest)`: Adds an unweighted edge between two vertices.
- `void addEdge(Vertex source, Vertex dest, int weight)`: Adds a weighted edge between two vertices.
- `Set<Vertex> getVertices()`: Returns a set of all vertices in the graph.
- `List<Edge> getEdges()`: Returns a list of all edges in the graph.
- `boolean search(Vertex start)`: Performs a breadth-first search from a given vertex.
- `Map<Vertex, Integer> dijkstra(Vertex start)`: Finds the shortest paths from a given vertex using Dijkstra's algorithm.
- `void printGraph()`: Prints the graph.
- `void removeVertex(Vertex vertex)`: Removes a vertex and its associated edges from the graph.
- `void removeEdge(Vertex source, Vertex dest)`: Removes an edge between two vertices.
- `List<Vertex> getNeighbors(Vertex vertex)`: Returns a list of neighbors of a given vertex.

### Vertex

The `Vertex` class represents a vertex in the graph.

#### Methods

- `Vertex(String id, String data)`: Constructor to initialize a vertex.
- `String getId()`: Returns the ID of the vertex.
- `String getData()`: Returns the data associated with the vertex.
- `void setData(String data)`: Sets the data associated with the vertex.
- `String toString()`: Returns a string representation of the vertex.
- `boolean equals(Object o)`: Checks if two vertices are equal.
- `int hashCode()`: Returns the hash code for the vertex.

### Edge

The `Edge` class represents an edge in the graph.

#### Methods

- `Edge(Vertex source, Vertex dest, int weight)`: Constructor to initialize an edge.
- `Vertex getSource()`: Returns the source vertex of the edge.
- `Vertex getDest()`: Returns the destination vertex of the edge.
- `int getWeight()`: Returns the weight of the edge.
- `String toString()`: Returns a string representation of the edge.

