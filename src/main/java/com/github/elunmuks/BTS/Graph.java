package com.github.elunmuks.BTS;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

// main data structure of the program
public class Graph {
    public ArrayList<GraphEdges> graphEdges;
    public ArrayList<GraphVertices> graphVertices;

    public Graph() {
        graphEdges = new ArrayList<>();
        graphVertices = new ArrayList<>();
    }

    private boolean isExistsVertexByNumber(int vertexNumber) {
        return vertexNumber >= 0 && vertexNumber < graphVertices.size();
    }
    private boolean isPossiblyEdgeByVertexNumber(GraphEdges graphEdge) {
        return isExistsVertexByNumber(graphEdge.firstVertex) && isExistsVertexByNumber(graphEdge.secondVertex);
    }
    public void addVertex(GraphVertices graphVertex) {
        graphVertices.add(graphVertex);
    }
    private void addEdgesInVertexByNumber(GraphEdges graphEdge) {
        graphVertices.get(graphEdge.firstVertex).vertexEdges.add(graphEdge);
        graphVertices.get(graphEdge.secondVertex).vertexEdges.add(graphEdge);
    }
    private void delEdgesInVertexByNumber(GraphEdges graphEdge) {
        graphVertices.get(graphEdge.firstVertex).vertexEdges.remove(graphEdge);
        graphVertices.get(graphEdge.secondVertex).vertexEdges.remove(graphEdge);
    }
    public void addEdges(GraphEdges graphEdge) {
        if (!isPossiblyEdgeByVertexNumber(graphEdge))
            throw new RuntimeException("The edge has a non-existent vertex.\n");
        for (GraphEdges graphEdges : this.graphEdges) {
            if (graphEdge.equals(graphEdges))
                return;
        }
        graphEdges.add(graphEdge);
        addEdgesInVertexByNumber(graphEdge);
    }

    public void delEdges(GraphEdges graphEdge) {
        if (!graphEdges.contains(graphEdge))
            throw new RuntimeException("This edge not found.\n");
        graphEdges.remove(graphEdge);
        delEdgesInVertexByNumber(graphEdge);
    }
    private void delEdgesByVertexNumber(int vertexNumber) {
        Iterator<GraphEdges> iterator = graphEdges.iterator();
        while (iterator.hasNext()) {
            GraphEdges rem = iterator.next();
            if (rem.isNeededEdgeByVertexNumber(vertexNumber)) {
                iterator.remove();
            }
        }
    }

    public void delVertex(GraphVertices graphVertex) {
        int k;
        if (!graphVertices.contains(graphVertex))
            throw new RuntimeException("This vertex not found.\n");
        k = graphVertices.indexOf(graphVertex);
        graphVertices.remove(graphVertex);
        delEdgesByVertexNumber(k);
    }
    public void graphFromFile() throws IOException, URISyntaxException {
        Path path = Path.of(Objects.requireNonNull(getClass().getResource("/forGraph.txt")).toURI());
        List<String> list = Files.readAllLines(path);

        int vertexNumber = Integer.parseInt(list.get(0));
        for (int i = 0; i < vertexNumber; i++) {
            addVertex(new GraphVertices());
        }

        for (String str : list) {
            if (str.compareTo(list.get(0)) == 0)
                continue;
            String[] S = str.split(" ");
            addEdges(new GraphEdges(Integer.parseInt(S[0]), Integer.parseInt(S[1]), Double.parseDouble(S[2])));
        }
    }
}
